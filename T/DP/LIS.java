package DP;

public class LIS {
    /**
     * 最长递增子序列
     * 灵神讲解链接  https://www.bilibili.com/video/BV1ub411Q7sB/
     * O(n^2) 和 O(nlogn) 两种做法
     * P_300   https://leetcode.cn/problems/longest-increasing-subsequence/
     * P_1671 LIS变形 https://leetcode.cn/problems/minimum-number-of-removals-to-make-mountain-array/
     * P_2407  https://leetcode.cn/problems/longest-increasing-subsequence-ii/   O(nlogn)
     */


    //O(n^2)
    public int lengthOfLIS(int[] nums) {
        int n = nums.length , ans = 0;
        int[] f = new int[n];  //f[i] 表示以nums[i]结尾的
        for (int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < i ; j++){
                //严格递增 ， 如果不是严格递增 ， 判断nums[j] <= nums[i]
                if (nums[j] < nums[i]){
                    f[i] = Math.max(f[i] , f[j]);
                }
            }
            f[i]++;  //算上自身
            ans = Math.max(ans , f[i]);
        }
        return ans;
    }

    //递归写法
    int[] rec ;
    public int lengthOfLIS_dfs(int[] nums) {
        int n = nums.length;
        rec = new int[n];
        int res = 0;
        for(int i = 0;i<n;i++){
            res = Math.max(res,dfs(nums,i));
        }
        return res;

    }
    private int dfs (int[] nums,int index){
        if(index==0){
            rec[0] = 1;
            return 1;
        }
        if(rec[index]!=0){
            return rec[index];
        }
        int res = 0;
        for(int i = 0;i<index;i++){
            if(nums[i] < nums[index]){
                res = Math.max(res,dfs(nums,i));
            }

        }
        return rec[index] = res  + 1;
    }

    //------------------------------------------------------

}
