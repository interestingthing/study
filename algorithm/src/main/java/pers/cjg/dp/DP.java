package pers.cjg.dp;

import java.time.Instant;
import java.util.Date;

/**
 * @Author: chenjingang@guazi.com  2020-10-23 10:20
 */
public class DP {


    /**
     * coins[i]
     * <p>
     * 0<=j<i
     * <p>
     * dp[n]=min{(dp[n-coins])+1|coin 属于coins}
     * res=0;
     * def dp(n)
     * if(n==0) return 0;
     * if(n==-1)return -1;
     * for coin in coins{
     * temp = dp(n-coin);
     * if(temp==-1){
     * continue;
     * }
     * res = min(res,temp+1);
     * }
     * <p>
     * return dp[n]
     */
    static Integer[] coins = new Integer[]{1, 2, 5};
    static int[] dp = new int[12];

    static Integer res = 0;
    static Integer count = 0;

    static Integer dp(Integer n) {
        if (n == 0) return 0;
        if (n == -1) return -1;

        if (dp[n] != 0) {
            return dp[n];
        }
        count++;
        System.out.println(count);
        for (Integer coin : coins) {
            if (n >= coin) {
                res = Math.min(dp[n], dp(n - coin)) + 1;
                dp[n] = res;
            }
        }

        return res;
    }


//    -------------------------------------------

    /**
     * n层楼 拥有k个
     * <p>
     * dp[n][0]=0;
     * dp[n][1]=n
     * <p>
     * <p>
     * <p>
     * dp[n][k]= min(res,max(dp[n-i][k],dp[i-1][k-1])+1)
     */

    public static int memo1[][] = new int[101][4];

    public static Integer dp1(Integer n, Integer k) {
        if (n == 0) {
            return 0;
        }
        if (k == 1) {
            return n;
        }
        if (memo1[n][k] != 0) {
            return memo1[n][k];
        }
        int res = Integer.MAX_VALUE, temp = 0;
        for (int i = 1; i < n + 1; i++) {
            int max = Math.max(dp1(n - i, k), dp1(i - 1, k - 1)) + 1;

            if (res > max) {
                res = max;
                temp = i;
            }
        }
        memo1[n][k] = res;

        return res;
    }


    public static void main(String[] args) {
        System.out.println(new Date(120, 0, 8));
//        Integer dp = dp(11);
//        System.out.println(dp);
        Integer integer = dp1(100, 3);

        System.out.println(integer);
        for (int j = 0; j < memo1[0].length; j++) {
            for (int i = 0; i < memo1.length; i++) {
                System.out.print(memo1[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }

    }


    /**
     *
     *
     *
     *
     */
}






















