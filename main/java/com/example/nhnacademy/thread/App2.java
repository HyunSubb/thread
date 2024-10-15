package com.example.nhnacademy.thread;

public class App2 {
    public static void main(String[] args) {
            //TODO#4 CounterHandler 객체를 생성 합니다. countMaxSize : 10
            RunnableCounter counterHandler = new RunnableCounter(10);

            //TODO#5 thread 생성시 counterHandler 객체를 paramter로 전달 합니다.
            Thread thread = new Thread(counterHandler);
    
            //TODO#6 thread의 name을 my-counter로 설정 합니다.
            thread.setName("mv-counter");
    
            //TODO#7 thread를 시작 합니다.
            thread.start();
    }
}
