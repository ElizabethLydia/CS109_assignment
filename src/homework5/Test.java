package homework5;

public class Test {
    public static void main(String[] args) {
        var location = new Location(3, 4);
        var circle = new Circle(location, 'X', 5);
        for (int i = 0; i < circle.grids.length; i++) {
            for (int j = 0; j < circle.grids.length; j++) {
                System.out.print(circle.grids[i][j]);
            }
            System.out.println();
        }
        System.out.println(location);
        System.out.println(circle.area());
    }
}
