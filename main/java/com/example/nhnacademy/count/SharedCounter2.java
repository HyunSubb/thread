package com.example.nhnacademy.count;

import java.util.concurrent.Semaphore;

public class SharedCounter2 {
private long count;
    private Semaphore semaphore;

    public SharedCounter2(){
        count =0l;
    }
    /*
     * Semaphore 클래스는 스레드 간의 동기화를 위해 사용된다. Semaphore는 주어진 수의 허가(permits)를 관리하며,
     * 스레드가 자원을 사용할 수 있도록 허가를 요청하고 반환하는 매커니즘을 제공한다.
     * (주어진 수의 허가 : 동시에 여러 쓰레드가 임계영역에 접근할 수 있음.)
     * 
     *  acquire() - 스레드가 허가를 얻으려고 할 때 호출된다.
     *      
     *      허가 획득 시도 : acquire() 메서드는 허가를 획득하려고 시도한다. 만약 허가가 남아 있다면,
     *              즉시 허가를 획득하고 메서드를 반환한다.
     *  
     *      허가 대기 : 만약 허가가 남아 있지 않다면, 스레드는 허가가 반환될 때까지 대기 상태에 들어간다.
     *              다른 스레드가 허가를 반환하면, 대기 중인 스레드 중 하나가 허가를 획득하고 메서드를 반환한다.
     *  
     *  release() - 스레드가 허가를 반환할 때 호출된다.
     *      
     *      스레드가 자원을 사용한 후 허가(permit)를 반환할 때 호출된다.
     *      
     *      허가 반환 : release() 메서드는 현재 스레드가 가지고 있는 허가를 반환한다. 이로 인해 Semaphore의 허가 수가
     *               증가한다.
     *      대기 중인 스레드 알림 : 만약 다른 스레드가 허가를 기다리고 있다면, release() 메서드는 대기 중인 스레드 중
     *                          하나를 깨워서 허가를 획득할 수 있도록 한다.
     */

    public SharedCounter2(long count) {
        if(count <0){
            throw new IllegalArgumentException("count > 0 ");
        }
        this.count = count;
        //TODO#1-1 semaphore를 생성 합니다.( 동시에 하나의 Thread만 접근할 수 있습니다. ), permits prameter를 확인하세요.
        semaphore = new Semaphore(1); 
        // 1을 줬다는 건 "현재 자원을 사용할 수 있는 허가를 1개 가지고 있다"라는 의미다.
        // 세마포어 값이 0보다 크면 자원을 사용할 수 있는 허가가 있다는 뜻이다. 
        // 값이 0이면 모든 자원이 사용 중이므로, 새로운 프로세스는 자원을 사용하기 위해 기다려야 한다.
        // Semaphore(1)의 경우: 초기값이 1이므로, 한 번에 한 개의 프로세스만 자원을 사용할 수 있습니다. 
        // 이는 뮤텍스(mutex)와 비슷한 기능을 제공하며, 주로 상호 배제를 구현하는 데 사용됩니다.

    }

    public long getCount(){
        /*TODO#1-2 count 를 반환 합니다.
            semaphore.acquire()를 호출하여 허가를 획득 합니다.
            쓰레드가 작업이 완료되면
            semaphore.release()를 호출하여
            허가를 반환 합니다.
         */
        try {
            semaphore.acquire(); // 자원 사용에 대한 허가 획득 -> 해당 스레드에게 허가증을 부여
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release(); // 세마포어가 release()로 허가를 반환한다.
        }

        return count;
    }

    public long increaseAndGet(){
        /* TODO#1-3 count = count + 1 증가시키고 count를 반환 합니다.
            1-2 처럼 semaphore를 이용해서 동기화할 수 있도록 구현 합니다.
        */
        try {
            semaphore.acquire(); // 자원 사용에 대한 허가 획득 -> 해당 스레드에게 허가증을 부여
            count = count + 1;
            return count;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release(); // 자원 사용에 대한 허가 반환 -> 해당 스레드에게서 허가증을 가져옴
        }
    }

    public long decreaseAndGet(){
        /*TODO#1-4 count = count-1 감소시키고 count를 반환 합니다.
            1-2 처럼 semaphore를 이용해서 동기화할 수 있도록 구현 합니다.
        */
        try {
            semaphore.acquire(); // 자원 사용에 대한 허가 획득 -> 해당 스레드에게 허가증을 부여
            count = count - 1;
            return count;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            semaphore.release(); // 자원 사용에 대한 허가 반환 -> 해당 스레드에게서 허가증을 가져옴
        }
    }
}
