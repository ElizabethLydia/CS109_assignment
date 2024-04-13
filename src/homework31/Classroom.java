package homework31;

public class Classroom {
    private String category;
    private int capacity;
    public boolean isVisited;

    public Classroom() {
    }

    public Classroom(String category, int capacity) {
        this.category = category;
        this.capacity = capacity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
