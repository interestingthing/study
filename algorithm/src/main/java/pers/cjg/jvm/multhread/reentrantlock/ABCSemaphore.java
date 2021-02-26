package pers.cjg.jvm.multhread.reentrantlock;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenjingang@guazi.com    2021-02-25 14:28
 */
public class ABCSemaphore {

    private ReentrantLock lock;
    private Semaphore cur;
    private Semaphore next;

    private Integer num;
    private Integer i;
    private String val;

    public ABCSemaphore(ReentrantLock lock, Semaphore cur, Semaphore next, Integer num, String val) {
        this.lock = lock;
        this.cur = cur;
        this.next = next;
        this.num = num;
        this.val = val;
    }

    public void print() {
        try {
            for (int j = 0; j < num; j++) {
                cur.acquire();
                System.out.println(val);
                next.release();
////                sleep();
//                try {
//                    if (j!=num-1)
//                    cur.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }catch(Exception e){

        } finally {
        }
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Semaphore a = new Semaphore(1);
        Semaphore b = new Semaphore(0);
        Semaphore c = new Semaphore(0);
        ABCSemaphore ABCLock1 = new ABCSemaphore(reentrantLock,a, b, 10,"A");
        new Thread(ABCLock1::print).start();

        ABCSemaphore ABCLock2 = new ABCSemaphore(reentrantLock,b, c, 10,"B");
        new Thread(ABCLock2::print).start();

        ABCSemaphore ABCLock3 = new ABCSemaphore(reentrantLock,c, a, 10,"C");
        new Thread(ABCLock3::print).start();
        return;
    }
}
