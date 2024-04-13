package homework1;

import java.util.*;

public class homework12wzh {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int times = input.nextInt();
        for (int i = 0; i < times; i++) {
            long decimal = input.nextInt();
            double n = 0;
            while (Math.pow(7, n) <= decimal) {
                n++;
            }
            n--;
            double x = n;
            long sum = 0;
            long d = decimal;
            while (x >= 0) {
                sum += ((d / (long) Math.pow(7, x)) * (long) Math.pow(10, x));
                d %= (long) Math.pow(7, x);
                x--;
            }

            System.out.println(sum);

        }
   /*     for (int i = 0; i < times; i++) {
            int x= input.nextInt();
            int a=7;
            int b=10;
            int vii=0;
            int n=1;
            int x1=x;
            if(a>x){
                vii=x;
            }else{
                while (a<=x){
                    a*=7;
                    n++;
                    b*=10;
                }
                a=a/7;
                n--;
                b=b/10;
                for (int m=n ;m>=1; m--) {
                    vii+=(x1/a)*b;
                    x1%=a;
                    a=a/7;
                    n--;
                    b=b/10;
                }
                vii+=x1;

            }
            System.out.println(vii);
        }*/
    }
}