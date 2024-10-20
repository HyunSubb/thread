package com.example.nhnacademy.thisIsJava._5장_스레드_상태;

public class WorkThread extends Thread{
    
    public boolean work = true;

    public WorkThread(String name) {
        setName(name);
    }

    @Override
    public void run() {
        while(true) {
            if(work) {
                System.out.println(getName() + "작업처리");
            } else {
                Thread.yield();
                // 현재 스레드가 더 이상 실행되지 않고 실행을 다른 스레드에게 양보를 한다.
            }
        }
    }
}
