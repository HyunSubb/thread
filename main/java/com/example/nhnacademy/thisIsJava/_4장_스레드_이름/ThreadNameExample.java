package com.example.nhnacademy.thisIsJava._4장_스레드_이름;

public class ThreadNameExample {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread(); // 지금 실행중인 스레드가 뭔지 알아보기.
        System.out.println(mainThread.getName() + "실행"); // getName()으로 실행중인 스레드의 이름을 확인.

        for(int i = 0; i < 3; i++) {
            // 작업 생성 0 1 2
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                }
            });
            System.out.println(thread.getName());
        }

        // 작업 생성 chat-thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });
        thread.setName("chat-thread");
        System.out.println(thread.getName());
    }
}
