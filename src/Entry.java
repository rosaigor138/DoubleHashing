public class Entry {
    private final int key;
    private final String element;
    private boolean available;

    public void setAvailable() {
        this.available = !this.isAvailable();
    }

    public Entry(int key, String value){
        this.key = key;
        this.element = value;
        this.available = false;
    }

    public int getKey() {
        return key;
    }

    public String getElement() {
        return element;
    }

    public boolean isAvailable() {
        return available;
    }
}
