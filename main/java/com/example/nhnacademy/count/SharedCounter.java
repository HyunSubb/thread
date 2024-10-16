package com.example.nhnacademy.count;

public class SharedCounter {
    private long count;

    public SharedCounter(){
        count =0l;
    }

    public SharedCounter(long count) {
        //TODO#1-1 생성자를 초기화 합니다. count < 0 IllegalArgumentException아 발생 합니다.
        if(count < 0) {
            throw new IllegalArgumentException();
        }
        this.count = 0l;

    }

    public long getCount(){
        //TODO#1-2 count 를 반환 합니다.

        return count;
    }

    public long increaseAndGet(){
        //TODO#1-3 count = count + 1 증가시키고 count를 반환 합니다.

        return ++count;
    }

    public long decreaseAndGet(){
        //TODO#1-4 count = count-1 감소시키고 count를 반환 합니다.

        return --count;
    }
}
