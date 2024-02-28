/**
 * LC Contest Third Problem
 */

import java.util.*;
import java.io.*;
public class AC3 {
    //static int maxn = 300005,n,m,inf=(int)1e9;
    static long INF = (long)2e18,ans = 0,mod = (int)1e9+7;
    static Scanner sc = new Scanner (System.in);
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer st  =new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    public static void main(String[]args) throws IOException{
        int T = 1;
        T=Integer.parseInt(S());
        while(T-->0) {
            solve();
        }
        pw.flush();
    }
    static final int I() throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
    static String S() throws IOException{
        String res = bf.readLine();
        while(res.equals(""))res=bf.readLine();
        return res;
    }


    static void solve() throws IOException {



    }



}
