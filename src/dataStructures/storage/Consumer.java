package dataStructures.storage;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    private final BlockingQueue<KouZhao> blockingQueue;

    public Consumer(BlockingQueue<KouZhao> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        while (true){
            try {
                Thread.sleep(100);
                long startTime = System.currentTimeMillis();
                KouZhao take = blockingQueue.take();
                long endTime = System.currentTimeMillis();
                System.out.println("我消费了口罩：" + take + ", 等到货的时候我阻塞了" + (endTime - startTime) + "秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
