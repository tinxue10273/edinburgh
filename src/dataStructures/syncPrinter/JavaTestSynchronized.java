package dataStructures.syncPrinter;

public class JavaTestSynchronized {

    private static volatile int state = 1;

    public static void main(String[] args) {
        Object o = new Object();
        new Thread(new printDepth(o, 1, 2, 'A')).start();
        new Thread(new printDepth(o, 2, 3, 'B')).start();
        new Thread(new printDepth(o, 3, 1, 'C')).start();
    }

    static class printDepth implements Runnable{
        private static final int COUNT = 10;
        private Object lockObject;
        private int currPrint;
        private int nextPrint;
        private char charPrint;

        public printDepth(Object lockObject, int currPrint, int nextPrint, char charPrint) {
            this.lockObject = lockObject;
            this.currPrint = currPrint;
            this.nextPrint = nextPrint;
            this.charPrint = charPrint;
        }

        @Override
        public void run() {
            synchronized (lockObject){
                for(int i = 0; i < COUNT; i++){
                    try {
                        while (currPrint != state){
                            lockObject.wait();
                        }
                        System.out.println(Thread.currentThread().getName() + " " + charPrint);
                        state = nextPrint;
                        lockObject.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
