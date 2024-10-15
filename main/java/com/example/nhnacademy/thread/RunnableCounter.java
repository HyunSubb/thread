package com.example.nhnacademy.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//TODO#1 Runnable interface을 implements(구현) 합니다.
public class RunnableCounter implements Runnable{
    private final long countMaxSize;

    private long count;

    public RunnableCounter(long countMaxSize) {
        //TODO#2 countMaxSize <=0 이면 IllegalArgumentException()이 발생 합니다.
        if(countMaxSize <= 0) {
            throw new IllegalArgumentException();
        }

        this.countMaxSize = countMaxSize;
        this.count=0l;
    }

    @Override
    public void run() {
        /* TODO#3 run method를 구현 합니다.
                - 1초에 한 번식 다음과 같이 출력 됩니다.
                - count 1 ~ 10 까지 출력 됩니다.
            ex) thread:my-thread,count:1 ....
         */
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
            log.info("thread : {} , count {}", Thread.currentThread().getName(), count);
        }while (count < countMaxSize);
    }
}
