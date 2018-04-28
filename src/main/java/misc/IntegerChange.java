package misc;

/**
 *     给定两个长度为 n ( 0 < n <= 8 ) 的 数字串 (由1到9构成)  ，我们希望对第一个数字串做一系列如下操作：
 *     1、将数字串的某一位加1
 *     2、将数字串的某一位减1
 *     3、交换数字串中任意两个数字的位置
 * 最终使得第一个数字串变成第二个数字串， 请问最少需要多少操作。
 */
public class IntegerChange {
    public static void main(String[] args) {
        int[] num1 = new int[]{1, 2, 3, 4, 5};
        int[] num2 = new int[]{3, 1, 2, 4, 8};
        System.out.println(new IntegerChange().interchange(num1, num2));
    }

    public int interchange(int[] num1, int[] num2) {
        if (num1 == null || num2 == null || num1.length == 0 || num1.length != num2.length) {
            return -1;
        }
        return interchange(num1, num2, 0);
    }

    private int interchange(int[] num1, int[] num2, int pos) {
        int diff1 = Math.abs(num1[pos] - num2[pos]);
        if (pos == num1.length - 1) {
            return diff1;
        } else {
            diff1 += interchange(num1, num2, pos + 1);
        }

         int diff2 = Integer.MAX_VALUE;
         for (int i = pos + 1; i < num1.length; ++i) {
            swap(num1, pos, i);
            diff2 = Math.min(diff2, interchange(num1, num2, pos + 1) + 1 + Math.abs(num1[pos] - num2[pos]));
            swap(num1, pos, i);
         }

         return Math.min(diff1, diff2);
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
