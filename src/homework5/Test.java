package homework5;

public class Test {
    public static void main(String[] args) {
        var location = new Location(123, 233);
        var triangle = new RightTriangle(location, 'T', 3, 4, Direction.LEFT_DOWN);
        for (int i = 0; i < triangle.grids.length; i++) {
            for (int j = 0; j < triangle.grids[0].length; j++) {
                System.out.print(triangle.grids[i][j]);
            }
            System.out.println();
        }
        System.out.println(location);
        System.out.println(triangle.area());

        var location2 = new Location(104, 222);
        var triangle2 = new RightTriangle(location, '~', 2, 2, Direction.RIGHT_DOWN);
        for (int i = 0; i < triangle2.grids.length; i++) {
            for (int j = 0; j < triangle2.grids[0].length; j++) {
                System.out.print(triangle2.grids[i][j]);
            }
            System.out.println();
        }
        System.out.println(location2);
        System.out.println(triangle.area());

        var location3 = new Location(99, 999);
        var triangle3 = new RightTriangle(location, '+', 7, 4, Direction.LEFT_UP);
        triangle3.shrink();
        for (int i = 0; i < triangle3.grids.length; i++) {
            for (int j = 0; j < triangle3.grids[0].length; j++) {
                System.out.print(triangle3.grids[i][j]);
            }
            System.out.println();
        }
    }
}
