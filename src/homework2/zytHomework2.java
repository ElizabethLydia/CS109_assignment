package homework2;

import java.util.Scanner;

public class zytHomework2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean issame = true;
        while (n > 0) {
            String x = sc.next();
            if (x.length() == 1) {
                System.out.println(true);
            } else {
                if (x.length() > 1) {
                    for (int i = 0, j = x.length() - 1; i < j; i++, j--) {
                        if ((String.valueOf(x.charAt(i)).equals(String.valueOf(x.charAt(j))))) {
                            issame = true;
                        } else {
                            issame = false;
                            break;
                        }
                    }
                }
            }
            if (issame == true && x.length() != 1) {
                System.out.println(true);
            } else {
                if (issame == false && x.length() != 1)
                    System.out.println(false);
            }
            n--;
        }
    }
}
