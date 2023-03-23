package ua.com.alevel;

public class CustomStopThread extends Thread {

    public CustomStopThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("thread name: " + threadName);
        while (true) {
            System.out.println("running ...");
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }
        System.out.println(threadName + " is stopped");
    }
}
