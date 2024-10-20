package com.example.nhnacademy.thisIsJava._5장_스레드_상태;

public class SumThread extends Thread{
    
    private long sum;

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public void run() {
        for(int i = 1; i <= 100; i++) {
            sum += i;
        }
    }
} 
