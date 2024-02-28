import javax.print.DocFlavor;
import java.util.*;
import java.util.spi.AbstractResourceBundleProvider;

public class A1 {
    public static void main(String[] args) {
        //int[] nums = {-5 , -5};
        //int[] nums = {1,3,2,3,1};
        int[] nums = {2147483647,1073741824,1073741823};
        System.out.println(reversePairs(nums));
    }
    static int N ;
    static int[] todo;
    static int[] cnt;
    public static int reversePairs(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>(); //记录不重复元素
        TreeMap<Long , Integer> map = new TreeMap<>(); //离散化
        int idx = 1;
        for (int x : nums){
            set.add(x);
        }
        for (int x : set){
            map.put((long)x , idx++);
        }
        N = map.size() + 1;
        todo = new int[4 * N];
        cnt = new int[4 * N];
        int res = 0;
        for (int i = nums.length - 1 ; i >= 0 ; i--){
            int v = map.get((long) nums[i]);
            //update(1 , 1 , N , v, v);
            long limit;
            if (nums[i] > 0){
                limit = ((long) nums[i] + 1) / 2 - 1;
            }else {
                limit = nums[i] / 2 - 1;
            }

            Long tov = map.floorKey(limit);
            if (tov == null){
                //没有比它小的，直接continue
                //update(1 , 1 , N , v, v);
                update(1 , 1 , N , v, v);
                continue;
            }else {
                res += query(1 ,1 , N , 1 , map.get(tov));
            }
            update(1 , 1 , N , v, v);

        }
        return res;

    }
    static void maintain(int o){
        cnt[o] = cnt[o * 2] + cnt[o * 2 + 1];
    }

    static void do_(int o , int l , int r){
        cnt[o] += cnt[o / 2] * (r - l + 1);
        todo[o] += todo[o / 2];
    }
    //单点更新
    static void update(int o , int l , int r , int L , int R){
        if (l >= L && r <= R){
            cnt[o]++;
            todo[o]++;
            return;
        }
        int m = (l + r) / 2;
        if (todo[o] != 0){
            do_(o * 2 , l , m );
            do_(o * 2 + 1 , m + 1 , r);
            todo[o] = 0;
        }
        if (m >= L){
            update(o * 2 , l , m , L , R);
        }
        if (m < R){
            update(o * 2 + 1 , m + 1 , r , L , R);
        }
        maintain(o);
    }
    static int query(int o , int l , int r , int L , int R){
        if (l >= L && r <= R){
            return cnt[o];
        }
        int m = (l + r) / 2;
        if (todo[o] != 0){
            do_(o * 2 , l , m );
            do_(o * 2 + 1 , m + 1 , r);
            todo[o] = 0;
        }
        int res = 0;
        if (m >= L){
            res += query(o * 2 , l , m , L , R);
        }
        if (m < R){
            res += query(o * 2 + 1 , m + 1 , r , L , R);
        }
        return res;
    }


}
