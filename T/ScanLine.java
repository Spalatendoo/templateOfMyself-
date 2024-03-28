import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 扫描线板子
 *
 * LC 850、218
 */
public class ScanLine {

    //求矩形面积
    public int rectangleArea(int[][] rectangles) {
        int n = rectangles.length , mod = (int)1e9 + 7;
        long ans = 0;
        List<Integer> list = new ArrayList<>(); //维护边界
        for(int[] r : rectangles){
            list.add(r[0]);
            list.add(r[2]);
        }
        Collections.sort(list);

        for (int i = 1 ; i < list.size() ; i++){
            int a = list.get(i - 1) , b = list.get(i) , len = b - a;
            if (len == 0){
                continue;
            }
            List<int[]> lines = new ArrayList<>();
            for (int[] rec : rectangles){
                if (rec[0] <= a && rec[2] >= b){
                    lines.add(new int[]{rec[1] , rec[3]});
                }
            }
            Collections.sort(lines , (l1 , l2) -> l1[0] != l2[0] ? l1[0] - l2[0] : l1[1] - l2[1]);
            long tot = 0 , l = -1 , r = -1;
            for (int[] cur : lines){
                if (cur[0] > r){
                    tot += r - l;
                    l = cur[0];
                    r = cur[1];
                }else if (cur[1] > r){
                    r = cur[1];
                }
            }
            tot += r - l;
            ans += tot * len;
            ans %= mod;

        }
        return (int)ans;
    }
}
