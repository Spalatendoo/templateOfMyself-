public class Prime {
    //判断是否为质数
    boolean is_prime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= n / i; i++)
            if (n % i == 0)
                return false;
        return true;
    }


    /**
     * -------------------
     */
    static int N = (int) 1e5 + 10;
    static boolean[] isNotPrime = new boolean[N];
    static {
        isNotPrime[1] = true;
        for (int i = 2 ; i * i < N ; i++){
            if (!isNotPrime[i]){
                for (int j = i * i ; j < N ; j += i){
                    isNotPrime[j] = true;
                }
            }
        }

    }
}
