import java.io.*;
import java.util.*;
public class A2 {
    public static void main(String[] args) {
        A2 solution = new A2();
        int[][] edges = {{0,1},{1,2},{1,3},{4,2}};
        int[][] guesses = {{1,3},{0,1},{1,0},{2,4}};
        int k = 3;
        System.out.println(solution.rootCount(edges , guesses , k));


    }
    List<Integer>[] g;
    int maxn = 100010;
    int cnt  , res;
    Set<Long> init;
    boolean[] count;
    public int rootCount(int[][] edges, int[][] guesses, int k) {
        Set<Long> set = new HashSet<>();
        int n = edges.length + 1; //节点数目
        g = new List[n];
        cnt = 0;
        init = new HashSet<>();
        count = new boolean[n];
        count[0] = true;
        Arrays.setAll(g , e -> new ArrayList<>());
        for (int[] edge : edges){
            int a = edge[0] , b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        for (int[] guess : guesses){
            int x = guess[0] , y = guess[1];
            long key = (long) x * maxn + y;
            set.add(key);
        }
        dfs1(0 , -1 , set);
        res = 0;
        //第二次遍历
        dfs2(0 , -1 , set , cnt , k);
        return res;


    }
    void dfs2(int x, int fa , Set<Long> set , int cnt1 , int k){
        if (cnt1 >= k){
            res++;
        }
        for (int next : g[x]){
            if (next == fa){
                continue;
            }
            int c = cnt1;
            long key1 = (long) x * maxn + next , key2 = (long) next * maxn + x;
            if (set.contains(key1)) c--;
            if (set.contains(key2)) c++;
            dfs2(next , x , set , c , k);


        }
    }
    //第一次遍历 ，统计以0开始的猜对次数
    void dfs1(int x , int fa , Set<Long> set){
        for (int next : g[x]){
            if (next == fa){
                continue;
            }
            long key = (long) x * maxn + next;
            if (set.contains(key)){
                cnt++;
            }
            init.add(key);
            dfs1(next , x , set);
        }
    }


}











