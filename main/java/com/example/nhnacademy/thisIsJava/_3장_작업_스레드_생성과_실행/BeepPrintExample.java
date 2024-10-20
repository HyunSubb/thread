package com.example.nhnacademy.thisIsJava._3장_작업_스레드_생성과_실행;

import java.awt.*;
public class BeepPrintExample {
    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // main 스레드는 두 가지 작업을 처리할 수 없다. 소리를 낸 다음 출력문을 실행한다.

        // 작업 1
        for (int i = 0; i < 5; i++) {
			toolkit.beep();
			try {
				Thread.sleep(500); // 0.5초 일시정지
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

        // 작업 2
		for (int i = 0; i < 5; i++) {
			System.out.println("띵");
			try {
				Thread.sleep(500); // 0.5초간 일시정지
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
    }
}
