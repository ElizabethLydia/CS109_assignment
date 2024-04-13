package homework31;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> str1 = new ArrayList<>();
        ArrayList<String> str2 = new ArrayList<>();
        int n = input.nextInt();
        String[] in1 = new String[n];
        String[][] in = new String[n][3];
        for (int i = 0; i < in1.length; i++) {
            in1[i] = input.next();
            String[] d = in1[i].split(",");
            for (int j = 0; j < d.length; j++) {
                in[i][j] = d[j];
            }
        }
        for (int i = 0; i < in.length; i++) {
            if (in[i][0].equals("R")) {
                for (int j = 0; j < in[i].length; j++) {
                    str1.add(in[i][j]);
                }
            } else if (in[i][0].equals("C")) {
                for (int j = 0; j < in[i].length; j++) {
                    str2.add(in[i][j]);
                }
            }
        }
        String[][] R1;
        R1 = S(str1);
        String[][] C1;
        C1 = S(str2);
        String[][] R;
        R = sort1(R1);
        String[][] C;
        C = sort2(C1);
        boolean judge = judge(R, C);
        if (judge) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static String[][] S(ArrayList<String> str) {
        int k = 0;
        String[][] S = new String[str.size() / 3][3];
        for (int i = 0; i < str.size() / 3; i++) {
            for (int j = 0; j < 3; j++) {
                S[i][j] = str.get(k);
                k++;
            }
        }
        return S;
    }

    public static String[][] sort1(String[][] s) {
        String[][] S = new String[s.length][s[0].length];
        for (int i = 1; i < s.length; i++) {
            for (int j = 0; j < s.length - i; j++) {
                if (Integer.parseInt(s[j][2]) > Integer.parseInt(s[j + 1][2])) {
                    String[] temp = s[j];
                    s[j] = s[j + 1];
                    s[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                S[i][j] = s[i][j];
            }
        }
        return S;
    }

    public static String[][] sort2(String[][] s) {
        String[][] S = new String[s.length][s[0].length];
        for (int i = 1; i < s.length; i++) {
            for (int j = 0; j < s.length - i; j++) {
                if (Integer.parseInt(s[j][2]) < Integer.parseInt(s[j + 1][2])) {
                    String[] temp = s[j];
                    s[j] = s[j + 1];
                    s[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                S[i][j] = s[i][j];
            }
        }
        return S;
    }

    public static Boolean judge(String[][] R, String[][] C) {
        boolean judge = true;
        String[] zero = new String[]{String.valueOf(0), String.valueOf(0), String.valueOf(0)};
        if (C.length > R.length) {
            judge = false;
        } else {
            loop:
            for (int i = 0; i < C.length; i++) {
                for (int j = 0; j < R.length; j++) {
                    if (C[i][1].equals(R[j][1])) {
                        if (Integer.parseInt(C[i][2]) <= Integer.parseInt(R[j][2])) {
                            R[j] = zero;
                            break loop;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return judge;
    }
}
