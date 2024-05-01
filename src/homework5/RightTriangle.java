package homework5;

public class RightTriangle extends Shape {
    //fields: width, height, d
    private int width;//[1-20]
    //The width of RightTriangle, and it is also the width of grids.
    private int height;//[1-20]
    //The height of RightTriangle, and it is also the height of grids.
    private final Direction d;
    //The direction of the right angle of a right triangle.

    //constructor
    //In RightTriangle, design a constructor with five attributes such as:
    //public RightTriangle(Location location, char pattern, int width, int height, Direction d);
    //Then given those five fields a initial value.
    public RightTriangle(Location location, char pattern, int width, int height, Direction d) {
        super(location, pattern);
        this.width = width;
        this.height = height;
        this.d = d;
        fillGrids();
    }

    //fillGrids()方法构造
    @Override
    public void fillGrids() {
        grids = new char[height][width];
        //初始化网格
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grids[i][j] = ' ';
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double slope = (double) height / width;
                switch (d) {
                    case LEFT_UP:
                        //***
                        //**
                        //*
                        //通过判断斜率的方法判断是否在直角三角形内
                        if (i <= slope * (width - j)) {
                            grids[i][j] = pattern;
                        } else {
                            grids[i][j] = ' ';
                        }
                        break;
                    case LEFT_DOWN:
                        //*
                        //**
                        //***
                        //通过判断斜率的方法判断是否在直角三角形内
                        if (height - i - 1 <= slope * (width - j - 1)) {
                            grids[i][j] = pattern;
                        } else {
                            grids[i][j] = ' ';
                        }
                        break;
                    case RIGHT_UP:
                        //***
                        // **
                        //  *
                        if ((i + 1) <= slope * (j + 1)) {
                            grids[i][j] = pattern;
                        } else {
                            grids[i][j] = ' ';
                        }
                        break;
                    case RIGHT_DOWN:
                        //  *
                        // **
                        //***
                        //没有考虑斜率为0的情况
                        if (height - (i + 1) <= slope * (j + 1)) {
                            grids[i][j] = pattern;
                        } else {
                            grids[i][j] = ' ';
                        }
                        break;
                }
            }
        }
    }

    @Override
    public void enlarge() {
        width++;
        height++;
        fillGrids();
    }

    @Override
    public void shrink() {
        width--;
        height--;
        fillGrids();
    }

    @Override
    public int area() {
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grids[i][j] == pattern) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "RightTriangle: " + location + " area=" + area() + " pattern=" + pattern;
    }
}