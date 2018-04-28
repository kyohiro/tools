package misc;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 对于一个长度为N的整型数组A， 数组里所有的数都是正整数，对于两个满足 0<=X <= Y <N的整数，A[X], A[X+1] … A[Y]构成A的一个切片，记作(X, Y)。
 *  用三个下标 m1, m2, m3下标满足条件 0 < m1, m1 + 1 < m2, m2 +1 < m3 < N – 1。
 *  可以把这个整型数组分成(0, m1-1), (m1+1, m2-1), (m2+1, m3-1), (m3+1, N-1) 四个切片。
 *  如果这四个切片中的整数求和相等，称作“四等分”。
 *  编写一个函数，求一个给定的整型数组是否可以四等分，如果可以，返回一个布尔类型的true，
 *  如果不可以返回一个布尔类型的false。
 *  限制条件： 数组A最多有1,000,000项，数组中的整数取值范围介于-1,000,000到1,000,000之间。
 *  要求： 函数的计算复杂度为O(N)，使用的额外存储空间（除了输入的数组之外）最多为O(N)。
 */
public class SplitFour {
    public static void main(String[] args) {
        int[] array = new int[]{2, 5, 1, 1, 1, 1, 4, 1, 7, 3, 7};
        System.out.println(Arrays.toString(new SplitFour(array).getSplitIndex()));
    }

    private final int[] array;
    private Map<Integer, BigInteger> sumMap = new HashMap<>();

    public SplitFour(int[] array) {
        this.array = array;
        buildSumMap();
    }

    private void buildSumMap() {
        sumMap.put(0, BigInteger.valueOf(array[0]));
        for (int i = 1; i < array.length; ++i) {
            sumMap.put(i, sumMap.get(i - 1).add(BigInteger.valueOf(array[i])));
        }
    }

    private int[] getSplitIndex() {
        if (array == null || array.length < 3) {
            return new int[]{};
        }
        for (int i = 2; i < array.length; ++i) {
            int[] ret = tryMid(i);
            if (ret.length == 2) {
                return new int[]{ret[0], i, ret[1]};
            }
        }

        return new int[]{};
    }

    private int[] tryMid(int mid) {
        BigInteger sumLeft = sumMap.get(mid).subtract(BigInteger.valueOf(array[mid]));
        BigInteger sumRight = sumMap.get(array.length - 1).subtract(sumMap.get(mid));

        int indexLeft = -1, indexRight = -1;
        BigInteger splitSum = null, halfSum = null;
        BigInteger two = BigInteger.valueOf(2);

        for (int i = 1; i < mid; ++i) {
            if (sumMap.get(i - 1).multiply(two).equals(sumLeft.subtract(BigInteger.valueOf(array[i])))) {
                indexLeft = i;
                splitSum = sumMap.get(i - 1);
                halfSum = splitSum.multiply(two);
            }
        }

        if (splitSum == null) {
            return new int[]{};
        }

        for (int i = mid + 2; i < array.length; ++i) {
            if (!sumRight.subtract(BigInteger.valueOf(array[i])).equals(halfSum)) {
                continue;
            }
            if (sumMap.get(i - 1).subtract(sumMap.get(mid)).equals(splitSum)) {
                indexRight = i;
                return new int[]{indexLeft, indexRight};
            }
        }
        return new int[]{};
    }





}
