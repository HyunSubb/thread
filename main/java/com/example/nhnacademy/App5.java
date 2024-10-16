package com.example.nhnacademy;

import com.example.nhnacademy.thread.RunnableCounter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App5 {
    public static void main(String[] args) {
        RunnableCounter counterHandler = new RunnableCounter(10l);
        Thread thread = new Thread(counterHandler);
        log.debug("thread-state:{}",thread.getState());
        thread.setName("my-counter");
        thread.start();
        //TODO#1 thread가 실행 후 (1-10 count 증가 후  아래 로그가 출력 됩니다.)
        //thread.join()을 호출 하면 thread가 종료될 때 까지 main thread가 대기하게 됩니다.
        try {
            // thread.join();
            /*
                Thread.join() 메서드는 다른 스레드가 종료될 때까지 현재 스레드의 실행을 일시 정지시키는 역할을 한다.
                쉽게 말해, 특정 스레드의 작업이 완료될 때까지 기다도록 하는 것이다.

                join() : 스레드가 완전히 종료될 때까지 무한정 기다린다.
                join(long millis) : 최대 millis 밀리초 동안 기다린다.

                Thread.join() 메서드는 스레드 간의 실행 순서를 조절하고, 
                프로그램의 흐름을 제어하는 데 사용되는 중요한 메서드이다. 
                스레드 간의 의존 관계를 명확히 하고, 자원 경쟁 상태를 방지하기 위해 적절하게 사용해야 한다.
             */        
        } catch (Exception e) {
            // TODO: handle exception
        }
    
        log.debug("Application exit!");
        log.debug("thread-state:{}",thread.getState());
    }

}
