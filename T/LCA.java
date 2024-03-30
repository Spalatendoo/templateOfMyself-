import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LCA {
    /**
     * P_1483 树节点的第K个祖先
     * 树上倍增
     * LCA 树上倍增板子
     */
    int[][] pa;
    //预处理每个节点的第 2^i 个祖先
    public void predo(int n , int[]parent){
        int m = 32 - Integer.numberOfLeadingZeros(n); //二进制长度
        pa = new int[n][m];
        for (int i = 0 ; i < n ; i++){
            pa[i][0] = parent[i]; // 每个节点的父亲节点编号
        }
        for (int i = 0 ; i < m - 1 ; i++){
            for (int x = 0 ; x < n ; x++){
                int p = pa[x][i];
                pa[x][i + 1] = p < 0 ? -1 : pa[p][i];
            }
        }
    }
    //获得node节点的第k个祖先
    public int getKthAncestor(int node , int k){
        int m = 32 - Integer.numberOfLeadingZeros(k);
        for (int i = 0 ; i < m ; i++){
            if ((k >> i & 1) == 1){
                node = pa[node][i];
                if (node < 0) break;
            }
        }
        return node;
    }

    //------------------------------------------
    /**
     * LCA 板子
     * 通常题目用 edges 方式输入的， 针对该题先用edges 建图
     */
    int[] depth;
    int[][] pa1;
    public void predo(int[][] edges){
        int n = edges.length + 1;
        int m = 32 - Integer.numberOfLeadingZeros(n);
        List<Integer>[] g = new List[n];
        Arrays.setAll(g , e -> new ArrayList<>());
        for (int[] e : edges){
            int x = e[0] , y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        depth = new int[n];
        pa = new int[n][m];
        dfs(g , 0 , -1); //拿到每一个节点的深度
        //预处理pa1数组
        for (int i = 0 ; i < m - 1 ; i ++){
            for (int x = 0 ; x < n ; x++){
                int p = pa[x][i];
                pa[x][i + 1] = p < 0 ? -1 : pa[p][i];
            }
        }

    }
    //预处理每个节点的深度
    public void dfs(List<Integer>[] g , int x , int fa){
        pa1[x][0] = fa;
        for (int ne : g[x]){
            if (ne != fa){
                depth[ne] = depth[x] + 1;
                dfs(g , ne , x);
            }
        }
    }
    public int getKthAncestor1(int node , int k){
        for (; k > 0 ; k &= k - 1){
            node = pa[node][Integer.numberOfTrailingZeros(k)]; //从低位第一个1开始
        }
        return node;
    }

    public int getLCA(int x , int y){
        if (depth[x] > depth[y]){
            int tmp = y ;
            y = x ;
            x = tmp;
        }
        //使得 x和y 在同一深度
        y = getKthAncestor1(y , depth[y] - depth[x]);
        if (y == x){
            return x;
        }
        for (int i = pa[x].length - 1 ; i >= 0 ; i--){
            int px = pa[x][i] ,  py = pa[y][i];
            if (px != py){
                x = px;
                y = py;
            }
        }
        return pa[x][0];
    }


    int[] numOfChild;
    public void cacul(int n , int[][] edges){
        List<Integer> g[] = new ArrayList[n];
        Arrays.setAll(g , e -> new ArrayList<>());
        for (int[] e : edges){
            int x = e[0] , y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        dfs1(g , 0 , -1);

    }
    //预处理每个节点的子节点数量
    int dfs1(List<Integer>[] g , int x , int fa){
        int cnt = 0;
        for (int ne : g[x]){
            if (ne != fa){
                cnt += dfs1(g , ne , x) + 1;
            }
        }
        numOfChild[x] = cnt;
        return cnt;
    }



}
