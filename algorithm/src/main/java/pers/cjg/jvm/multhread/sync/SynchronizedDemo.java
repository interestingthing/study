package pers.cjg.jvm.multhread.sync;

public class SynchronizedDemo {
    public static Integer i =0;
    public static void main(String[] args) {
        synchronized (SynchronizedDemo.class) {
            i=5;
        }
        method();
    }
 
    private static void method() {
    }
}