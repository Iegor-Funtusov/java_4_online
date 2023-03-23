package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadTest {

    private final Long[] longList = MathUtil.generateLongList(1_000_000);

    public void test() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Select your options");
            String select;
            menu();
            while ((select = reader.readLine()) != null) {
                threadTest(select);
            }
        } catch (IOException e) {
            System.out.println("e = " + e);
        }
    }

    private void threadTest(String select) {
        switch (select) {
            case "1" ->  runThreadTest();
            case "2" ->  exceptionsInThread();
            case "3" ->  incorrectStopThread();
            case "4" ->  correctStopThread();
            case "5" ->  simpleSum();
            case "6" ->  simpleThreadSum();
            case "7" ->  callableSum();
            case "8" ->  futureSum();
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want run test threads, please enter 1");
        System.out.println("If you want throw exceptions in thread, please enter 2");
        System.out.println("If you want incorrect stop thread, please enter 3");
        System.out.println("If you want correct stop thread, please enter 4");
        System.out.println("If you want simple calculate sum, please enter 5");
        System.out.println("If you want thread calculate sum, please enter 6");
        System.out.println("If you want callable thread calculate sum, please enter 7");
        System.out.println("If you want future thread calculate sum, please enter 8");
        System.out.println();
    }

    private void runThreadTest() {
        List<CustomThread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);
            list.add(new CustomThread("Thread_name_" + i));
        }
        for (CustomThread customThread : list) {
            System.out.println("customThread name = " + customThread.getThreadName());
        }
        for (CustomThread customThread : list) {
            customThread.start();
        }
    }

    private void exceptionsInThread() {
        List<CustomExceptionThread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new CustomExceptionThread());
        }
        for (CustomExceptionThread customExceptionThread : list) {
            customExceptionThread.start();
        }
    }

    private void incorrectStopThread() {
        Thread thread = new CustomStopThread("incorrect_stop_thread");
        thread.start();
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }

    private void correctStopThread() {
        System.out.println("ThreadTest.correctStopThread");
        String name = Thread.currentThread().getName();
        System.out.println("thread name = " + name);
        Thread thread = new CustomStopThread("correct_stop_thread");
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    private void simpleSum() {
        System.out.println("ThreadTest.simpleSum");
        long start = System.currentTimeMillis();
        long sum = MathUtil.sum(longList);
        long end = System.currentTimeMillis() - start;
        System.out.println("sum: " + sum + " calculated by " + end + " ms.");
    }

    private void simpleThreadSum() {
        System.out.println("ThreadTest.simpleThreadSum");
        long start = System.currentTimeMillis();
        List<Long[]> list = MathUtil.divideArray(longList);
        List<Long[]> newList = new ArrayList<>();
        for (Long[] longs : list) {
            newList.addAll(MathUtil.divideArray(longs));
        }
        Long sum = 0L;
        List<SumThread> sumThreadList = new ArrayList<>();
        for (Long[] longs : newList) {
            SumThread sumThread = new SumThread();
            sumThread.setLongs(longs);
            sumThread.start();
            sumThreadList.add(sumThread);
        }

        boolean notInterrupt = true;
        while (notInterrupt) {
            int count = 0;
            for (SumThread sumThread : sumThreadList) {
                if (sumThread.isInterrupted()) {
                    count += 1;
                }
            }
            if (count == sumThreadList.size()) {
                for (SumThread sumThread : sumThreadList) {
                    sum += sumThread.getSum();
                }
                notInterrupt = false;
            }
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("thread sum: " + sum + " calculated by " + end + " ms.");
    }

    private void callableSum() {
        System.out.println("ThreadTest.callableSum");
        long start = System.currentTimeMillis();
        List<Long[]> list = MathUtil.divideArray(longList);
        List<Long[]> newList = new ArrayList<>();
        for (Long[] longs : list) {
            newList.addAll(MathUtil.divideArray(longs));
        }
        Long sum = 0L;
        for (Long[] longs : newList) {
            Callable<Long> callable = () -> MathUtil.sum(longs);
//            Callable<Long> callable = new Callable<Long>() {
//                @Override
//                public Long call() throws Exception {
//                    return MathUtil.sum(longs);
//                }
//            };
            try {
                sum += callable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("callable sum: " + sum + " calculated by " + end + " ms.");
    }

    private void futureSum() {
        System.out.println("ThreadTest.futureSum");
        long start = System.currentTimeMillis();
        List<Long[]> list = MathUtil.divideArray(longList);
        List<Long[]> newList = new ArrayList<>();
        for (Long[] longs : list) {
            newList.addAll(MathUtil.divideArray(longs));
        }
        Long sum = 0L;

        ExecutorService executorService = Executors.newFixedThreadPool(newList.size());

        List<Future<Long>> futures = new ArrayList<>();

        for (Long[] longs : newList) {
            Future<Long> longFuture = executorService.submit(() -> MathUtil.sum(longs));
            futures.add(longFuture);
        }

        while (true) {
            if (futures.stream().allMatch(Future::isDone)) {
                for (Future<Long> future : futures) {
                    try {
                        sum += future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }

        long end = System.currentTimeMillis() - start;
        System.out.println("future sum: " + sum + " calculated by " + end + " ms.");
    }
}
