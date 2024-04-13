import java.util.Scanner;

public class homework33 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();//输入m行
        int n = sc.nextInt();//输入n列
        int[][] island = new int[m][n];//定义一个m行n列的二维数据，来填充数字编号
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                island[i][j] = sc.nextInt();//完成每一行的数字编码输入
            }
        }

        int start = sc.nextInt();//输入起始点
        int k = sc.nextInt();//输入最后台风经过的点
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};//定义一个二维数组，表示右下左上四个方向
        //0 1 2 3

        //现在需要找到起始点对应的横纵坐标
        int x = 0;
        int y = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (island[i][j] == start) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        int j = 1;
        int step = 0;
        int count = 0;
        boolean shouldBreak = false;
        while (count < k && !shouldBreak) {
            for (int i = 0; ; i++) {
                int dr = direction[Math.floorMod(i, 4)][0];
                int dc = direction[Math.floorMod(i, 4)][1];
                if (Math.floorMod(i, 4) == 0) {
                    step = 2 * j - 1;
                } else {
                    if (Math.floorMod(i, 4) == 1) {
                        step = 2 * j - 1;
                    } else {
                        if (Math.floorMod(i, 4) == 2) {
                            step = 2 * j;
                        } else {
                            if (Math.floorMod(i, 4) == 3) {
                                step = 2 * j;
                                j++;
                            }
                        }
                    }
                }
                for (int l = 1; l <= step; l++) {
                    x += dr;
                    y += dc;
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        count++;
                        if (count == k) {
                            shouldBreak = true; // 设置标志变量为true
                            break; // 跳出内层循环
                        }
                    }
                }
                if (shouldBreak) {
                    break; // 跳出外层循环
                }
            }
        }
        System.out.println(island[x][y]);
    }
}
