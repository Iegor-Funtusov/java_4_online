package ua.com.alevel;

public class CustomThread extends Thread {

    private final String threadName;

    public CustomThread(String name) {
        super(name);
        this.threadName = name;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("threadName = " + threadName);
    }

    public String getThreadName() {
        return threadName;
    }
}
