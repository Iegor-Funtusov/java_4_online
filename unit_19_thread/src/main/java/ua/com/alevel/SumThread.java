package ua.com.alevel;

public class SumThread extends Thread {

    private Long[] longs;
    private Long sum;

    @Override
    public void run() {
        sum = MathUtil.sum(longs);
        this.interrupt();
    }

    public void setLongs(Long[] longs) {
        this.longs = longs;
    }

    public Long getSum() {
        return sum;
    }
}
