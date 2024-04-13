package homework1;

import java.util.Scanner;

public class homework11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        while (n-- > 0) {
            int m = 0;
            int l = sc.nextInt();
            int r = sc.nextInt();
            for (int i = l; i <= r; i++) {
                if (i % 7 == 0) {
                    m++;
                } else {
                    String number = String.valueOf(i);
                    for (int j = 0; j < number.length(); j++) {
                        if (number.charAt(j) == '7') {
                            m++;
                            break;
                        }
                    }
                }
            }
            System.out.println(m);
        }
    }
}
