package homework5;

import java.util.ArrayList;
import java.util.List;

public class AvoidConflictShapeCanvas implements ShapeCanvas {
    private List<Shape> shapes;
    private char[][] canvas;

    //Constructor
    public AvoidConflictShapeCanvas(int rows, int cols) {
        shapes = new ArrayList<>();
        canvas = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                canvas[i][j] = ' ';
            }
        }
    }

    @Override
    public boolean addShape(int x, int y, char pattern, int... params) {
        //This method is to add a shape into canvas, and the parameter means:
        //x and y: the location x and y of shape
        //pattern: the pattern of shape
        //params: The length of params only be 1 or 3 in all test cases in this assignment.
//        For circle: the length of params is 1, which represents the radius of circle
//        For RightTriangle: the length of params is 3.
//        The first one is the width of RightTriangle
//        The second one is the height of RightTriangle
//        The third one is the index of Direction. For example, if the third value is 0, it
//        means LEFT_UP direction.
        //The return value is determined by whether the shape can be added successfully, which means if
        //it has no conflict when adding a shape, the method returns true, otherwise it returns false.
        //The conflict includes:
        //The shape is out of the bound of canvas.
        //Overlap Conflict

        if (params.length == 1) {
            Circle circle = new Circle(new Location(x, y), pattern, params[0]);
            if (circle.getGrids() == null) {//If the shape is invalid, return false
                return false;
            }
            //判断是否超出画布边界
            if (x + circle.getGrids().length > canvas.length || y + circle.getGrids()[0].length > canvas[0].length) {
                return false;
            }
            //Check if the shape has overlap conflict
            for (int i = 0; i < circle.getGrids().length; i++) {
                for (int j = 0; j < circle.getGrids()[0].length; j++) {
                    if (circle.getGrids()[i][j] != ' ') {//If the grid is not empty, it means the grid is in the shape
                        if (canvas[x + i][y + j] != ' ') {
                            return false;
                        }
                    }
                }
            }
            //将图形填充到画布中
            for (int i = 0; i < circle.getGrids().length; i++) {
                for (int j = 0; j < circle.getGrids()[0].length; j++) {
                    if (circle.getGrids()[i][j] != ' ') {
                        canvas[x + i][y + j] = circle.getGrids()[i][j];
                    }
                }
            }
            //Add the shape into shapes list
            shapes.add(circle);
            return true;
        } else if (params.length == 3) {
            RightTriangle rightTriangle = new RightTriangle(new Location(x, y), pattern, params[0], params[1], Direction.values()[params[2]]);
            if (rightTriangle.getGrids() == null) {
                return false;
            }
            //判断是否超出画布边界
            if (x + rightTriangle.getGrids().length > canvas.length || y + rightTriangle.getGrids()[0].length > canvas[0].length) {
                return false;
            }
            //判断是否有重叠冲突
            for (int i = 0; i < rightTriangle.getGrids().length; i++) {
                for (int j = 0; j < rightTriangle.getGrids()[0].length; j++) {
                    if (rightTriangle.getGrids()[i][j] != ' ') {//如果格子不为空，说明格子在图形中，那么如果canvas中对应的格子不为空，说明有冲突，这是一种什么冲突呢，就是重叠冲突
                        if (canvas[x + i][y + j] != ' ') {
                            return false;
                        }
                    }
                }
            }
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
        return false;
    }

    @Override
    public int getSpaceGridCount() {
        int count = 0;
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                if (canvas[i][j] == ' ') {
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
        //RightTriangle: (0,2) area=11 pattern=A
        //RightTriangle: (6,8) area=23 pattern=B
        //RightTriangle: (6,6) area=23 pattern=D
        //Circle: (0,8) area=36 pattern=E
        //这是它的输出形式
        //这个方法是根据图形的面积来排序，面积小的在前面，面积大的在后面
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
                        }else {
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
        //Ascending order of the x value in location.
        //If shapes with a same value of x, sorted by the ascending order of its y value in location.
        //If shapes with a same location, sorted by the ascending order of its character value of
        //pattern.
        if (shapes.size() == 0) {
            return new ArrayList<>();
        } else {
            if (shapes.size() == 1) {
                return shapes;
        }else {
                shapes.sort((o1, o2) -> {
                    if (o1.location.getX() < o2.location.getX()) {
                        return -1;
                    } else if (o1.location.getX() > o2.location.getX()) {
                        return 1;
                    } else {
                        if (o1.location.getY() < o2.location.getY()) {
                            return -1;
                        }
                        else if (o1.location.getY() > o2.location.getY()) {
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
    //Using a char[][] array to represent the canvas. The initial value of each grid in canvas is a space
    //' ' , which mean an empty grid
}
