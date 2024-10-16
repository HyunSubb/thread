package com.example.nhnacademy.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CounterHandler9 implements Runnable{
    private final Object monitor;
    private final long countMaxSize;

    private long count;

    public CounterHandler9(long countMaxSize, Object monitor) {
        //TODO#4 countMaxSize<=0 or monitor 객체가 null 이면 IllegalArgumentException이 발생 합니다.
        if(countMaxSize <= 0 || monitor == null) {
            throw new IllegalArgumentException();
        }

        //TODO#5  countMaxSize, count, monitor 변수를 초기화 합니다.
        this.countMaxSize = countMaxSize;
        this.monitor = monitor; 
        count = 0;
    }

    @Override
    public void run() {
        // TODO#6 Thread에 의해서 run() method가 호출되면 무한 대기 합니다. monitor객체를 이용해서 구현하세요
        // [monitor는 여러 Thread가 동시에 접근할 수 없도록 접근을 제어]해야 합니다.
        /*
         * 모니터 (Monitor)
         * 
         * 모니터는 자바에서 스레드 간의 동기화를 관리하는 중요한 개념이다.
         * 모니터는 특정 객체에 대한 접근을 제어하여, 동시에 여러 스레드가 동일한 자원에 접근하지 못하도록 한다.
         * 이를 통해 데이터의 일관성을 유지하고, 동시성 문제를 방지할 수 있다.
         * 
         * 상호 배제 (Mutual Exclusion) : 한 번에 하나의 스레드만 모니터에 진입할 수 있다.
         * 다른 스레드들은 모니터가 해제될 때까지 대기한다.
         * 
         * 조건 변수(Condition Variables) : 스레드가 특정 조건을 기다리거나, 다른 스레드에 신호를 보내기 위해 사용한다.
         * 자바에서는 wait(), notify(), notifyAll() 메서드를 통해 구현됩니다.
         * 
         * [제미니]
         * 
         * synchronized 키워드는 자바에서 스레드 간의 동기화를 보장하기 위해 사용되는 중요한 키워드이다.
         * 즉, 여러 스레드가 동시에 [공유 자원]에 접근하려 할 때, 한 번에 하나의 스레드만 해당 자원에 접근하도록
         * 보장하여 데이터의 일관성을 유지한다.
         * 
         * 모니터는 자바 객체와 연관된 잠금 매커니즘이다. 즉, 모든 자바 객체는 내부적으로 모니터를 가지고 있으며,
         * synchronized 키워드를 사용하면 이 모니터를 통해 스레드 간의 동기화를 관리할 수 있다.
         * 
         * [synchronized 블록의 동작 방식]
         * 
         * 1. 모니터 획득 : 스레드가 synchronized 블록에 진입하려고 할 때, 해당 블록과 연관된 모니터를 획득하려고 시도한다.
         * 2. 상호 배제 : 만약 다른 스레드가 이미 모니터를 획득하고 있다면, 현재 스레드는 모니터 획득을 위해 대기한다.
         * 3. 임계 영역 실행 : 모니터를 획득한 스레드만 synchronized 블록 내의 코드를 실행할 수 있다.
         * 4. 모니터 해제 : synchronized 블록을 벗어나면 자동으로 모니터를 해제하여 다른 스레드가 모니터를 획득할 수 있도록 한다.
         * 
         *      synchronized (monitor) {
                    // ...
                }

            위 코드에서 synchronized 블록은 monitor 객체를 잠금(lock)으로 사용한다. 
            즉, 여러 스레드가 동시에 이 블록에 진입하려 할 때, 한 번에 하나의 스레드만 진입할 수 있다.

            monitor.wait() : 이 메서드는 현재 스레드를 모니터의 대기 큐에 넣고, 모니터를 해제한다. 
            다른 스레드가 notify() 또는 notifyAll() 메서드를 호출하여 해당 스레드를 깨울 때까지 대기한다.

            monitor.notify(): 모니터의 대기 큐에서 하나의 스레드를 임의로 깨워 실행을 허용한다.

            monitor.notifyAll(): 모니터의 대기 큐에 있는 모든 스레드를 깨워 실행을 허용한다.

            [요약]
            synchronized: 스레드 간의 동기화를 보장하는 키워드
            모니터: 자바 객체와 연관된 잠금 메커니즘
            synchronized 블록: 모니터를 획득하여 상호 배제를 구현하는 코드 블록
         */

        synchronized (monitor) {
            try {
                monitor.wait();
                // 다른 스레드에서 notify() 메서드를 호출할 때까지 현재 스레드를 대기 상태로 만든다.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
            log.debug("thread:{},state:{},count:{}",Thread.currentThread().getName(),Thread.currentThread().getState(),count);

        }while (count<countMaxSize);
    }
}
