/**
 * 区间修改
 * 求区间和
 *
 * P_218 天际线问题  线段树 + 离散化
 */

public class SegmentTree1 {
    static int N = 1000001;
    static long[] todo;
    static long[] sum;
    static void maintain(int o){
        sum[o] = sum[o * 2] + sum[o * 2 + 1];
    }
    static void build(int o , int l , int r , int[] arr){
        if (l == r){
            sum[o] = arr[l - 1];
            return;
        }
        int m = l + (r - l) / 2;
        build(o * 2 , l , m , arr);
        build(o * 2 + 1 , m + 1 , r , arr);
        maintain(o);
    }
    static void do_(int o , int l , int r ){
        sum[o] += (r - l + 1) * todo[o / 2];
        todo[o] += todo[o / 2];
//        todo[o * 2] +=  todo[o] * (r - l + 1);
//        todo[o * 2 + 1] += todo[o] * (r - l + 1);
        return;
    }
    static void update(int o , int l , int r , int L , int R , int add){
        if(l >= L && r <= R){
            sum[o] += (long) add * (r - l + 1);
            todo[o] += add;
            return;
        }
        int m = (l + r) / 2;
        if (todo[o] != 0){
            do_(o * 2, l , m);
            do_(o * 2 + 1 , m + 1 , r);
            todo[o] = 0;
        }
        if (m >= L){
            update(o * 2 , l , m , L , R , add);
        }
        if (m < R){
            update(o * 2 + 1 , m + 1, r , L ,R , add);
        }
        maintain(o);
    }
    static long query(int o , int l , int r , int L , int R){
        if(l >= L && r <= R){
            return sum[o];
        }
        long res = 0;
        int m = (l + r) >> 1;
        if (todo[o] != 0){
            do_(o * 2 , l , m);
            do_(o * 2 + 1, m + 1 , r);
            todo[o] = 0;
        }
        if (m >= L){
            res += query(o * 2 , l , m , L , R);
        }
        if (m < R){
            res += query(o * 2 + 1 , m + 1 , r , L , R);
        }
        return res;
    }
}
