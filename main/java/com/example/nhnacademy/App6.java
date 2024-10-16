package com.example.nhnacademy;

import com.example.nhnacademy.thread.RunnableCounter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App6 {
    public static void main(String[] args) {
        //TODO#1 counterHandlerA 객체를 생성 합니다. countMaxSize : 10
        RunnableCounter counterHandlerA = new RunnableCounter(10);
        //TODO#2 threadA 생성시 counterHandlerA 객체를 paramter로 전달 합니다.
        Thread threadA = new Thread(counterHandlerA);
        //TODO#3 threadA의 name을 'my-counter-A' 로 설정 합니다.
        threadA.setName("my-counter-A");

        log.debug("threadA-state:{}",threadA.getState());


        //TODO#4 counterHandlerB 객체를 생성 합니다. countMaxSize : 10
        RunnableCounter counterHandlerB = new RunnableCounter(10);
        //TODO#5 threadB 생성시 counterHandlerB 객체를 paramter로 전달 합니다.
        Thread threadB = new Thread(counterHandlerB);
        //TODO#6 threadB의 name을 'my-counter-B' 로 설정 합니다.
        threadB.setName("my-counter-B");

        log.debug("threadB-state:{}",threadB.getState());

        //TODO#7 threadA를 시작 합니다.
        threadA.start();

        //TODO#8 threadA 작업이 완료될 때까지 main Thread는 대기 합니다.
        try {
            threadA.join();
        } catch (Exception e) {
            // TODO: handle exception
        }
        log.debug("threadA-state:{}",threadA.getState());

        //TODO#9 threadB를 시작 합니다.
        threadB.start();

        //TODO#10 threadB 작업이 완료될 때까지 main Thread는 대기 합니다.
        try {
            threadB.join(); // 스레드B가 종료될 때 까지 기다린다.
        } catch (Exception e) {
            // TODO: handle exception
        }

        log.debug("threadB-state:{}",threadB.getState());
        /*
         * threadB의 상태는 terminated 되었다고 출력된다.
         * 
         * run() 메서드가 종료되면 일반적으로는 대기 상태로 전환된다. 
         * 이후 JVM의 가비지 컬렉터에 의해 메모리에서 회수될 수 있는 대상이 되지만, 바로 회수되지 않을 수도 있다.
         * 
         * join() 메서드의 역할
         * 
         *  - 스레드 종료 기다리기 : 다른 스레드가 종료될 때까지 기다리므로, 스레드가 사용하던 자원을 
         * 안전하게 해제할 수 있는 시간을 확보한다.
         * 
         * JVM은 스레드가 사용하던 자원을 안전하게 해제할 수 있도록 충분한 시간을 주기 위해서 
         * 스레드 객체를 바로 회수하지 않을 수 있다.
         * 
         * [핵심]
         * 
         *  - 스레드는 run() 메서드 종료 후 대기 상태로 전환
         *  - 가비지 컬렉터에 의해 회수되지만, 바로 회수되지 않을 수가 있다.
         *  - join() 메서드를 사용하여 스레드 종료를 기다리고 자원 해제.
         *  - 스레드 안전한 종료를 위해 적절한 예외 처리 필요.
         */

        //TODO#11 'Application exit!' message를 출력 합니다.
        log.info("Application exit!");
    }
}
