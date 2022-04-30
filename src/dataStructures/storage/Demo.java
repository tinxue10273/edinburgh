package dataStructures.storage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Demo {

    public static void main(String[] args) {
        BlockingQueue<KouZhao> queue = new ArrayBlockingQueue<KouZhao>(20);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        try {
            new Thread(producer).start();
            Thread.sleep(20000);
            new Thread(consumer).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
