package com.jiangxb.juc.juclock;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class T04_Phaser {

    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人到齐！" + registeredParties + "\n");
                    return false;
                case 1:
                    System.out.println("所有人都吃完饭了！" + registeredParties + "\n");
                    return false;
                case 2:
                    System.out.println("客人都离开了！" + registeredParties + "\n");
                    return false;
                case 3:
                    System.out.println("婚礼结束!" + registeredParties + "\n");
                    return true;
                default:
                    return true;

            }
        }
    }

    static class Persion implements Runnable {

        String name;

        public Persion(String name){
            this.name = name;
        }

        public void secondsSleep(int seconds) {
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void arrive() {
            secondsSleep(2);
            System.out.println(name + "到达现场！");
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            secondsSleep(2);
            System.out.println(name + "吃完饭！");
            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            if ("新郎".equals(name) || "新娘".equals(name)) {
                secondsSleep(2);
                System.out.println(name + "留下！");
                phaser.arriveAndAwaitAdvance();
            } else {
                System.out.println(name + "离开！");
                /*
                 * 客人离开后从phaser中注销，不再受阶段控制
                 *
                 * 注意：这时客人的线程并没有结束，只是不再受phaser影响可自由往下执行
                 *
                 * 注销后可减少下一阶段中需要提前加入的线程，提高效率
                 */
                phaser.arriveAndDeregister();
            }

        }

        public void hug() {
            if ("新郎".equals(name) || "新娘".equals(name)) {
                secondsSleep(2);
                System.out.println(name + "拥抱！");
                phaser.arriveAndAwaitAdvance();
            }
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }

    public static void main(String[] args) {
        phaser.bulkRegister(7);

        for (int i = 0; i < 5; i++) {
            new Thread(new Persion("客人" + i)).start();
        }
        new Thread(new Persion("新郎")).start();
        new Thread(new Persion("新娘")).start();
    }

}

