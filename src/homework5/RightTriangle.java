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
                        if ((i + 0.5) < (slope * (width - j - 0.5))) {
                            grids[i][j] = pattern;
                        } else {
                            double[] x = new double[3];
                            double[] y = new double[3];
                            double[] multi = new double[2];
                            x[0] = x[1] = i - 0.5;
                            x[2] = i + 0.5;
                            y[0] = y[2] = j - 0.5;
                            y[1] = j + 0.5;
                            multi[0] = ((x[0] + 0.5) - slope * (width - y[0] - 0.5)) * ((x[2] + 0.5) - slope * (width - y[2] - 0.5));
                            multi[1] = ((x[0] + 0.5) - slope * (width - y[0] - 0.5)) * ((x[1] + 0.5) - slope * (width - y[1] - 0.5));
                            int count = 0;
                            for (int k = 0; k < 2; k++) {
                                if (multi[k] < 0) {
                                    count++;
                                }
                            }
                            if (count > 0) {
                                grids[i][j] = pattern;
                            } else {
                                grids[i][j] = ' ';
                            }
                        }
                        break;
                    case LEFT_DOWN:
                        //*
                        //**
                        //***
                        //通过判断斜率的方法判断是否在直角三角形内
                        if (height - i - 0.5 <= slope * (width - j - 0.5)) {
                            grids[i][j] = pattern;
                        } else {
                            double[] x = new double[3];
                            double[] y = new double[3];
                            double[] multi = new double[2];
                            x[0] = i - 0.5;
                            x[1] = x[2] = i + 0.5;
                            y[0] = y[1] = j - 0.5;
                            y[2] = j + 0.5;
                            multi[0] = ((height - x[0] - 0.5) - slope * (width - y[0] - 0.5)) * ((height - x[1] - 0.5) - slope * (width - y[1] - 0.5));
                            multi[1] = ((height - x[2] - 0.5) - slope * (width - y[2] - 0.5)) * ((height - x[1] - 0.5) - slope * (width - y[1] - 0.5));
                            int count = 0;
                            for (int k = 0; k < 2; k++) {
                                if (multi[k] < 0) {
                                    count++;
                                }
                            }
                            if (count > 0) {
                                grids[i][j] = pattern;
                            } else {
                                grids[i][j] = ' ';
                            }
                        }
                        break;
                    case RIGHT_UP:
                        //***
                        // **
                        //  *
                        if ((i + 0.5) <= slope * (j + 0.5)) {
                            grids[i][j] = pattern;
                        } else {
                            double[] x = new double[3];
                            double[] y = new double[3];
                            double[] multi = new double[2];
                            x[0] = x[1] = i - 0.5;
                            x[2] = i + 0.5;
                            y[0] = j - 0.5;
                            y[1] = y[2] = j + 0.5;
                            multi[0] = ((x[0] + 0.5) - slope * (y[0] + 0.5)) * ((x[1] + 0.5) - slope * (y[1] + 0.5));
                            multi[1] = ((x[1] + 0.5) - slope * (y[1] + 0.5)) * ((x[2] + 0.5) - slope * (y[2] + 0.5));
                            int count = 0;
                            for (int k = 0; k < 2; k++) {
                                if (multi[k] < 0) {
                                    count++;
                                }
                            }
                            if (count > 0) {
                                grids[i][j] = pattern;
                            } else {
                                grids[i][j] = ' ';
                            }
                        }
                        break;
                    case RIGHT_DOWN:
                        //  *
                        // **
                        //***
                        if ((height - i - 0.5) <= slope * (j + 0.5)) {
                            grids[i][j] = pattern;
                        } else {
                            double[] x = new double[3];
                            double[] y = new double[3];
                            double[] multi = new double[2];
                            x[0] = i - 0.5;
                            x[1] = x[2] = i + 0.5;
                            y[1] = j - 0.5;
                            y[0] = y[2] = j + 0.5;
                            multi[0] = ((height - x[0] - 0.5) - slope * (y[0] + 0.5)) * ((height - x[2] - 0.5) - slope * (y[2] + 0.5));
                            multi[1] = ((height - x[1] - 0.5) - slope * (y[1] + 0.5)) * ((height - x[2] - 0.5) - slope * (y[2] + 0.5));
                            int count = 0;
                            for (int k = 0; k < 2; k++) {
                                if (multi[k] < 0) {
                                    count++;
                                }
                            }
                            if (count > 0) {
                                grids[i][j] = pattern;
                            } else {
                                grids[i][j] = ' ';
                            }
                        }
                        break;
                }
            }
        }
    }
    //fillGrids()方法构造
//    @Override
//    public void fillGrids() {
//        grids = new char[height][width];
//        //初始化网格
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                grids[i][j] = ' ';
//            }
//        }
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                double slope = (double) height / width;
//                switch (d) {
//                    case LEFT_UP:
//                        //***
//                        //**
//                        //*
//                        //通过判断斜率的方法判断是否在直角三角形内
//                        if (i <= slope * (width - j)) {
//                            grids[i][j] = pattern;
//                        } else {
//                            grids[i][j] = ' ';
//                        }
//                        break;
//                    case LEFT_DOWN:
//                        //*
//                        //**
//                        //***
//                        //通过判断斜率的方法判断是否在直角三角形内
//                        if (height - i - 1 <= slope * (width - j - 1)) {
//                            grids[i][j] = pattern;
//                        } else {
//                            grids[i][j] = ' ';
//                        }
//                        break;
//                    case RIGHT_UP:
//                        //***
//                        // **
//                        //  *
//                        if ((i + 1) <= slope * (j + 1)) {
//                            grids[i][j] = pattern;
//                        } else {
//                            grids[i][j] = ' ';
//                        }
//                        break;
//                    case RIGHT_DOWN:
//                        //  *
//                        // **
//                        //***
//                        if (i == height - 1) {
//                            grids[i][j] = pattern;
//                        } else {
//                            if (height - (i + 1) <= slope * (j + 1)) {
//                                grids[i][j] = pattern;
//                            } else {
//                                grids[i][j] = ' ';
//                            }
//                        }
//                        break;
//                }
//            }
//        }
//    }

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