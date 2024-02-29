/**
 * LC Contest First Problem
 */

import java.util.*;
import java.io.*;
public class AC1 {
    static int maxn = 300005,n,inf=(int)1e9;
    static long INF = (long)2e18,mod = (int)1e9+7;
    static Scanner sc = new Scanner (System.in);
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer st  =new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    public static void main(String[]args) throws IOException{
        int T = 1;
        T=I();
        while(T-- > 0) {
            solve();
        }


        pw.flush();
    }
    static int I() throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
    static String S() throws IOException{
        String res = bf.readLine();
        while(res.equals(""))res=bf.readLine();
        return res;
    }


    static void solve() throws IOException {
        int n = I();
        int[] arr = new int[n];
        for (int i = 0 ; i < n ; i++){
            arr[i] = I();
        }
        int q = I();
        int[][] queries = new int[q][2];
        for (int i = 0 ; i < q ; i++){
            queries[i][0] = I();  // l
            queries[i][1] = I();  // u
        }
        int[] preSum = new int[n + 1];
        for (int i = 1 ; i <= n ; i++){
            preSum[i] = preSum[i-1] + arr[i - 1];
        }
        for (int i = 0 ; i < q ; i++){
            int l = queries[i][0] , u = queries[i][1];  //l 从1开始， 记得-1
            int res = -1;
            int r = Math.max(l - 1 , binarySearch(l - 1 , arr , preSum , u));
            res = r;
            //找到区间[l - 1 , r]
            int sum = preSum[r + 1] - preSum[l - 1]; //一共这么多个段，从 u 一直加到  u+1-sum
            long tot = (long) (u + u + 1 - sum) * sum / 2;
            //判断两边
            if (r > 0 && r >= l){
                //找左边一个位置[l - 1 , r - 1]
                int lsum = preSum[r] - preSum[l - 1];
                long ltot = (long) (u + u + 1 - lsum) * lsum / 2;
                if (tot <= ltot){
                    tot = ltot;
                    res = r - 1;
                }
            }
            if (r < n - 1){
                //找右边一个位置 [l - 1 , r + 1]
                int rsum = preSum[r + 2] - preSum[l - 1];
                long rtot = (long) (u + u + 1 - rsum) * rsum / 2;
                if (rtot > tot){
                    res = r + 1;
                    tot = rtot;
                }
            }
            pw.print(res+1 + " ");

        }
        pw.println();

    }
    //选择靠近u的部分 ，也就是选取的r，满足[l,r]这一段总共有的sum段，靠近u
    static int binarySearch(int l , int[] arr , int[] preSum , int u){

        int high = arr.length - 1 , low = l;
        while (low <= high){
            int mid = low + (high - low) / 2;
            if (preSum[mid + 1] - preSum[l] >= u){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return low - 1;
    }







}
