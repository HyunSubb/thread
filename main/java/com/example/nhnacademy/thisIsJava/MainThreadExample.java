package com.example.nhnacademy.thisIsJava;

public class MainThreadExample {
    public static void main(String[] args) {
        System.out.println("시작");

        Thread currThread = null;
        System.out.println(currThread.getName()); 
        // Exception in thread "main" java.lang.NullPointerException: 이 코드는 이런 오류가 뜨는데 main이라는 이름을 가진 thread에서 nullPointerException이 발생한다는 의미다.

        // Thread currThread = Thread.currentThread();
        // System.out.println(currThread.getName()); // currThread의 이름은 main이다. -> thread는 이름을 가지고 있다. 기본 스레드는 main 스레드임.
        // 모든 자바 프로그램은 메인 스레드가 main() 메소드를 실행하면서 시작된다.

        System.out.println("종료");
    }
}
