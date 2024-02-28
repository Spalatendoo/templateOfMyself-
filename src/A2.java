import java.io.*;
import java.util.*;
public class A2 {
    public static void main(String[] args) {
        A2 solution = new A2();
        int[] nums = {1,3};
        int[] change = {1,1,1,2,1,1,1};
        System.out.println(solution.earliestSecondToMarkIndices(nums , change));

    }
    int n , m ;
    int cnt ;
    boolean[] f;
    int[][] rec;
    int res;
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        n = nums.length;
        m = changeIndices.length;
        res = Integer.MAX_VALUE;
        f = new boolean[n];
        Set<Integer> set = new HashSet<>();
        for (int x : changeIndices){
            set.add(x);
        }
        if (set.size() < n){
            return -1;
        }
        for (int i = 0 ; i < n ; i++){
            if (nums[i] == 0){
                f[i] = true;
                cnt++;
            }
        }
        dfs(0 , nums , changeIndices);
        return res;


    }
    void dfs(int seconds , int[] nums  , int[] changeIndices){
        if (cnt == n){
            res = Math.min(res , seconds);
            return;
        }
        if (seconds == m){
            return;
        }
        for (int i = 0 ; i < n ; i++){
            if (nums[i] > 0){
                nums[i]--;
                if (!f[i] && changeIndices[seconds] == i && nums[i]==0){
                    cnt++;
                    f[i] = true;
                }
                dfs(seconds + 1 , nums , changeIndices );
                nums[i]++;
                cnt--;
                f[i] = false;
            }else if (nums[i] == 0){
                if (!f[i] && changeIndices[seconds] == i){
                    cnt++;
                    f[i] = true;
                }
                dfs(seconds + 1 , nums , changeIndices);

            }
        }
    }

}











