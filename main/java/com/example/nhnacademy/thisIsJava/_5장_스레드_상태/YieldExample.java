package com.example.nhnacademy.thisIsJava._5장_스레드_상태;

public class YieldExample {
    public static void main(String[] args) {
        WorkThread workThreadA = new WorkThread("workThreadA");
        WorkThread workThreadB = new WorkThread("workThreadB");
        workThreadA.start();
        workThreadB.start();

        try {
            // 3초뒤에는 workThreadA가 실행을 하지 않고 B에게 넘김.
            Thread.sleep(3000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        workThreadA.work = false;

        try {
            // 6초뒤에는 workThreadA가 다시 실행을 할 수 있도록 바꿈.
            Thread.sleep(6000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        workThreadA.work = true;
    }
}
