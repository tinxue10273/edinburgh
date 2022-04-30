package dataStructures;

// 手写交替打印FooBar
public class FooBar {
    private int n;
    private static final  Object LOCK = new Object();
    public FooBar(int n){
        this.n = n;
    }

    public void foo(){
        System.out.print("foo");
    }

    public void bar(){
        System.out.print("bar");
    }

    public void foo(Runnable printFoo) throws InterruptedException{
        for(int i = 0; i < n; i++){
            synchronized (LOCK){
                LOCK.notify();
                printFoo.run();
                LOCK.wait();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException{
        for(int i = 0; i < n; i++){
            LOCK.notify();
            printBar.run();
            if(i < n - 1){
                LOCK.wait();
            }
        }
    }

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(1);
        try {
            fooBar.foo(() -> fooBar.foo());
            fooBar.bar(() -> fooBar.bar());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
