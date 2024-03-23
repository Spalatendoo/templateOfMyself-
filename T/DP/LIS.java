package DP;

import java.util.ArrayList;
import java.util.List;

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

    //------------------优化------------------------------------


    /**
     * O(nlogn)写法
     * 维护固定长度的IS的末尾元素的最小值 + 二分优化
     * 基于 值域 的线段树、平衡树等数据结构优化
     */

    //贪心
    public int lengthOfLIS1(int[] nums) {
        int n = nums.length;
        List<Integer> g = new ArrayList<>();
        for (int x : nums){
            int j = lowerBound(g , x);
            if (j == g.size()){
                g.add(x);
            }else {
                g.set(j , x);
            }
        }
        return g.size();
    }
    int lowerBound(List<Integer> g , int tar){
        int left = -1 , right = g.size();
        while (left + 1 < right){
            int mid = (left + right) >>> 1;
            if (g.get(mid) < tar){
                left = mid;
            }else {
                right = mid;
            }
        }
        return right;
    }




    //线段树优化
    int[] max;
    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int mx = 0;
        for (int x : nums){
            //+10001是因为有负数， 全部置为正数
            mx = Math.max(x + 10001 , mx);
        }
        max = new int[mx * 4];
        for (int x : nums){
            if (x == -10000){
                update(1 , 1 , mx , 1 , 1);
            }else {
                int q = query(1 ,1 , mx , 1 , x + 10000) + 1;
                update(1 , 1 , mx , x + 10001, q);
            }
        }
        return max[1];

    }
    void update(int o , int l , int r , int i , int val){
        if (l == r){
            max[o] = val;
            return;
        }
        int mid = (l + r) >>> 1;
        if (i <= mid){
            update(o * 2 , l , mid , i , val );
        }else {
            update(o * 2 + 1 , mid + 1 , r , i , val);
        }
        max[o] = Math.max(max[o * 2] , max[o * 2 + 1]);
    }
    int query(int o , int l , int r , int L , int R){
        if (l >= L && r <= R){
            return max[o];
        }
        int res = 0;
        int mid = (l + r) >>> 1;
        if (L <= mid){
            res = query(o *  2 , l , mid , L , R);
        }
        if (R > mid){
            res = Math.max(res , query(o * 2 + 1 , mid + 1 , r , L , R));
        }
        return res;
    }
}
