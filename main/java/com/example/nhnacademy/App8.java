package com.example.nhnacademy;

import com.example.nhnacademy.thread.RunnableCounter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App8 {
    public static void main(String[] args) {
        //counterHandlerA 객체를 생성 합니다. countMaxSize : 10
        RunnableCounter counterHandlerA = new RunnableCounter(10l);
        //threadA 생성시 counterHandlerA 객체를 paramter로 전달 합니다.
        Thread threadA = new Thread(counterHandlerA);
        //threadA의 name을 'my-counter-A' 로 설정 합니다.
        threadA.setName("my-counter-A");
        log.debug("threadA-state:{}",threadA.getState());

        //counterHandlerB 객체를 생성 합니다. countMaxSize : 10
        RunnableCounter counterHandlerB = new RunnableCounter(10l);
        //threadB 생성시 counterHandlerB 객체를 paramter로 전달 합니다.
        Thread threadB = new Thread(counterHandlerB);
        //threadB의 name을 'my-counter-B' 로 설정 합니다.
        threadB.setName("my-counter-B");
        log.debug("threadB-state:{}",threadB.getState());

        //threadA를 시작 합니다.
        threadA.start();
        log.debug("threadA-state:{}",threadA.getState());

        //threadB를 시작 합니다.
        threadB.start();
        log.debug("threadB-state:{}",threadB.getState());

        //TODO#1 - main Thread 에서 3초 후  threadA에 interrupt 예외를 발생 시킴 니다.
        try {
            Thread.sleep(3000);
            threadA.interrupt();
            /*
             * interrupt()는 스레드에게 종료를 요청하고 스레드가 정상적으로 종료될 수 있도록 한다.
             */
        } catch (Exception e) {
            // TODO: handle exception
            log.error("interrupt 발생");
        }

        //TODO#3 Main Thread가 threadA, ThreadB가 종료될 때 까지 대기 합니다. Thread.yield를 사용 합니다.
        while(threadA.isAlive() || threadB.isAlive()) {
            Thread.yield();
        }

        //threadA, threadB 상태를 출력 합니다.
        log.debug("threadA-status:{}",threadA.getState());
        log.debug("threadB-status:{}",threadB.getState());

        //main thread 종료, 'Application exit!' message를 출력 합니다.
        log.debug("Application exit!");
    }
}
