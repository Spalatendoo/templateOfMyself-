public class quickMi {


    int quick(int x,int n){
        if (n == 0){
            return 1;
        }
        int y  =  quick(x,n / 2);
        return n % 2 == 1 ? y * y * x : y * y;
    }
}
