/**
 * 树状数组
 */
public class Fenwick {
    private final int[] tree;
    public Fenwick(int n){
        tree = new int[n];
    }

    //把下标为i的元素增加v
    public void add(int i , int v){
        while (i < tree.length){
            tree[i] += v;
            // i & -i    lowbit
            i += i & -i;
        }
    }

    //返回下标在[1 , i] 的元素之和
    public int preSum(int i){
        int res = 0;
        while (i > 0){
            res += tree[i];
            //减去lowbit
            //写成 i -= i & -i； 也可以
            i &= i - 1;
        }
        return res;
    }
}

