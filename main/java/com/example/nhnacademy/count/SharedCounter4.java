package com.example.nhnacademy.count;

public class SharedCounter4 {
    private long count;

    public SharedCounter4(){
        this(0l);
    }

    public SharedCounter4(long count) {
        //TODO#1-1 생성자를 초기화 합니다. count < 0 IllegalArgumentException아 발생 합니다.
        if(count < 0) {
            throw new IllegalArgumentException();
        }
        this.count = count;
    }

    //TODO#1-2 mehtod 단위 lock을 걸고, count 를 반환 합니다.
    public synchronized long getCount(){
        /*
         * synchronized 키워드의 역할
                Java에서 synchronized 키워드는 멀티 스레드 환경에서 공유 자원에 대한 접근을 동기화시키는 데 사용됩니다. 
                즉, 여러 스레드가 동시에 같은 자원에 접근하려 할 때, 한 번에 하나의 스레드만 자원에 접근하도록 보장하여 
                데이터의 일관성을 유지합니다.

            뮤텍스, synchronized, 세마포어(1) 비교: 핵심은 동일, 뉘앙스는 다름
                결론부터 말씀드리면, 뮤텍스, synchronized 키워드, 그리고 세마포어를 1로 설정한 경우는 
                모두 여러 스레드가 동시에 특정 코드 블록에 접근하는 것을 막고, 한 번에 하나의 스레드만 실행되도록 하는, 
                즉 상호 배제(mutual exclusion)를 구현한다는 점에서 동일한 목적을 가집니다.
         */
        return count;
    }

    public synchronized long increaseAndGet(){
        //TODO#1-3 block 단위로 lock을 걸고 count = count + 1 증가시키고 count를 반환 합니다.
        count = count + 1;
        return count;
    }

    public synchronized long decreaseAndGet(){
        //TODO#1-4 count = count -1  부분 lock을 걸고, count를 반환 합니다.
        count = count - 1;
        return count;
    }
}
