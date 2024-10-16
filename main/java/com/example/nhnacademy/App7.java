package com.example.nhnacademy;

import com.example.nhnacademy.thread.RunnableCounter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App7 {
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

        //TODO#1 Main Thread가 threadA, ThreadB가 종료될 때 까지 대기 합니다. Thread.yield를 사용 합니다.
        while(threadA.isAlive() || threadB.isAlive()) {
            /*
             * isAlive() 메서드는 스레드가 현재 실행 중인지 여부를 판단하는 데 사용한다.
             * 즉, 스레드가 생성되어 실행 중인 상태인지, 아니면 이미 종료되었는지를 확인하는 메서드이다.
             * 
             * [반환값]
             * 스레드가 실행 중인 상태 - true 
             * 스레드가 생성되지 않았거나 이미 종료된 상태 : false
             * 
             * yield() 메서드는 현재 실행 중인 스레드가 스케줄러에게 실행 기회를 양보하는 역할을 한다.
             * 즉, 다른 스레드가 CPU를 사용할 수 있도록 현재 스레드가 자발적으로 실행을 잠시 멈추는 것이다.
             * 
             * join() 메서드는 다른 스레드가 완료될 때까지 실행을 멈추고 기다리는 역할을 하는 것이고,
             * yield() 메서드는 현재 실행 중인 스레드가 스케줄러에게 실행 기회를 양보하여 다른 스레드가 CPU를 사용할 수
             * 있도록 하는 역할을 한다.
             * 
             * 핵심 차이점:

                **join()**은 다른 스레드의 종료를 기다리는 반면, 
                **yield()**는 현재 스레드가 임시적으로 실행을 멈추고 다른 스레드에게 실행 기회를 주는 것입니다.
                **join()**은 스레드 간의 의존 관계를 나타내는 반면, 
                **yield()**는 스레드 간의 협력을 위한 메커니즘입니다.
             */
            Thread.yield();
            /*
             * Thread.yield()를 사용하는 이유는 main 스레드가 threadA와 threadB가 종료될 때까지 계속해서 실행되면서, 
             * 다른 스레드들에게도 CPU 시간을 할당할 수 있도로 하기 위함이다. 
             * 
             * yield() 메소드를 호출한 스레드는 실행 대기 상태로 돌아가고 
             * [동일한 우선순위 또는 높은 우선순위를 갖는 다른 스레드가 실행 기회를 가질 수 있도록] 해준다.
             * 
             * Thread.yield()를 호출하면 우선순위가 높은 스레드에게 양보할 수 있도록 해준다.
             */
        }

        // threadA, threadB가 종료되면 'Application exit!' message를 출력 합니다.
        log.debug("Application exit!");
    }
}
