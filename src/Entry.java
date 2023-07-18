public class Entry {
    private final Integer key;
    private final String element;
    private boolean available;

    public void toggleAvailability() {
        this.available = !this.isAvailable();
    }

    public Entry(Integer key, String value){
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
