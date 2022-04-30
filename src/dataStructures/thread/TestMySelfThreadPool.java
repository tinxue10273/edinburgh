package dataStructures.thread;

public class TestMySelfThreadPool {
    private static final int TASK_NUM = 50;

    public static void main(String[] args) {
        ThreadPool myPool = new ThreadPool(3, 50);
        for(int i = 0; i < TASK_NUM; i++){
            myPool.execute(new MyTask("task_" + i));
        }
    }

    static class MyTask implements Runnable{

        private String name;

        public MyTask(String name){
            this.name = name;
        }

        public String getname(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString(){
            return "name = " + name;
        }
    }
}
