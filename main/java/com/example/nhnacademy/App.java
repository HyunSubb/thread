/*
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright 2024. NHN Academy Corp. All rights reserved.
 * + * While every precaution has been taken in the preparation of this resource,  assumes no
 * + responsibility for errors or omissions, or for damages resulting from the use of the information
 * + contained herein
 * + No part of this resource may be reproduced, stored in a retrieval system, or transmitted, in any
 * + form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the
 * + prior written permission.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

package com.example.nhnacademy;

import com.example.nhnacademy.thread.Counter;

/**
     * Hello world!
    *
    */
    public class App 
    {
        public static void main( String[] args ){
            // Thread.currentThread().setName("my-thread");
            Counter counter1 = new Counter(10);
            Counter counter2 = new Counter(10);
            counter1.setName("counter1");
            counter2.setName("counter2");
            // counter1.run(); // run()은 [main 스레드]에서 counter1의 run() 메서드를 그냥 호출한다.
            counter1.start(); // start()는 counter1 기반의 [새로운 스레드]를 생성하고 새로운 스레드에서 run()을 실행시킨다.
            counter1.start(); // start()는 counter1 기반의 [새로운 스레드]를 생성하고 새로운 스레드에서 run()을 실행시킨다.

            counter2.start(); // start()는 counter2 기반의 [새로운 스레드]를 생성하고 새로운 스레드에서 run()을 실행시킨다.
            /*
             * 1. counter1.run() 호출 : run() 메서드는 일반 메서드처럼 호출되므로, 
             * 현재 실행 중인 스레드 (즉, main 스레드)의 컨텍스트에서 실행된다. 
             * 따라서 Thread.currentThread().getName()은 main을 반환합니다.
             * 새로운 스레드를 생성하지 않고, 현재 스레드에서 run() 메서드의 내용이 순차적으로 실행되는 것이다.
             * 
             * 2. counter1.start() 호출 : start() 메서드는 새로운 스레드를 생성하고 그 스레드에서 run() 메서드를 실행한다.
             * 따라서 counter1이라는 이름의 스레드가 생성되어 run() 메서드가 실행되며, 로그에 name : counter1이 출력된다.
             * 
             */
        }
    }