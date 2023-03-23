package ua.com.alevel;

import java.util.Random;

public class CustomExceptionThread extends Thread {

    @Override
    public void run() {
        Random random = new Random();
        int val = random.nextInt(2);
        System.out.println("val = " + 10 / val);
    }
}
