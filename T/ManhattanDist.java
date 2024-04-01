import java.util.*;
public class ManhattanDist {
    //O(N) 曼哈顿距离求最大


    //有序集合 O(nlogn)
    public int maxDistance(int[][] points) {
        TreeMap<Integer, Integer> xs = new TreeMap<>();
        TreeMap<Integer, Integer> ys = new TreeMap<>();
        for (int[] p : points){
            xs.put(p[0] + p[1] , xs.getOrDefault(p[0] + p[1] , 0) + 1);
            ys.put(p[1] - p[0] , ys.getOrDefault(p[1] - p[0] , 0) + 1);
        }
        return Math.max(xs.lastKey() - xs.firstKey() , ys.lastKey() - ys.firstKey());
    }



    //LC 3102 移除一个点，最小化任意两点距离最大值

    //有序集合 ， 整体复杂度为O(nlogn)
    public int minimumDistance(int[][] points) {
        TreeMap<Integer, Integer> xs = new TreeMap<>();
        TreeMap<Integer, Integer> ys = new TreeMap<>();
        for (int[] p : points){
            xs.put(p[0] + p[1] , xs.getOrDefault(p[0] + p[1] , 0) + 1);
            ys.put(p[1] - p[0] , ys.getOrDefault(p[1] - p[0] , 0) + 1);
        }
        int ans = Integer.MAX_VALUE;
        //依次枚举要移除的点
        for (int[] p : points){
            int x = p[0] + p[1] , y = p[1] - p[0];
            if(xs.get(x) == 1){
                xs.remove(x);
            }
            if (ys.get(y) == 1){
                ys.remove(y);
            }
            ans = Math.min(ans , Math.max(xs.lastKey() - xs.firstKey() , ys.lastKey() - ys.firstKey()));
            //将移除的再补回来
            if (!xs.containsKey(x)){
                xs.put(x , 1);
            }
            if (!ys.containsKey(y)){
                ys.put(y , 1);
            }
        }
        return ans;
    }

    //使用数组维护，并维护最大 次大  最小 次小值 ， 实现移除某个点后O(1)求最大值
    public int minimumDistance1(int[][] points) {

        int ans = Integer.MAX_VALUE , n = points.length ;
        int[] plus = new int[n] , sub = new int[n];
        int idx = 0;
        for (int[] p : points){
            plus[idx] = p[0] + p[1];
            sub[idx] = -p[0] + p[1];
            idx++;
        }

        int[] rec1 = helper(plus);
        int[] rec2 = helper(sub);
        for (int i = 0 ; i < n ; i++){
            int m1 = points[i][0] + points[i][1] , m2 = -points[i][0] + points[i][1]  ;
            int mx1 = rec1[0] , mn1 = rec1[1] , mx2 = rec2[0] , mn2 = rec2[1];

            mx1 = m1 == mx1 ? rec1[2] : mx1;
            mn1 = m1 == mn1 ? rec1[3] : mn1;
            mx2 = m2 == mx2 ? rec2[2] : mx2;
            mn2 = m2 == mn2 ? rec2[3] : mn2;

            int cm = Math.max(mx1 - mn1 , mx2 - mn2);
            ans = Math.min(ans , cm);
        }
        return ans;
    }


    //求数组的最大、次大值 最小、次小值

    /**
     *
     * @param a
     * a[0]-最大值    a[1]-最小值    a[2]-次大值   a[3]-次小值
     * @return
     */
    int[] helper( int[] a){
        int[] ans = new int[4];
        ans[0] = Integer.MIN_VALUE ; ans[1] = Integer.MAX_VALUE ; ans[2] = Integer.MIN_VALUE ; ans[3] = Integer.MAX_VALUE;
        for (int i = 0 ; i < a.length ; i++){
            int x = a[i];
            if (x > ans[2]){
                if (x > ans[0]){
                    ans[2] = ans[0];
                    ans[0] = x;

                }else {
                    ans[2] = x;
                }
            }
            if (x < ans[3]){
                if (x < ans[1]){
                    ans[3] = ans[1];
                    ans[1] = x;

                }else {
                    ans[3] = x;
                }
            }
        }
        return ans;
    }

}
