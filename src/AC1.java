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
        for (int i = 0; i < n; i++) {
            arr[i] = I();
        }
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int x : arr) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        TreeSet<Integer> list = new TreeSet<>();
        for (int x : map.keySet()) {
            if (map.get(x) == 1) {
                min = x;
                break;
            }
            list.add(x);

        }


        boolean flag = false;
        if (list.size() == 0) { //最小值只有一个的情况
            pw.println("YES");
        } else {
            int mmin = list.first();
            for (int x : map.keySet()){
                if (x == mmin) continue;
                if (x % mmin != 0){
                    pw.println("YES");
                    flag = true;
                    break;
                }
            }
            if (!flag) pw.println("NO");

        }
    }







}
