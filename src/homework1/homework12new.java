package homework1;

import java.util.Scanner;

public class homework12new {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long d;

        while (n-- > 0) {
            //在每一个测试用例中输入需要转换的原本的10进制数字
            d = sc.nextInt();
            long answer = 0;
            int m = 0;
            int add = 0;
            //定义一个D使它的数值和d相同，这样每次对D进行改变，没有真正改变d的值
            long D = d;
            while (D > 0) {
                long temp = D % 7;
                double pow = Math.pow(10,m);
                long pow2 = (long) pow;
                answer = answer + temp * pow2;
                m++;
                D = D / 7;
            }
            System.out.println(answer);
        }
    }
}