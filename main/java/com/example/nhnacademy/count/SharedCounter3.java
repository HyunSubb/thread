package com.example.nhnacademy.count;

import java.util.concurrent.locks.ReentrantLock;

public class SharedCounter3 {
    private long count;
    private final ReentrantLock mutex;
    /*
     * Mutex(뮤 텍스) - (Mutual Exclusion)의 약자이다. Mut Ex -> 뮤텍스
     * 
     * 뮤 텍스는 한 번에 하나의 스레드만 임께 구역에 접근할 수 있도록 보장하는 잠금 매커니즘이다.
     * 
     * 뮤텍스와 세마포어를 1로 설정하였을 때 동일한 기능을 수행할 수 있는 경우도 있지만, 
     * 두 개념은 서로 다른 특징과 용도를 가지고 있다.
     * 
     * lock() - lock() 메서드는 뮤텍스를 잠근다. 
     * 만약 다른 스레드가 이미 뮤텍스를 잠근 상태라면, 현재 스레드는 뮤텍스가 해제될 때까지 대기한다.
     * 
     * unlock() - unlock() 메서드를 통해 여러 스레드가 동시에 임계 구역에 접근하는 것을 방지하고, 공유 자원의 일관성과
     * 무결성을 유지할 수 있다.
     * 
     * Fair Lock(공정한 락)
     * 
     *      ReentrantLock은 생성자에서 공정성(fairness)을 설정할 수 있다. 공정한 락은 FIFO (First-In-First-Out) 순서로 
     *      락을 대기하는 스레드에게 락을 부여합니다. 즉, 먼저 락을 요청한 스레드가 먼저 락을 획득하게 됩니다. 
     *      이를 통해 모든 스레드가 공평하게 락을 획득할 기회를 가지게 됩니다.

            기아 현상(starvation)을 방지할 수 있습니다.

            성능이 약간 저하 : 공정한 락은 스레드 간의 컨텍스트 스위칭이 더 자주 발생할 수 있습니다.

            //공정한 락 설정
            private final Lock lock = new ReentrantLock(true);
        
        Unfair Lock(비공정한 락)

            기본적으로 ReentrantLock은 비공정한 락입니다. 
            비공정한 락은 락을 요청하는 순서와 상관없이 현재 락을 해제한 스레드가 다시 락을 획득할 수 있는 기회를 더 많이 가집니다. 
            이는 성능 면에서 유리할 수 있지만, 특정 스레드가 락을 오랫동안 획득하지 못하는 기아 현상(Starvation)이 발생할 수 있습니다.
     */

    public SharedCounter3(){
        this(0l);
    }

    public SharedCounter3(long count) {
        if(count <0){
            throw new IllegalArgumentException("count > 0 ");
        }
        this.count = count;
        /*TODO#1-1 ReentrantLock 생성 합니다.( mutex는 동시에 하나의 Thread만 접근할 수 있습니다. )
            ReentrantLock은 기본적으로 비공정한 락 입니다. 공정성을 보장 하도록 초기화 합니다.
         */
        mutex = new ReentrantLock(true); // 비공정한 락
    }

    public long getCount(){
        /*TODO#1-2 count 를 반환 합니다.
            mutex.lock()를 호출하여 다른 thread가 접근할 수 없도록 lock을 걸어 줍니다.
            쓰레드가 작업이 완료되면
            mutex.unlock()를 호출하여
            잠금을 해제 합니다. 뮤텍스는 lock을 건 쓰레드만 lock을 해제할 수 있습니다.
         */
        try {
            mutex.lock(); // 뮤텍스로 다른 스레드들이 들어올 수 없도록 잠군다.
            return count;
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException();
        } finally {
            mutex.unlock(); // 뮤텍스의 잠금을 풀어줘서 다른 스레드가 접근이 가능하도록 해준다.
        }
    }

    public long increaseAndGet(){
        /* TODO#1-3 count = count + 1 증가시키고 count를 반환 합니다.
            1-2 처럼 mutex를 이용해서 동기화 될 수 있도록 구현 합니다.
        */

        try {
            mutex.lock(); // 뮤텍스로 다른 스레드들이 들어올 수 없도록 잠군다.
            return ++count;
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException();
        } finally {
            mutex.unlock(); // 뮤텍스의 잠금을 풀어줘서 다른 스레드가 접근이 가능하도록 해준다.
        }
    }

    public long decreaseAndGet(){
        /*TODO#1-4 count = count-1 감소시키고 count를 반환 합니다.
            1-2 처럼 mutex를 이용해서 동기화 될 수 있도록 구현 합니다.
        */
        try {
            mutex.lock(); // 뮤텍스로 다른 스레드들이 들어올 수 없도록 잠군다.
            return --count;
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException();
        } finally {
            mutex.unlock(); // 뮤텍스의 잠금을 풀어줘서 다른 스레드가 접근이 가능하도록 해준다.
        }
    }
}
