package ua.com.alevel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String FILE = "file.txt";
    private static final String FILE_UPDATE = "update_file.txt";
    private static final String FILE_HIDDEN = ".file.txt";
    private static final String DIR = "folder";
    private static final String DIRS = "folder/folder1/folder2";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        FileIO fileIO = new FileIO();
//        fileIO.create(FILE);
//        fileIO.createDir(DIR);
//        fileIO.createDirs(DIRS);
//        fileIO.remove(FILE);
//        fileIO.observ(DIR);
//        fileIO.removeDir(DIR);
//        RWFile rwFile = new RWFile();
//        rwFile.read(FILE);
//        rwFile.write();
//        SerialTest serialTest = new SerialTest();
//        serialTest.test();
//        FileNIO fileNIO = new FileNIO();
//        fileNIO.create(FILE);
//        fileNIO.createDir(DIR);
//        fileNIO.createDirs(DIRS);
//        fileNIO.write(FILE, "\nHello World");
//        fileNIO.read(FILE);
        List<WriteTest> writeTests = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            writeTests.add(new WriteTest());
        }
        for (WriteTest writeTest : writeTests) {
            writeTest.start();
        }



        // old style
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        try {
            reader = new FileReader("file.txt");
            bufferedReader = new BufferedReader(reader);
        } catch (Exception e) {

        }
        finally {
            bufferedReader.close();
            reader.close();
        }

        try(
                FileReader reader1 = new FileReader("file.txt");
                BufferedReader bufferedReader1 = new BufferedReader(reader)
                ) {
            String s = bufferedReader1.readLine();
            System.out.println("s = " + s);
        } catch (Exception e) {

        }
    }
}
