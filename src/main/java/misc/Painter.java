package misc;


/**
 * 有个穷困的艺术家。他画了一幅超现实主义的作品《方块手拉手》。
 * 现在他已经把图画中手拉手的一排大小不一的方块都画出来了。现在要考虑上颜色了。
 * 可惜他手中的钱并不多了。但是他是个有追求的人，他希望这幅画中每两个相邻的方块的颜色是不一样的。
 * 你能帮他计算一下把这幅画上色后，最少需要花多少钱么。
 */
public class Painter {
    public static void main(String[] args) {
        int[][] array = new int[][]{{2,3,2}, {9,1,4}, {7,8,1}, {2,8,3}};
        System.out.println(minCost(array));
    }

    public static int minCost(int[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            return 0;
        }
        int blocks = array.length, colors = array[0].length;

        int[][] dp = new int[blocks][colors];
        for (int i = 0; i < colors; ++i) {
            dp[0][i] = array[0][i];
        }

        for (int i = 1; i < blocks; ++i) {
            for (int j = 0; j < colors; ++j) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < colors; ++k) {
                    if (k != j) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + array[i][j]);
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < colors; ++j) {
            min = Math.min(min, dp[blocks - 1][j]);
        }

        return min;
    }
}
