import java.util.*;
public class bitCalc {
    //位运算的某类问题通用模板

    /**
     * 求OR AND GCD 子数组相关问题通用板子  （求子数组数量、最长、最短等问题
     * LC 2411 898 1521 3097 ...
     * https://leetcode.cn/circle/discuss/dHn9Vk/ 灵神位运算题单   与、或的性质
     */

    //LC 1521 与
    public int closestToTarget(int[] nums, int target) {
        int ans = Integer.MAX_VALUE;
        List<int[]> ors = new ArrayList<>(); // 保存 (右端点为 i 的子数组 OR, 该子数组左端点的最大值)
        for (int i = 0; i < nums.length; i++) {
            ors.add(new int[]{(1 << 30) - 1, i});
            int j = 0;
            for (int[] or : ors) {
                or[0] &= nums[i];

                if (Math.abs(or[0] - target) < ans) {   //判断题目条件
                    ans = Math.min(ans, Math.abs(or[0] - target));
                }

                if (ors.get(j)[0] == or[0]) {
                    ors.get(j)[1] = or[1]; // 原地去重：合并相同值，左端点取靠右的
                } else {
                    ors.set(++j, or);
                }
            }
            ors.subList(j + 1, ors.size()).clear(); // 去重：移除多余元素
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;

    }
    // 898 或
    public int subarrayBitwiseORs(int[] nums) {
        int ans = 0;
        Set<Integer> set = new HashSet<>();
        List<int[]> ors = new ArrayList<>(); // 保存 (右端点为 i 的子数组 OR, 该子数组左端点的最大值)
        for (int i = 0; i < nums.length; i++) {
            ors.add(new int[]{0, i});
            int j = 0;
            for (int[] or : ors) {
                or[0] |= nums[i];
                if (set.add(or[0])) {  //判断题目条件
                    ans++;
                }
                if (ors.get(j)[0] == or[0]) {
                    ors.get(j)[1] = or[1]; // 原地去重：合并相同值，左端点取靠右的
                } else {
                    ors.set(++j, or);
                }
            }
            ors.subList(j + 1, ors.size()).clear(); // 去重：移除多余元素
        }
        return ans;

    }

}
