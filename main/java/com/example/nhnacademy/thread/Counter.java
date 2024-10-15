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

    package com.example.nhnacademy.thread;

    import lombok.extern.slf4j.Slf4j;
    
    @Slf4j
    public class Counter extends Thread{
        private final long countMaxSize;
        private long count;
    
        public Counter(long countMaxSize) {
            //TODO#1 countMaxSize < 0 작다면 IllegalArgumentException 예외가 발생 합니다.
            if(countMaxSize < 0) {
                throw new IllegalArgumentException();
            }
            //TODO#2 this.countMaxSize 초기화 합니다.
            this.countMaxSize = countMaxSize;
            //TODO#3 this.count 값을 0으로 초기화 합니다.
            this.count = 0;
        }
    
        @Override
        public void run() {
            /*  
                run() 메서드
                - 스레드가 수행할 작업을 정의 : run() 메서드는 스레드가 실제로 수행해야 할 작업을 담고 있는 메서드이다.
                프로그램의 메인 실행 함수와 비슷한 역할을 한다.

                - 단순 메서드 호출 : run() 메서드를 직접 호출하면 일반 메서드처럼 순차적으로 실행된다.
                즉, 새로운 스레드가 생성되지 않고 현재 스레드에서 run() 메서드의 코드가 실행된다.

                start() 메서드
                - 새로운 스레드 생성 및 시작 : start() 메서드는 새로운 스레드를 생성하고, 생성된 스레드에서 run() 메서드를
                실행하도록 한다.

                - 비동기 실행 : start() 메서드를 호출하면 현재 스레드와 새로 생성된 스레드가 독립적으로 동시에 실행된다.

                왜 start()를 사용해야 할까??

                    멀티스레딩의 목적 달성 : start() 메서드를 사용해야 비로소 여러 작업을 동시에 수행하는 멀티스레딩 환경을
                    구축할 수 있다.

                    독립적인 실행 : 각 스레드는 독립적인 스택과 프로그램 카운터를 가지므로, 다른 스레드의 실행에 영향을 받지
                    않고 동시에 실행될 수 있다.
                
                    thread.run(); // 이렇게 하면 새로운 스레드가 생성되지 않음
                    thread.start(); // 새로운 스레드를 생성하고 실행
                    결론적으로, start() 메서드를 사용해야 비로소 진정한 멀티 스레딩 환경을 구축하고,
                    여러 작업을 동시에 수행할 수 있다.
                
                run() 메서드를 여러 번 호출하면 어떻게 될까??

                    - run() 메서드를 여러 번 호출해도 새로운 스레드가 생성되지 않고, 단순히 메서드가 다시 실행될 뿐입니다.
                    - start() 메서드는 한 번만 호출해야 합니다. 여러 번 호출하면 IllegalThreadStateException 예외가 발생합니다.
                
                start() 메서드를 한 번만 호출해줘야 하는 이유

                    스레드 생명주기 관리: 스레드의 생명주기를 명확하게 관리하기 위해서입니다.
                    예상치 못한 동작 방지: 스레드를 여러 번 시작하면 프로그램의 동작이 예상치 못하게 될 수 있습니다.
                    자원 낭비 방지: 불필요한 스레드 생성을 방지하여 시스템 자원을 효율적으로 사용할 수 있습니다.
                    
                    start() 메서드는 스레드를 한 번만 시작: 스레드는 생성 후 한 번만 시작될 수 있습니다.
                    여러 번 호출 시 예외 발생: IllegalThreadStateException 예외가 발생합니다.
                    스레드 생명주기 관리 중요: 스레드의 생명주기를 명확하게 관리해야 프로그램의 안정성을 확보할 수 있습니다.

                start()를 여러 번 사용하려면 여러 개의 스레드를 생성한 후에 start() 메서드를 호출해주면 된다.
                ex)
                    for (int i = 0; i < 10; i++) {
                        Thread thread = new Thread(new MyRunnable());
                        thread.start();
                    }


            */
            // do {
            //     /*TODO#4 1초 간격 으로 count++ 됩니다.
            //         Thread.sleep method를 사용하세요.
            //         https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Thread.html#sleep(java.time.Duration)
            //     */
            //     try {
            //         Thread.sleep(1000);
            //     } catch (Exception e) {
            //         // TODO: handle exception
            //         log.info("스레드 오류 발생");
            //     }
            //     count++;
    
            //     /*TODO#5 count 출력
            //         name:{Thread name}, count:{count 변수}
            //         Thread name : Thread.currentThread().getName();
            //         ex) name:my-thread, count:1
            //      */
            //     log.info("name : {}, count : {}",Thread.currentThread().getName(), count);
    
            // }while (count < countMaxSize);

            while (count < countMaxSize) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // TODO: handle exception
                    log.info("스레드 오류 발생");
                }
                count++;
    
                /*TODO#5 count 출력
                    name:{Thread name}, count:{count 변수}
                    Thread name : Thread.currentThread().getName();
                    ex) name:my-thread, count:1
                 */
                log.info("name : {}, count : {}",Thread.currentThread().getName(), count);
            }
        }
    }
