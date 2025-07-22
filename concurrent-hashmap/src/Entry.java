public class Entry {
    private Object key;
    private Object value;
    private Entry nextEntry;

    public Entry(Object key, Object value){
        this.key = key;
        this.value = value;
        this.nextEntry = null;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public Entry getNextEntry() {
        return nextEntry;
    }

    public void setValue(Object value){
        this.value = value;
    }

    public void setNextEntry(Entry nextEntry){
        this.nextEntry = nextEntry;
    }
}
