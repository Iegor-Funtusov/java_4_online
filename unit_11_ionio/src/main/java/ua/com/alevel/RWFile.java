package ua.com.alevel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RWFile {

    private Reader reader;
    private Writer writer;

    private InputStream inputStream;
    private OutputStream outputStream;

    public void read(String path) throws IOException {
        inputStream = new FileInputStream(path);
        byte[] bytes = inputStream.readAllBytes();
        for (byte aByte : bytes) {
//            System.out.println("aByte = " + aByte);
        }

        reader = new FileReader(path);

        System.out.println();

//        while (reader.ready()) {
//            int read = reader.read();
//            System.out.println("read = " + read);
//        }

        BufferedReader bufferedReader = new BufferedReader(reader);
//        while (bufferedReader.ready()) {
//            String text = bufferedReader.readLine();
//            System.out.println("text = " + text);
//        }
        Stream<String> lines = bufferedReader.lines();

        String result = lines
                .filter(text -> !text.isBlank())
                .collect(Collectors.joining());

        FileWriter fileWriter = new FileWriter("out.txt", true);
        fileWriter.write(result);
        fileWriter.flush();

    }

    public void write(String text, int sleep) throws IOException, InterruptedException {
//        outputStream = new FileOutputStream("out.txt");
//        outputStream.write(new byte[]{ '@', 32, 65 });

        System.out.println("sleep = " + sleep);
        Thread.sleep(sleep);
        FileWriter fileWriter = new FileWriter("out.txt", true);
        fileWriter.write('\n');
        fileWriter.write(text);
        fileWriter.flush();
    }
}
