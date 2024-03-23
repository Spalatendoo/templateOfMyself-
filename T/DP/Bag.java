package DP;

public class Bag {
    //背包问题

    /**
     * 多重背包
     * P_2585 求方案数  https://leetcode.cn/problems/number-of-ways-to-earn-points/
     * P_1981  求拿的不同结果  https://leetcode.cn/problems/minimize-the-difference-between-target-and-chosen-elements/
     * P_2218  求最大 https://leetcode.cn/problems/maximum-value-of-k-coins-from-piles/
     */
    /**
     *
     * @param target 背包容量
     * @param things 物品类型有n种 ， 每种物品数量有c个 ， 每种物品的价值为v
     * @return 返回可以拿满target价值的 方案数
     *
     */
    //   f[i][j] 表示用拿前 i 种物品恰好组成 j 分的方案数
    public int multi_bag(int target, int[][] things) {
        int n = things.length , mod = (int) 1e9 + 7;
        long[][] f = new long[n + 1][target + 1];
        for (int i = 0 ; i <= n ; i++){
            f[i][0] = 1;
        }
        for (int i = 1 ; i <= n ; i++){
            int[] type = things[i - 1];
            for (int j = 1 ; j <= target ; j++){
                f[i][j] = f[i-1][j];
                for (int k = 1 ; k <= type[0] ; k++){
                    if (j - k * type[1] < 0){
                        break;
                    }
                    f[i][j] = (f[i - 1][j - k * type[1]] + f[i][j]) % mod;
                }
            }
        }
        return (int)f[n][target];
    }
     int muti_bag_1(int target , int[][] things){
        int MOD = (int)1e9 + 7;
        var f = new int[target + 1];
        f[0] = 1;
        for (var p : things) {
            int count = p[0], marks = p[1];
            for (int j = target; j > 0; --j)
                for (int k = 1; k <= count && k <= j / marks; ++k)
                    f[j] = (f[j] + f[j - k * marks]) % MOD;
        }
        return f[target];

    }

    //--------------------------------------------


}
