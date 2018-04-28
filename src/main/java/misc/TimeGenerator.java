package misc;

import permutation.Permutations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 随机给出6个0~9的数字，按照24小时格式组合这六个数字，生成最早时间和最晚时间，如果不能生成则返回错误。
 * 如给定1、2、3、4、5、6六个数字，则组合生成的最早时间是：12:34:56，最晚时间是：23:56:41。
 * 如果给定111111六个数字，则能够组合生成的最早和最晚时间均是：11:11:11；
 * 如果给出的是999999六个数字，因为无法组合成24小时格式的时间则返回错误。
 */
public class TimeGenerator {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1,2,3,4,5,6};

        Optional<List<Integer>> ret = Permutations.of(array)
            .map(s -> s.collect(Collectors.toList()))
            .filter(s -> isValid(s)).findFirst();

        System.out.println(ret);
    }

    public int[] readData() {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[] array = new int[length];
        for (int i = 0; i < length; ++i) {
            array[i] = scanner.nextInt();
        }

        return array;
    }

    public static boolean isValid(List<Integer> list) {
        return isValid(list.stream().toArray(Integer[]::new));
    }

    public static boolean isValid(Integer[] array) {
        return array[0] * 10 + array[1] <= 23 && array[2] * 10 + array[3] <= 59 && array[4] * 10 + array[5] <= 59;
    }

}
