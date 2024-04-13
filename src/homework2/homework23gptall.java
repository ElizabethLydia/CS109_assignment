package homework2;

import java.util.Scanner;

public class homework23gptall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read input
        int numBalloons = sc.nextInt();
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        // Check if all balloons can be placed
        boolean canPlaceAllBalloons = canPlaceBalloons(numBalloons, grid);

        // Output the result
        if (canPlaceAllBalloons) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    public static boolean canPlaceBalloons(int numBalloons, int[][] grid) {
        int maxBalloons = 0;

        // Count the maximum number of balloons that can be placed
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 && isBalloonPosition(i, j, grid)) {
                    maxBalloons++;
                }
            }
        }

        // Check if all balloons can be placed
        return numBalloons <= maxBalloons;
    }

    public static boolean isBalloonPosition(int x, int y, int[][] grid) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
                if (grid[newX][newY] == 1) {
                    return false;
                }
            }
        }

        return true;
    }
}