package com.example.nhnacademy.thisIsJava._3장_작업_스레드_생성과_실행;

import java.awt.*;

public class BeepPrintExample2 {
    public static void main(String[] args) {

        // 작업 1
        Thread thread = new Thread(new Runnable() { // 작업 스레드를 새로 생성 함.
            @Override
            public void run() {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
				for (int i = 0; i < 5; i++) {
					toolkit.beep();
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
            }
        });

        thread.start(); // 작업 스레드를 실행한다. -> new Runnable에서 구현한 run() 메서드를 start() 메서드로 시작한다.

        // 작업 2
        for (int i = 0; i < 5; i++) {
			System.out.println("띵");
			try {
				Thread.sleep(500); // 0.5초간 일시정지
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

        // 작업 1과 작업 2가 동시에 동작한다.
        // 출력문은 메인문이 담당하고 비프음을 들려주는 것은 작업 스레드가 담당하도록 만들었음.
    }
}
