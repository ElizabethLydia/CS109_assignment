import java.util.Scanner;
//没有写完
public class homework33new {
    public void main(String[] args) {
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
        Person person = new Person(x, y, 0);
        int count = 0;
        boolean shouldBreak = false;
        while (count < k && !shouldBreak) {
            int step = 1;
            for (int c = 1; c <=step ; c++) {//走step步
                person.walk();
                if (person.i >= 0 && person.i < m && person.j >= 0 && person.j < n) {
                    count++;
                }
                if (count == k) {
                    shouldBreak = true;
                    break;
                }
            }
            person.changeDirection();
        }
    }

    class Person {
        int i;
        int j;
        int index;
        Direction[] directions;

        public Person(int i, int j, int index) {
            this.i = i;
            this.j = j;
            this.index = index;
            this.directions = new Direction[4];
            directions[0] = new Direction(0, 1);//右
            directions[1] = new Direction(1, 0);//下
            directions[2] = new Direction(0, -1);//左
            directions[3] = new Direction(-1, 0);//上

        }

        public void changeDirection() {
            index++;
            if (index == 4) {
                index = 0;
            }
        }

        public void walk() {
            int dr = directions[index].row;
            int dc = directions[index].col;
        }

        public String toString() {
            return String.format("(%d, %d)", i, j);
        }
    }

    class Direction {
        int row;
        int col;

        public Direction(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}