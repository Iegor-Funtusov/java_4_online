package ua.com.alevel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class WriteTest extends Thread {

    @Override
    public void run() {

    }

    private void oldStyle() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("out.txt", true);
            String s = Thread.currentThread().toString();
            System.out.println("thread name = " + s);
            Random random = new Random();
            fileWriter.write('\n');
            int sleep = random.nextInt(5) * 1000;
            System.out.println("sleep = " + sleep);
            Thread.sleep(sleep);
            fileWriter.write(s);
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void newStyle() {
        try(FileWriter fileWriter = new FileWriter("out.txt", true)) {
            String s = Thread.currentThread().toString();
            System.out.println("thread name = " + s);
            Random random = new Random();
            fileWriter.write('\n');
            int sleep = random.nextInt(5) * 1000;
            System.out.println("sleep = " + sleep);
            Thread.sleep(sleep);
            fileWriter.write(s);
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
