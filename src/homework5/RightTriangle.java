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
    }

    //fillGrids()方法构造
    @Override
    public void fillGrids() {
        grids = new char[height + location.getX()][width + location.getY()];
        for (int i = location.getX(); i < location.getX() + height; i++) {
            for (int j = location.getY(); j < location.getY() + width; j++) {
                double slope = (double) height / width;
                switch (d) {
                    case LEFT_UP:
                        //***
                        //**
                        //*
                        //通过判断斜率的方法判断是否在直角三角形内
                        if (i - location.getX() <= slope * (width - (j - location.getY()))) {
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
                        if (height - (i - location.getX()) <= slope * (width - (j - location.getY()))) {
                            grids[i][j] = pattern;
                        } else {
                            grids[i][j] = ' ';
                        }
                        break;
                    case RIGHT_UP:
                        //***
                        // **
                        //  *
                        if (i - location.getX() <= slope * (j - location.getY())) {
                            grids[i][j] = pattern;
                        } else {
                            grids[i][j] = ' ';
                        }
                        break;
                    case RIGHT_DOWN:
                        //  *
                        // **
                        //***
                        if (height - (i - location.getX()) <= slope * (j - location.getY())) {
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
        for (int i = location.getX(); i < location.getX() + height; i++) {
            for (int j = location.getY(); j < location.getY() + width; j++) {
                if (grids[i][j] == pattern) {
                    count++;
                }
            }
        }return count;
    }

    @Override
    public String toString() {
        return "RightTriangle: " + location + " area=" + area() + " pattern=" + pattern;
    }
}