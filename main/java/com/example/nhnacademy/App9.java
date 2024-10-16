package com.example.nhnacademy;

import com.example.nhnacademy.thread.CounterHandler9;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App9 {
    //TODO#1 monitor로 사용한 객체를 생성 합니다.
    public static Object monitor = new Object(); // monitor는 잠금 역할을 한다.

    public static void main( String[] args )
    {

        //TODO#2 counterHandlerA 객체를 생성 합니다. countMaxSize : 10, monitor
        CounterHandler9 counterHandlerA = new CounterHandler9(10l, monitor);

        //threadA 생성시 counterHandlerA 객체를 paramter로 전달 합니다.
        Thread threadA = new Thread(counterHandlerA);

        //threadA의 name을 'my-counter-A' 로 설정 합니다.
        threadA.setName("my-counter-A");
        log.debug("threadA-state:{}",threadA.getState()); // threadA-state:NEW

        //threadA를 시작 합니다.
        threadA.start(); // start()되면 run() 내부 메서드 실행하고 wait()있어서 대기 상태가 된다.
        log.debug("threadA-state:{}",threadA.getState()); // threadA-state:RUNNABLE

        //TODO#2 - Main Thread에서 2초 후
        try {
            Thread.sleep(2000);
            System.out.println("2초 지남");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*
         *  synchronized는 여러 사람이 동시에 하나의 방에 들어가는 것을 막는 문과 같습니다. 
         *  한 번에 한 사람만 방에 들어갈 수 있도록 합니다. monitor는 이 문의 역할을 하는 객체입니다.

            코드에서 synchronized (monitor) { ... } 부분은 "이 문을 열고 방에 들어가서 작업을 하고, 
            다른 사람이 들어오려고 할 때까지 기다린 후 문을 닫고 나가세요"라고 말하는 것과 같습니다.

            멀티 쓰레드 프로세스에서는 다른 쓰레드의 작업에 영향을 미칠 수 있습니다. 
            그렇기 때문에 진행중인 작업이 다른 쓰레드에게 간섭받지 않게 하려면 동기화가 필요합니다. 
            동기화를 하려면 간섭받지 않아야 하는 문장들을 임계 영역으로 설정해주면 된다. 
            임계 영역은 락(lock)을 얻은 단 하나의 쓰레드만 출입할 수 있다. (객체 1개에 락 1개)
         */

        // monitor를 이용하여 대기하고 있는 threadA를 깨웁니다.
        // monitor를 잠근 후 notify() 메서드를 호출하여 CounterHandler9 스레드를 깨운다.
        /*
         * synchronized 동기화 블록 내에서만 wait()와 notify()를 사용할 수 있다.
         */
        synchronized (monitor){ // monitor는 잠금 역할을 한다.
            // 이 블록 내의 코드는 한 번에 하나의 스레드만 실행할 수 있다.
            log.debug("2초 지난 상태에서 깨우기 call monitor.notify()");
            monitor.notify();
            // threadA를 깨운다. 
            // (사실 대기 중인 스레드 중 임의의 하나를 깨우는 것이다. / 깨어난 스레드는 다시 synchronized 블록에 진입을 하기 위해 경쟁한다.)
            /*
             * monitor.notify()는 Thread.sleep()으로 잠든 스레드를 직접 깨우는 것이 아니라, 
             * wait() 메서드를 통해 특정 조건에서 대기하고 있는 스레드를 깨우는 메커니즘입니다.
             */
        }

        // Main Thread가 threadA 종료될 때 까지 대기 합니다. Thread.yield를 사용 합니다.
        do {
            Thread.yield(); // Thread.yield()를 호출하면 우선순위가 높은 스레드에게 양보할 수 있도록 해준다.
        }while (threadA.isAlive());

        //'Application exit!' message를 출력 합니다.
        log.debug("Application exit!");

    }
}
