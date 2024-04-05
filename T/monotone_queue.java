import java.util.*;
public class monotone_queue {
    /**
     *
     * 需要不断维护队列的单调性 ， 时刻保证队列元素从大到小或者从小到大
     * 以固定窗口大小的区间最大值为例（此时维护的是一个从大到小的单调队列）：
     * 每次向右移动一格左指针，在移动前，如果左指针指向的元素在队首左侧，说明左指针指向的元素小于队首，移动左指针不会改变区间最大值，直接移动左指针即可；
     * 如果左指针指向的就是队首，那么移动左指针会使区间最大值变小（变为单调队列队首之后的那个元素），我们要弹出队首。
     * 这样无论是何种情况，都保证了在移动左指针后，单调队列的队首始终为当前区间的最大值。
     *
     *
     * 和单调栈思想基本一致， 不一样的是 ， 由于是在一个滑窗内处理出局， 窗口左端数据滑出时， 要及时移出队列 ， 也就是滑窗的左右两端都要处理
     * 单调队列处理的一系列问题是  区间最大/最小值
     * 区间最大最小 也可以用线段树 / ST表 来处理
     */

    //239 滑动窗口最大值
    public int[] solution1(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> q = new ArrayDeque<>();
        int[] ans = new int[n - k + 1];
        int idx = 0;
        for (int i = 0 ; i < k ; i++){
            while (!q.isEmpty() && nums[i] > nums[q.peekLast()]){
                q.pollLast();
            }
            q.offerLast(i);
        }
        ans[idx++] = nums[q.peekFirst()];
        for (int left = 0 , right = k ; right < n ; right++){
            if (nums[left] == nums[q.peekFirst()]){
                q.pollFirst();
            }
            left++;
            while (!q.isEmpty() && nums[right] > nums[q.peekLast()]){
                q.pollLast();
            }
            q.offerLast(right);
            ans[idx++] = nums[q.peekFirst()];
        }
        return ans;
    }
    //代码优化
    public int[] solution2(int[] nums, int k) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n-k+1];
        int count = 0;
        for (int i = 0;i<n;i++){
            //i为nums下标，要在[i-k+1,i]中选出最大值，只需要保证两点
            //1.队列头结点需要在[i-k+1,i]范围内，不符合则要弹出
            while (!deque.isEmpty() && deque.peek() < i-k+1){
                deque.poll();
            }
            // 2.既然是单调，就要保证每次放进去的数字要比末尾的都大，否则也弹出
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.pollLast();
            }
            deque.offer(i);
            //因为单调，当i增长到符合第一个k范围的时候，每滑动一步都将队列头结点放入结果就行了
            if (i >= k-1){
                res[count++] = nums[deque.peek()];
            }
        }

        return res;
    }

    //-----------------------------------------
    // 2398 预算内的最多机器人数目
    //求 符合条件的最长子数组  ， 如果求符合条件的最长子序列？就要用 排序 + 堆 来处理
    //最大子序列参考  1318





}
