package dataStructures.storage;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    private final BlockingQueue<KouZhao> blockingDeque;

    public Producer(BlockingQueue<KouZhao> blockingDeque){
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(200);
                if(blockingDeque.remainingCapacity() > 0){
                    KouZhao kouZhao = new KouZhao(UUID.randomUUID().toString(), "N95");
                    blockingDeque.add(kouZhao);
                    System.out.println("我在生产口罩，当前库存是：" + blockingDeque.size());
                }else {
                    System.out.println("当前库存满了，" + blockingDeque.size() + "个口罩， 大家快来买啊");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
