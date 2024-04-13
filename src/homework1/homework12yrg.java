package homework1;

import java.util.Scanner;

public class homework12yrg {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        while (n-- > 0) {
            int l = sc.nextInt();
            int a = 0;
            while (true) {
                a++;
                l = l / 7;
                System.out.println(l);
                if (l - 7 < 0) {
                    break;
                }
            }
            System.out.println(a);
            //a+1就是有几位数
        }
    }
}
