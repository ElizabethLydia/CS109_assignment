package homework5;

public class Circle extends Shape {
    //fields: radius
    private int radius;//[1-15]
    //The height and width of grids are all radius*2

    //constructor

    //In Circle, design a constructor with three attributes such as:
    //public Circle(Location location, char pattern, int radius);
    //Then given those three fields a initial value.
    public Circle(Location location, char pattern, int radius) {
        super(location, pattern);
        this.radius = radius;
    }

    //fillGrids()方法构造
    @Override
    public void fillGrids() {
        int diameter = radius * 2;
        grids = new char[diameter + location.getX()][diameter + location.getY()];
        for (int i = location.getX(); i < location.getX() + diameter; i++) {
            for (int j = location.getY(); j < location.getY() + diameter; j++) {
                //首先把圆形的每个格子都填充为空格
                grids[i][j] = ' ';
            }
        }
        for (int i = location.getX(); i < location.getX() + diameter; i++) {
            for (int j = location.getY(); j < location.getY() + diameter; j++) {
                //将圆形分为四个板块，分别判断每个板块中的点是否在圆内
                //左上角的板块
                if (i <= location.getX() + radius - 1 && j <= location.getY() + radius - 1) {
                    int x = location.getX() + radius - 1;
                    int y = location.getY() + radius - 1;
                    if ((i - x) * (i - x) + (j - y) * (j - y) <= radius * radius) {
                        grids[i][j] = pattern;
                    } else {
                        grids[i][j] = ' ';
                    }
                }
                //右上角的板块
                if (i <= location.getX() + radius - 1 && j >= location.getY() + radius) {
                    int x = location.getX() + radius - 1;
                    int y = location.getY() + radius;
                    if ((i - x) * (i - x) + (j - y) * (j - y) <= radius * radius) {
                        grids[i][j] = pattern;
                    } else {
                        grids[i][j] = ' ';
                    }
                }
                //左下角的板块
                if (i >= location.getX() + radius && j <= location.getY() + radius - 1) {
                    int x = location.getX() + radius;
                    int y = location.getY() + radius - 1;
                    if ((i - x) * (i - x) + (j - y) * (j - y) <= radius * radius) {
                        grids[i][j] = pattern;
                    } else {
                        grids[i][j] = ' ';
                    }
                }
                //右下角的板块
                if (i >= location.getX() + radius && j >= location.getY() + radius) {
                    int x = location.getX() + radius;
                    int y = location.getY() + radius;
                    if ((i - x) * (i - x) + (j - y) * (j - y) <= radius * radius) {
                        grids[i][j] = pattern;
                    } else {
                        grids[i][j] = ' ';
                    }
                }
            }
        }
    }

    //enlarge()方法构造
    @Override
    public void enlarge() {
        //In Circle, the radius should be increased by 1.
        radius++;
        //Both Circle and RightTriangle should update grids by the enlarged fields.
        fillGrids();
    }

    //shrink()方法构造
    @Override
    public void shrink() {
        //In Circle, the radius should be decreased by 1.
        radius--;
        //Both Circle and RightTriangle should update grids by the shrunken fields.
        if (radius >= 1) {
            fillGrids();
//            //如果在缩小前的某些位置花了图，现在缩小后的位置没有图，那么这些位置应该怎么处理？
//            //这里的处理方法是，如果缩小后的位置没有图，那么就填充为空格。
//            for (int i = location.getX(); i < location.getX() + (radius+1) * 2; i++) {
//                for (int j = location.getY() + 2 * radius; j < location.getY() + (radius + 1) * 2; j++) {
//                    if (grids[i][j] == pattern) {
//                        grids[i][j] = ' ';
//                    }
//                }
//            }
//            for (int i = location.getX() + 2 * radius; i < location.getX() + (radius + 1) * 2; i++) {
//                for (int j = location.getY(); j < location.getY() + radius * 2; j++) {
//                    if (grids[i][j] == pattern) {
//                        grids[i][j] = ' ';
//                    }
//                }
//            }
        } else {
            radius = 1;
            fillGrids();
        }
    }

    //area()方法构造
    @Override
    public int area() {
        //In Circle, the area should be the count of patterns that being filled in grids.
        int count = 0;
        for (int i = location.getX(); i < location.getX() + radius * 2; i++) {
            for (int j = location.getY(); j < location.getY() + radius * 2; j++) {
                if (grids[i][j] == pattern) {
                    count++;
                }
            }
        }
        return count;
    }

    //toString()方法构造
    @Override
    public String toString() {
        //In Circle, the format should be "Circle: (x,y) area=area pattern=pattern".
        return String.format("Circle: %s area=%d pattern=%c", location.toString(), area(), pattern);
    }
}
