package com.example.nhnacademy;

import com.example.nhnacademy.count.SharedCounter;
import com.example.nhnacademy.thread.CounterIncreaseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App2_1 {
    public static void main(String[] args) {
        //TODO#1 shardCounter 객체를 0으로 초기화 합니다.
        // SharedCounter sharedCounter = new SharedCounter();
        SharedCounter sharedCounter = new SharedCounter();

        //TODO#2 counterIncreaseHandler 객체를 생성 합니다.
        CounterIncreaseHandler counterIncreaseHandler = new CounterIncreaseHandler(sharedCounter);

        //TODO#3 counterIncreaseHandler를 이용해서 threadA를 생성 합니다.
        Thread threadA = new Thread(counterIncreaseHandler);

        //TODO#4 threadA의 thread name을 "thread-A"로 설정 합니다.
        threadA.setName("thread-A");
        //TODO#5 threadA를 시작 합니다.
        threadA.start();

        //TODO#6 counterIncreaseHandler를 이용해서 threadB를 생성 합니다.
        Thread threadB = new Thread(counterIncreaseHandler);

        //TODO#7 threadB의 name을 'thread-B' 로 설정 합니다.
        threadB.setName("thread-B");
        //TODO#8 threadB를 시작 합니다.
        threadB.start();
        /*
         * threadA와 threadB는 공통의 자원을 사용한다.
         * 둘 이상의 thread가 자원을 공유하는 것을 Race Condition(경쟁 조건)이라고 한다.
         * 
         * 이 처럼 하나 이상의 thread가 경쟁하는 문제를 race condition이라고 하고, 동시 접근을 함으로써
         * 문제가 발생할 수 있는 구역을 Critical Section(임계 구역)이라고 한다.
         * 
         * Mutual exclusion(상호 배제)이란 두 개 이상의 process 혹은 thread가 동시에 하나의 공유 자원으로
         * 발생할 수 있는 race Condition 문제를 해결하기 위해 어느 시점에서의 
         * [ 공유 자원 접근을 하나의 process 혹은 thread로 제한하는 것을 말한다. ]
         */
        //TODO#9 main thread가 실행 후 20초 후 threadA, threadB 종료될 수 있도록 interrupt 발생 시킵니다.
        try {
            Thread.sleep(20000);
            threadA.interrupt();
            threadB.interrupt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //TODO#10 main Thread는 threadA와 threadB의 상태가 terminated가 될 때 까지 대기 합니다. 즉 threadA, threadB가 종료될 때 까지 대기(양보) 합니다.
        while(threadA.isAlive() && threadB.isAlive()) {
            Thread.yield(); // 최우선 스레드에게 양보를 한다.
            // 밑에거는 양보 다 끝나고 실행됨.
        }

        log.debug("System exit!");
    }
}
