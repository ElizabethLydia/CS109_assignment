package homework5;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OverLapShapeCanvas implements ShapeCanvas {
    private List<Shape> shapes;//存储成功added的shape
    private char[][] canvas;//画布

    public OverLapShapeCanvas(int width, int height) {
        shapes = new ArrayList<>();
        canvas = new char[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                canvas[i][j] = ' ';
            }
        }
    }

    @Override
    public boolean addShape(int x, int y, char pattern, int... params) {
        if (params.length == 1) {
            Circle circle = new Circle(new Location(x, y), pattern, params[0]);
            if (circle.getGrids() == null) {//If the shape is invalid, return false
                return false;
            }
            //Return value: Has one non-space grid of shape in corresponding location of canvas is in the
            //bound, in this case it returns true, otherwise returns false.
            //这个意味着只要有一个格子在画布内，就可以return true，把这一个格子填充到画布中，然后把图形添加到shapes列表中
            boolean flag = false;
            for (int i = 0; i < circle.getGrids().length; i++) {
                for (int j = 0; j < circle.getGrids()[0].length; j++) {
                    if (circle.getGrids()[i][j] != ' ') {
                        //加一个判断，放置数组越界
                        if (x + i <= canvas.length && y + j <= canvas[0].length) {
                            if (canvas[x + i][y + j] == ' ') {
                                flag = true;
                                canvas[x + i][y + j] = circle.getGrids()[i][j];
                                shapes.add(circle);
                                return true;
                            } else {
                                return false;

                            }
                        }
                    }
                }
            }
            //判断是否有重叠冲突，此时允许图形重叠，但是如果重叠的话，重叠的部分必须是空格，不能覆盖原来的非空格
            boolean flag1 = true;
            while (flag1 == true) {
                for (int i = 0; i < circle.getGrids().length; i++) {
                    for (int j = 0; j < circle.getGrids()[0].length; j++) {
                        if (circle.getGrids()[i][j] != ' ') {
                            //加一个判断，放置数组越界
                            if (x + i <= canvas.length && y + j <= canvas[0].length) {
                                if (canvas[x + i][y + j] != ' ') {
                                    flag1 = false;
                                    break;
                                }
                            }
                        }
                    }
                    return false;
                }
            }

            if (flag == true && flag1 == true) {
                //将图形填充到画布中
                for (int i = 0; i < circle.getGrids().length; i++) {
                    for (int j = 0; j < circle.getGrids()[0].length; j++) {
                        if (circle.getGrids()[i][j] != ' ') {
                            if (x + i <= canvas.length && y + j <= canvas[0].length) {
                                canvas[x + i][y + j] = circle.getGrids()[i][j];
                            }
                        }
                    }
                }
                //Add the shape into shapes list
                shapes.add(circle);
                return true;
            }
        } else if (params.length == 3) {
            RightTriangle rightTriangle = new RightTriangle(new Location(x, y), pattern, params[0], params[1], Direction.values()[params[2]]);
            if (rightTriangle.getGrids() == null) {
                return false;
            }
            //判断是否超出画布边界
            boolean flag = false;
            for (int i = 0; i < rightTriangle.getGrids().length; i++) {
                for (int j = 0; j < rightTriangle.getGrids()[0].length; j++) {
                    if (rightTriangle.getGrids()[i][j] != ' ') {
                        //加一个判断，避免数组越界
                        if (x + i <= canvas.length && y + j <= canvas[0].length) {
                            if (canvas[x + i][y + j] == ' ') {
                                flag = true;
                                break;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
            //判断是否有重叠冲突，此时允许图形重叠，但是如果重叠的话，重叠的部分必须是空格，不能覆盖原来的非空格
            boolean flag1 = true;
            while (flag1 == true) {
                for (int i = 0; i < rightTriangle.getGrids().length; i++) {
                    for (int j = 0; j < rightTriangle.getGrids()[0].length; j++) {
                        if (rightTriangle.getGrids()[i][j] != ' ') {
                            if (x + i <= canvas.length && y + j <= canvas[0].length) {
                                if (canvas[x + i][y + j] != ' ') {
                                    flag1 = false;
                                    break;
                                }
                            }
                        }
                    }
                    return false;
                }
            }

            if (flag == true && flag1 == true) {
                //将图形填充到画布中，得根据图形的Direction来填充
                switch (params[2]) {
                    case 0://LEFT_UP
                        for (int i = 0; i < rightTriangle.getGrids().length; i++) {
                            for (int j = 0; j < rightTriangle.getGrids()[0].length; j++) {
                                if (rightTriangle.getGrids()[i][j] != ' ') {
                                    canvas[x + i][y + j] = rightTriangle.getGrids()[i][j];
                                }
                            }
                        }
                        break;
                    case 1://LEFT_DOWN
                        for (int i = 0; i < rightTriangle.getGrids().length; i++) {
                            for (int j = 0; j < rightTriangle.getGrids()[0].length; j++) {
                                if (rightTriangle.getGrids()[i][j] != ' ') {
                                    canvas[x + i][y + j] = rightTriangle.getGrids()[i][j];
                                }
                            }
                        }
                        break;
                    case 2://RIGHT_UP
                        for (int i = 0; i < rightTriangle.getGrids().length; i++) {
                            for (int j = 0; j < rightTriangle.getGrids()[0].length; j++) {
                                if (rightTriangle.getGrids()[i][j] != ' ') {
                                    canvas[x + i][y + j] = rightTriangle.getGrids()[i][j];
                                }
                            }
                        }
                        break;
                    case 3://RIGHT_DOWN
                        for (int i = 0; i < rightTriangle.getGrids().length; i++) {
                            for (int j = 0; j < rightTriangle.getGrids()[0].length; j++) {
                                if (rightTriangle.getGrids()[i][j] != ' ') {
                                    canvas[x + i][y + j] = rightTriangle.getGrids()[i][j];
                                }
                            }
                        }
                        break;
                }
                //Add the shape into shapes list
                shapes.add(rightTriangle);
                return true;
            }
        }
        return false;
    }

    @Override
    public int getSpaceGridCount() {
        //统计画布中所有填充格子的数量
        int count = 0;
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                if (canvas[i][j] != ' ') {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int getShapeCount() {
        return shapes.size();
    }

    @Override
    public List<Shape> getShapesByArea() {
        if (shapes.size() == 0) {
            return new ArrayList<>();
        } else {
            if (shapes.size() == 1) {
                return shapes;
            } else {
                //用compareTo方法来比较两个图形的面积,形状面积按照升序排列
                shapes.sort((o1, o2) -> {
                    if (o1.area() < o2.area()) {
                        return -1;
                    } else if (o1.area() > o2.area()) {
                        return 1;
                    } else {
                        if (o1.getPattern() > o2.getPattern()) {
                            return 1;
                        } else if (o1.getPattern() < o2.getPattern()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });
                return shapes;
            }
        }
    }

    @Override
    public List<Shape> getShapesByLocation() {
        if (shapes.size() == 0) {
            return new ArrayList<>();
        } else {
            if (shapes.size() == 1) {
                return shapes;
            } else {
                shapes.sort((o1, o2) -> {
                    if (o1.location.getX() < o2.location.getX()) {
                        return -1;
                    } else if (o1.location.getX() > o2.location.getX()) {
                        return 1;
                    } else {
                        if (o1.location.getY() < o2.location.getY()) {
                            return -1;
                        } else if (o1.location.getY() > o2.location.getY()) {
                            return 1;
                        } else {
                            if (o1.getPattern() < o2.getPattern()) {
                                return -1;
                            } else if (o1.getPattern() > o2.getPattern()) {
                                return 1;
                            } else {
                                return 0;
                            }
                        }
                    }
                });
                return shapes;
            }
        }
    }

    @Override
    public char[][] getCanvas() {
        return canvas;
    }
}
