import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        Dijkstra d = new Dijkstra();
        //[2,1,1],[2,3,1],[3,4,1]
        int[][] edges = {{2,1,1},{2,3,1},{3,4,1}};
        int n = 4 , k = 2;
        System.out.println(d.dijk2(edges , n , k));
    }


    //节点从1开始编号
    //朴素Dijkstra
    //单源最短路
    //求节点k到其他各个点的最短路径
    //复杂见 O(n ^ 2)
    public int[] dijk1(int[][] edges, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2; // 防止加法溢出
        int[][] g = new int[n][n]; // 邻接矩阵
        for (int[] row : g) {
            Arrays.fill(row, INF);
        }
        for (int[] t : edges) {
            g[t[0] - 1][t[1] - 1] = t[2];
        }

        int maxDis = 0;
        int[] dis = new int[n];
        Arrays.fill(dis, INF);
        dis[k - 1] = 0;
        boolean[] done = new boolean[n];
        while (true) {
            int x = -1;
            for (int i = 0; i < n; i++) {
                if (!done[i] && (x < 0 || dis[i] < dis[x])) {
                    x = i;
                }
            }
            if (x < 0) {
                break; // 最后一次算出的最短路就是最大的
            }
            if (dis[x] == INF) { // 有节点无法到达
                break;
            }
            // maxDis = dis[x]; // 求出的最短路会越来越大
            done[x] = true; // 最短路长度已确定（无法变得更小）
            for (int y = 0; y < n; y++) {
                // 更新 x 的邻居的最短路
                dis[y] = Math.min(dis[y], dis[x] + g[x][y]);
            }
        }
        return dis;
    }


    //堆优化Dijkstra
    //复杂度 O(mlogm)  m是边数量
    int[] dijk2(int[][] edges, int n, int k) {
        List<int[]>[] g = new ArrayList[n]; // 邻接表
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] t : edges) {
            g[t[0] - 1].add(new int[]{t[1] - 1, t[2]});
        }

        //int maxDis = 0;
        //int left = n; // 未确定最短路的节点个数
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[k - 1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        pq.offer(new int[]{0, k - 1});
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int dx = p[0];
            int x = p[1];
            if (dx > dis[x]) { // x 之前出堆过
                continue;
            }
//            maxDis = dx; // 求出的最短路会越来越大
//            left--;
            for (int[] e : g[x]) {
                int y = e[0];
                int newDis = dx + e[1];
                if (newDis < dis[y]) {
                    dis[y] = newDis; // 更新 x 的邻居的最短路
                    pq.offer(new int[]{newDis, y});
                }
            }
        }
        return dis;
    }



}
