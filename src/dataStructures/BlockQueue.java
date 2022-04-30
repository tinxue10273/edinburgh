package dataStructures;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 手写阻塞队列
public class BlockQueue {
    private Object[] tab;
    private int takeIndex;
    private int putIndex;
    private int size;
    private ReentrantLock reentrantLock = new ReentrantLock();

    private Condition notEmpty;
    private Condition notFull;

    public BlockQueue(int tabCount){
        if(tabCount <= 0){
            new NullPointerException();
        }

        tab = new Object[tabCount];
        notEmpty = reentrantLock.newCondition();
        notFull = reentrantLock.newCondition();
    }

    public boolean offer(Object obj){
        if(obj == null){
            throw new NullPointerException();
        }

        try {
            reentrantLock.lock();

            while (size == tab.length){
                System.out.println("队列已满");
                notFull.await();
            }
            tab[putIndex] = obj;
            if(++putIndex ==tab.length){
                putIndex = 0;
            }

            size++;

            notEmpty.signal();
            return true;
        } catch (InterruptedException e) {
            notEmpty.signal();
        }finally {
            reentrantLock.unlock();
        }
        return false;
    }

    public Object take(){
        try {
            reentrantLock.lock();
            while (size == 0){
                System.out.println("队列空了");
                notEmpty.await();
            }

            Object obj = tab[takeIndex];

            if(++ takeIndex == tab.length){
                takeIndex = 0;
            }
            size--;

            notFull.signal();
            return obj;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
        return null;
    }

    public static void main(String[] args) {
        Random random = new Random(100);
        BlockQueue blockQueue = new BlockQueue(5);
        Thread thread1 = new Thread(() -> {
           for(int i = 0; i < 100; i++){
               try {
                   Thread.sleep(300);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               blockQueue.offer(i);
               System.out.println("生产者生产了: " + i);
           }
        });

        Thread thread2 = new Thread(() -> {
           for(int i = 0; i< 100; i++){
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               Object take = blockQueue.take();
               System.out.println("消费者消费了: " + take);
           }
        });
        thread1.start();
        thread2.start();
    }
}
