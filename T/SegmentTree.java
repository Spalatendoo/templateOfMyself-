public class SegmentTree {
    //开辟静态数组
    int N ;
    int[] todo; // lazy tag
    int[] cnt; // 记录每个节点表示区间 的信息

    void maintain(int o){
        cnt[o] = cnt[o * 2] + cnt[o * 2 + 1];
    }
    void build(int o , int l , int r , int[] nums1){
        if (l == r){
            //建树
            cnt[o] = nums1[l - 1]; //l从1开始
            return;
        }
        int m = l + (r - l) / 2;
        //建左子树 右子树
        build(2 * o , l , m , nums1);
        build(2 * o + 1 , m + 1 , r , nums1);
        //维护
        maintain(o);

    }
    //更新操作 ， 将 l , r 区间翻转，只需要记录该区间1的个数
    void _do(int o , int l , int r){
        //翻转操作
//        cnt[o] = r - l + 1 - cnt[o];
//        todo[o] ^= 1;
    }
    //更新L R 区间
    void update(int o , int l , int r , int L , int R , int add){
        if (l >= L && r <= R){
            //更新
//            todo[o] += add;  //不再继续递归更新
            _do(o , l , r);
            return;

        }


        int m = l + (r - l) / 2;

        //lazy tag 下推
        if (todo[o] != 0){
            //需要继续递归更新， 将todo内容传给左右儿子
//            todo[o * 2] += todo[o];
//            todo[o * 2 + 1] += todo[o];
            _do(o * 2 , l , m);
            _do(o * 2 + 1 , m + 1 , r);
            todo[0] ^= 1; //下推过后 清空
        }
        update(o * 2 , l , m , L , R , add);
        update(o * 2 + 1 , m + 1 , r , L , R , add);

        //维护
        maintain(o);

    }
}
