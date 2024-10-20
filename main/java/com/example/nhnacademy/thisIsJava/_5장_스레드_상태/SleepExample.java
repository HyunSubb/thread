package com.example.nhnacademy.thisIsJava._5장_스레드_상태;

public class SleepExample {
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            System.out.println(i + "번!");
            try {
                Thread.sleep(1000); // 1초 일시 정지
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
