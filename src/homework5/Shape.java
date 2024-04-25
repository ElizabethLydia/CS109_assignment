package homework5;

public abstract class Shape {
    //fields: grids, pattern, location
    protected char[][] grids;
    protected char pattern;
    //grids 是由 char 类型字符组成，⾥⾯每⼀个⼩格⼦是⼀个像素，如果有元素则⽤ pattern 表
    //示，如果没有元素则⽤⼀个空格表示。
    protected Location location;

    //methods: constructor, getGrids, fillGrids, enlarge, shrink, area
    public Shape(Location location, char pattern) {
        //Set the original value of Location and the concrete pattern in Shape
        this.location = location;
        this.pattern = pattern;
    }

    public char[][] getGrids() {
        //Return the reference of the attribute grids
        return grids;
    }

    public abstract void fillGrids();
    //determined by the concrete subclass of shape.

    public abstract void enlarge();
    //determined by the concrete subclass of shape.

    public abstract void shrink();
    //determined by the concrete subclass of shape.

    public abstract int area();
    //Return the count of patterns that being filled in grids.
}
