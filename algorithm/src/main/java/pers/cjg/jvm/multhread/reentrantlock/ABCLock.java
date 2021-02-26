package pers.cjg.jvm.multhread.reentrantlock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenjingang@guazi.com    2021-02-25 14:28
 */
public class ABCLock {

    private ReentrantLock lock;
    private Condition cur;
    private Condition next;

    private Integer num;
    private Integer i;
    private String val;

    public ABCLock(ReentrantLock lock, Condition cur, Condition next, Integer num, String val) {
        this.lock = lock;
        this.cur = cur;
        this.next = next;
        this.num = num;
        this.val = val;
    }

    public void print() {
        try {
            lock.lock();
            for (int j = 0; j < num; j++) {
                System.out.println(val);
                next.signal();
//                sleep();
                try {
                    if (j!=num-1)
                    cur.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
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
        Condition a = reentrantLock.newCondition();
        Condition b = reentrantLock.newCondition();
        Condition c = reentrantLock.newCondition();
        ABCLock ABCLock1 = new ABCLock(reentrantLock,a, b, 10,"A");
        new Thread(ABCLock1::print).start();

        ABCLock ABCLock2 = new ABCLock(reentrantLock,b, c, 10,"B");
        new Thread(ABCLock2::print).start();

        ABCLock ABCLock3 = new ABCLock(reentrantLock,c, a, 10,"C");
        new Thread(ABCLock3::print).start();
        return;
    }
}
