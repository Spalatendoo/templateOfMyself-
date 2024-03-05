public class gcd {
    //求最小公倍数
    //如果是 求连续一段区间数字的最小公倍数 ， 用cur记录当前最小公倍数 ，当做a ， 迭代向后遍历，新遍历到的数字当作b
    private int LCM (int a , int b){
        int g = gcd(a , b);
        return a * b / g;

    }



    // 求最大公因数
    private int gcd(int a , int b){
        if (a<b) {
            int c = a;
            a = b;
            b = c;
        }
        if (a%b==0) {
            return b;
        }
        return gcd(b, a%b);
    }
}
