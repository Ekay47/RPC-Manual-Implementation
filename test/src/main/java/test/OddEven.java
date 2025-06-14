package test;

import java.util.concurrent.Semaphore;

public class OddEven {
    private final int max;

    private final Semaphore aSemaphore = new Semaphore(1);
    private final Semaphore bSemaphore = new Semaphore(0);
    private final Semaphore cSemaphore = new Semaphore(0);

    public OddEven(int max) {
        this.max = max;
    }

    public void printA() {
        printNumber("A", aSemaphore,bSemaphore);
    }

    public void printB() {
        printNumber("B", bSemaphore,cSemaphore);
    }

    public void printC() {
        printNumber("C", cSemaphore,aSemaphore);
    }

    public void printNumber(String str, Semaphore cur, Semaphore next){
        for(int i=1;i<=max;i++){
            try {
                cur.acquire();
                System.out.println(Thread.currentThread().getName() + " : " + str);
                next.release();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public static void main(String[] args) {
        OddEven oddEven = new OddEven(10);
        Thread t1 = new Thread(oddEven::printA, "TA");
        Thread t2 = new Thread(oddEven::printB, "TB");
        Thread t3 = new Thread(oddEven::printC, "TC");
        t1.start();
        t2.start();
        t3.start();
    }
}
