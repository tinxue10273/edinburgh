package dataStructures.syncPrinter;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JavaTestSemaphore {
    private static  final int COUNT = 10;
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 101, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));

    public static void main(String[] args) {
        Semaphore seA = new Semaphore(1);
        Semaphore seB = new Semaphore(0);
        Semaphore seC = new Semaphore(0);

        executor.submit(new PrintABC(seA, seB, 'A'));
        executor.submit(new PrintABC(seB, seC, 'B'));
        executor.submit(new PrintABC(seC, seA, 'C'));
    }

    static class PrintABC implements Runnable{
        private Semaphore currentPoint;
        private Semaphore nextPoint;
        private char printChar;

        public PrintABC(Semaphore currentPoint, Semaphore nextPoint, char printChar) {
            this.currentPoint = currentPoint;
            this.nextPoint = nextPoint;
            this.printChar = printChar;
        }

        @Override
        public void run() {
            for(int i = 0; i < COUNT; i++){
                try {
                    currentPoint.acquire();
                    System.out.println(Thread.currentThread().getName() + "  " + printChar);
                    nextPoint.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
