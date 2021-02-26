package pers.cjg.jvm.multhread.sync;


import java.util.concurrent.TimeUnit;

/**
 * @author chenjingang@guazi.com    2021-02-25 14:28
 */
public class WaitNotify {

    private Object cur;
    private Object next;
    private Integer num;

    public WaitNotify(Object cur, Object next, Integer num) {
        this.cur = cur;
        this.next = next;
        this.num = num;
    }

    public void print() {
        for (int i = 0; i < num; i++) {
            synchronized (cur) {
                synchronized (next) {
                    System.out.println(cur);
                    next.notify();
                }
                try {
                    if (i!=num-1)

                        cur.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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

        String a = new String("A");
        Object b = new String("B");
        Object c = new String("C");
        WaitNotify waitNotify1 = new WaitNotify(a, b, 10);
        new Thread(waitNotify1::print).start();

        WaitNotify waitNotify2 = new WaitNotify(b, c, 10);
        new Thread(waitNotify2::print).start();

        WaitNotify waitNotify3 = new WaitNotify(c, a, 10);
        new Thread(waitNotify3::print).start();
        return;
    }
}
