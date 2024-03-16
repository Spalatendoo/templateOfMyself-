import java.util.*;
public class Circle {
    /**
     *
     * 内向基环树
     * 由于每个大小为 k 的连通块都有 k 个点和 k 条边，所以每个连通块必定有且仅有一个环，
     * 且由于每个点的出度均为 1，这样的有向图又叫做内向基环树 (pseudotree)，由基环树组成的森林叫基环树森林 (pseudoforest)。
     *
     * 常规做法 ， 拓扑去掉树枝 ， 剩下的环bfs来找
     */

    //LC 2306  图中的最长环
    public int longestCycle(int[] edges) {
        int n = edges.length;
        int ans = 0;
        int[] in = new int[n];  //统计入度
        for (int i = 0 ; i < n ; i++){
            if (edges[i] == -1){
                continue;
            }
            in[edges[i]]++;
        }
        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0 ; i < n ; i++){
            if (in[i] == 0){
                queue.add(i);
            }
        }
        //剪去树枝
        while (!queue.isEmpty()){
            int node = queue.pollFirst();
            if (edges[node] == -1){
                continue;
            }
            in[edges[node]]--;
            if (in[edges[node]] == 0){
                queue.addLast(edges[node]);
            }
        }

        //bfs找环
        for (int i = 0 ; i < n ; i++){
            if (in[i] == 0 || visited[i]){
                continue;
            }
            ans = Math.max(ans , bfs(i , 0 , visited , edges));
        }
        return ans == 0 ? -1 : ans;

    }
    int bfs(int x , int step , boolean[] visited , int[] edges){
        while (true){
            if (visited[x]){
                return step;
            }
            visited[x] = true;
            x = edges[x];
            step++;
        }
    }

    //-------------------------------------------------------------
    /**
     * 三色染色法 在无向图找环
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0 ; i < n ; i++){
            if (isSafe(graph , color , i)){
                //如果不在环上
                ans.add(i);
            }
        }
        return ans;


    }
    //0 还没访问到 ，
    // 1 已经访问到一次了
    // 2 访问了一圈 ，没被重复访问到，说明不在环上，标记为2
    boolean isSafe(int[][] graph , int[] color , int x){
        if (color[x] > 0){
            return color[x] == 2;
        }
        color[x] = 1;
        for (int ne : graph[x]){
            if (!isSafe(graph , color , ne)){
                return false;
            }
        }
        color[x] = 2;
        return true;
    }

    //------------------------------------------------
    /**
     * bfs 在无向图中找最短环
     *
     */
    int[] dist;
    List<Integer>[] g ; //建图
    //int ans ;
    public int findShortestCycle(int n, int[][] edges) {
        boolean[] visited = new boolean[n];
        int ans = 10001;
        dist = new int[n];
        g = new List[n];
        Arrays.setAll(g , e -> new ArrayList<>());
        for (int[] e : edges){
            int a = e[0] , b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = 0 ; i < n ; i++){
            ans = Math.min(ans , bfs(i));
        }
        return ans == 10001 ? -1 : ans;
    }
    //bfs 找环
    int bfs(int start){
        int ans = Integer.MAX_VALUE;
        Arrays.fill(dist , -1);
        dist[start] = 0;
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{start , -1});
        while (!dq.isEmpty()){
            int size = dq.size();
            int[] p = dq.pollFirst();
            int node = p[0] , fa = p[1];
            for (int ne : g[node]){
                if (dist[ne] < 0){
                    //第一次遇到
                    dist[ne] = dist[node] + 1;
                    dq.add(new int[]{ne , node});
                }else if (ne != fa){
                    //第二次遇到
                    ans = Math.min(ans , dist[node] + dist[ne] + 1);
                }
            }
        }
        return ans;
    }
}
