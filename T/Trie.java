import java.util.HashMap;
import java.util.Map;

public class Trie {
    // 当每个Trie的孩子节点固定时(如处理字母字符串 数字字符串) ，可以直接用数组来存储

    /**
     * @N 孩子节点数量 一般为26(a ~ z) 或者 10 (0 ~ 9)
     * @sons 孩子节点
     * @idx idx可以表示遍历到当前节点是否是字符串末尾 ， 也可以是给字符串的编号，编号类型见题2977
     */
    class Trie1{
        int N;
        Trie[] sons;
        int idx;
        public Trie1(){
            N = 26;
            sons = new Trie[N];
            idx = -1;
        }
    }

    //当 key 不固定时，或者没法预先知道 key 时 ， 或者key范围很大时 ， 需要采用map来存储，这样子可以避免爆内存
    //比如 P3045 统计前后缀下标对，这一题将一个 字符pair 转化为数字 ， 然后存储。   ///ps: java中pair可以hash转化为一个数字
    class Trie2{
        Map<Integer, Trie2> son = new HashMap<>();
        int cnt;
    }
}
