package com.example.nhnacademy.thread;

import com.example.nhnacademy.count.SharedCounter;
import com.example.nhnacademy.count.SharedCounter2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CounterIncreaseHandler implements Runnable{
    private SharedCounter sharedCounter;

    public CounterIncreaseHandler(SharedCounter sharedCounter) {
        //TODO#2-1 sharedCounter를 초기화 합니다.  sharedCounter가 null 이면 IllegalArgumentException이 발생 합니다.
        if(sharedCounter == null) {
            throw new IllegalArgumentException();
        }
        this.sharedCounter = sharedCounter;
    }

    @Override
    public void run() {
        //TODO#2-2 현재 Thread의 interrupted이 ture <--  while의 종료조건 : interrupt가 발생 했다면 종료 합니다.
        while(!(Thread.currentThread().isInterrupted())) {
            try {
                Thread.sleep(1000);
                //TODO 2-3 sharedCounter의 count를 1증가 시키고 count값을 반환 합니다.
                // long count = sharedCounter.increaseAndGet();
                long count = sharedCounter.increaseAndGet();

                log.debug("thread:{}, count:{}", Thread.currentThread().getName(), count);
            } catch (Exception e) {
                log.debug("{} - interrupt!",Thread.currentThread().getName());

                //TODO#2-4 현제 Thread에 interrupt()를 호출하여 interrput()를 발생 시킵 니다. 즉 현제 Thread의 interrupted 값이 -> true로 변경 됩니다. -> 즉 while 문을 종료하게 됩니다.
                Thread.currentThread().interrupt();
            }
        }
    }
}
