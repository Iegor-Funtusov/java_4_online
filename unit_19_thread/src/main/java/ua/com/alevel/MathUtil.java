package ua.com.alevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class MathUtil {

    private MathUtil() { }

    public static Long[] generateLongList(int size) {
        List<Long> longList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            longList.add(random.nextLong(1, 1_000_000));
        }
        return longList.toArray(Long[]::new);
    }

    public static Long sum(Long[] longList) {
        Long sum = 0L;
        for (Long aLong : longList) {
            sum += aLong;
        }
        return sum;
    }

    public static List<Long[]> divideArray(Long[] longs) {
        int didide = longs.length / 2;
        List<Long[]> list = new ArrayList<>();
        Long[] left = Arrays.copyOfRange(longs, 0, didide);
        Long[] right = Arrays.copyOfRange(longs, didide, longs.length);
        list.add(left);
        list.add(right);
        return list;
    }
}
