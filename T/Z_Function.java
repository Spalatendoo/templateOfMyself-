public class Z_Function {
    /**
     * z-函数
     * 在 O(n) 时间复杂度的情况下进行字符串的前后缀匹配
     * LC题目 3031 将单词恢复初始状态所需的最短时间 II
     *        3036. 匹配模式数组的子数组数目 II
     */
    void zFunc(String s){
        char[] sc = s.toCharArray();
        int n = sc.length;
        //z[i] 表示字符串s第i个位置之后的字符串 可以和从开头开始的字符串能匹配上的最大长度
        int[] z = new int[n];
        int l = 0 , r = 0;
        for (int i = 1 ; i < n ; i++){
            if (i <= r){
                z[i] = Math.min(r - i + 1,z[i - l]);
            }
            while (i + z[i] < n && sc[i + z[i]] == sc[z[i]]){
                l = i ;
                r = i + z[i];
                z[i]++;
            }
//            if (i % k == 0 && i + z[i] >= n){
//                return  i / k;
//            }
        }
//        return (n - 1) / k + 1;
    }
}
