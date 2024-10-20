package com.example.nhnacademy.thisIsJava._5장_스레드_상태;

public class JoinExample {
    public static void main(String[] args) {
        SumThread sumThread = new SumThread(); // 스레드 생성
        sumThread.start(); // 스레드 시작 -> 1 ~ 100까지 더한다.

        try {
            sumThread.join(); // sumThread가 끝날 때 까지 현재 메서드가 기다린다.
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println("1 ~ 100 까지의 합 : " + sumThread.getSum());
    }
}
