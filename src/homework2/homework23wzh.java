package homework2;

import java.util.Scanner;

public class homework23wzh {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int balllon = in.nextInt();
        int row = in.nextInt();
        int column = in.nextInt();
        int[][] playgroud = new int[row + 2][column];
        for (int m = 1; m < row + 1; m++) {
            for (int n = 0; n < column; n++) {
                playgroud[m][n] = in.nextInt();
            }
        }
        int avaliable = 0;
        for (int j = 0; j < column; j++) {
            for (int i = 1; i < row + 1; i++) {
                if (playgroud[i - 1][j] == 0 & playgroud[i][j] == 0 & playgroud[i + 1][j] == 0) {
                    avaliable++;
                    playgroud[i][j] = 1;
                }
            }
        }
        System.out.println(avaliable >= balllon ? "True" : "False");
    }
}