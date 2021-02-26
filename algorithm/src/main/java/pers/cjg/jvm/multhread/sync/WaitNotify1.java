package pers.cjg.jvm.multhread.sync;


import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author chenjingang@guazi.com    2021-02-25 14:28
 */
public class WaitNotify1 {

    private Object odd;
    //    private Object even;
    private Integer num = 10;
    private Boolean ifOdd;
    private static Integer curNum = 1;
    private Random random = new Random(1000);

    public WaitNotify1(Object odd, Boolean ifOdd) {
        this.odd = odd;
        this.ifOdd = ifOdd;
    }

    public void print() {
//            try {
//                TimeUnit.MILLISECONDS.sleep(random.nextLong() % 500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        synchronized (odd) {
            for (int i = 0; i < num; i++) {
                if (curNum % 2 == (ifOdd ? 1 : 0)) {
                    System.out.println((ifOdd ? "奇数------" : "偶数") + " " + curNum++);
                    odd.notify();

                } else {
                    try {
                        System.out.println((ifOdd ? "奇数------" : "偶数") + " " + "阻塞");
                        if (i!=num-1)

                            odd.wait();
                        try {
                            TimeUnit.MILLISECONDS.sleep(random.nextLong() % 300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }


    public static void main(String[] args) {

        Object lock = new Object();
        WaitNotify1 waitNotify1 = new WaitNotify1(lock, true);
        new Thread(waitNotify1::print).start();
        WaitNotify1 waitNotify2 = new WaitNotify1(lock, false);
        new Thread(waitNotify2::print).start();

        try {
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.notifyAll();
        lock.notifyAll();
        lock.notifyAll();

    }
}
