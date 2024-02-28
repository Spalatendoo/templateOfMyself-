public class StringHash {
    private static final int p = 131;
    public final long[] hash , prime;
    public StringHash(String str){
        int n = str.length();
        hash = new long[n + 10];
        prime = new long[n + 10];
        prime[0] = 1;
        for (int i = 0 ; i < n ; i++){
            prime[i + 1] = prime[i] * p;
            hash[i + 1] = hash[i] * p + str.charAt(i);
        }
    }
    //获得 字符串text [i,j) 的哈希值  i从0开始
    public long getSubStrHash(int i , int j){
        return hash[j] - hash[i] * prime[j - i];
    }
}
