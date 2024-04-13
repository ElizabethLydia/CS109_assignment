package homework31;

public class Course {
    private String category;
    private int capacity;

    public Course() {
    }

    public Course(String category, int capacity) {
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
