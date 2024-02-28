import java.util.Arrays;

public class DSU {
    /**
     * 并查集
     */
    int[] fa; //记录连通块
    int[] size; //记录连通块的大小
    int N;
    public DSU(int N){
        //初始化
        this.N = N; //节点数量
        fa = new int[N];
        size = new int[N];
        for (int i = 0 ; i < N ; i++){
            fa[i] = i;
        }
        Arrays.fill(size , 1);
    }
    int find(int x){
        if (x != fa[x]){
            fa[x] = find(fa[x]);
        }
        return fa[x];
    }
    void union(int x , int y){
        int faX = find(x);
        int faY = find(y);
        if (faX != faY){
            fa[faY] = faX;
            size[faX] += size[faY];
        }
    }
}
