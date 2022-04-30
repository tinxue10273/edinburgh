package dataStructures.syncPrinter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JavaTestReentrantLock {

    private static volatile int state = 1;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition conA = lock.newCondition();
        Condition conB = lock.newCondition();
        Condition conC = lock.newCondition();

        new Thread(new printABC(lock, conA, conB, 'A', 1)).start();
        new Thread(new printABC(lock, conB, conC, 'B', 2)).start();
        new Thread(new printABC(lock, conC, conA, 'C', 3)).start();
    }


    static class printABC implements Runnable{

        private Lock lock;
        private Condition currentPoint;
        private Condition nextPoint;
        private char printChar;
        private int targetState;

        public printABC(Lock lock, Condition currentPoint, Condition nextPoint, char printChar, int targetState) {
            this.lock = lock;
            this.currentPoint = currentPoint;
            this.nextPoint = nextPoint;
            this.printChar = printChar;
            this.targetState = targetState;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                for(int i = 0; i< 10; i++){

                    while (state != targetState){
                        currentPoint.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + printChar);
                    state++;
                    if(state > 3){
                        state = 1;
                    }
                    //nextPoint.signalAll();
                    nextPoint.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
