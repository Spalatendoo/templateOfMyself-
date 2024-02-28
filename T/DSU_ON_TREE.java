import java.util.ArrayList;
import java.util.List;

public class DSU_ON_TREE {
    /**
     * 树上启发式合并
     * codeforces  600E  Lomsat gelral
     */
    static int N = (int) 1e5 + 5;
    static int[] son = new int[N]; //记录每个节点对应的重儿子
    static List<Integer>[] edge = new List[N];
    static int[] cnt = new int[N]; //编号i颜色的数量
    static int[] col = new int[N];
    static int[] siz = new int[N];  //每个节点下的儿子数量
    static long[] ans = new long[N];
    static long max = 0 , sum = 0;
    static {
        for (int i = 0 ; i < N ;i++){
            edge[i] = new ArrayList<>();
        }
    }



    static void dfs2 (int x, int fa , int opt){
        //先搜轻儿子
        for (int ne : edge[x]){
            if (ne != fa && ne != son[x]){
                dfs2(ne , x , 0);
            }
        }
        //如果有重儿子
        if (son[x] != 0){
            dfs2(son[x] , x , 1 );
        }
        //加到重儿子上
        add(x , fa , son[x]);
        ans[x] = sum;
        //清空权重
        if (opt == 0){
            sub(x , fa);
            sum = 0 ;
            max = 0;
        }

    }
    //求每个子节点的重儿子
    static void dfs1(int x , int fa){
        siz[x] = 1;
        for (int ne : edge[x]){
            if (ne != fa){
                dfs1(ne , x);
                siz[x] += siz[ne];
                if (siz[ne] > siz[son[x]]){
                    son[x] = ne;
                }
            }
        }
    }
    static void add(int x, int fa , int son){
        cnt[col[x]]++;
        if (cnt[col[x]] > max){
            max = cnt[col[x]];
            sum = col[x];
        }else if (cnt[col[x]] == max){
            sum += col[x];
        }
        for (int ne : edge[x]){
            if (ne != fa && ne != son){
                add(ne , x ,son);
            }
        }
    }
    static void sub(int x , int fa){
        cnt[col[x]]--;
        for (int ne : edge[x]){
            if (ne != fa){
                sub(ne , x);
            }
        }
    }
}
