import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomHashMap {
    private List<Entry> hashMap = new ArrayList<>();
    private int capacity;
    private int size;
    private double loadFactor;


    public CustomHashMap(int capacity){
        this.capacity = capacity;
        for(int i=0;i<capacity;i++){
            hashMap.add(null);
        }
        size=0;
        loadFactor = 1;
    }

    private Entry findEntry(Object key){
        int generatedHash = Math.abs(key.hashCode())%capacity;
        Entry entry = hashMap.get(generatedHash);
        while(Objects.nonNull(entry) && Objects.nonNull(entry.getNextEntry())){
            if(Objects.equals(entry.getKey(), key)){
                return entry;
            }
            entry = entry.getNextEntry();
        }
        return entry;
    }

    private CustomHashMap reHash(){
        CustomHashMap newHashMap = new CustomHashMap(2*capacity);
        for(Entry entry: hashMap){
            while(Objects.nonNull(entry)){
                newHashMap.put(entry.getKey(), entry.getValue());
                entry=entry.getNextEntry();
            }
        }

        return newHashMap;
    }

    public void put(Object key, Object value){
        int generatedHash = Math.abs(key.hashCode())%capacity;

        if(Objects.isNull(hashMap.get(generatedHash))){
            size++;
            hashMap.set(generatedHash, new Entry(key, value));
        }

        Entry getEntry = findEntry(key);

        if(getEntry.getKey().equals(key)){
            getEntry.setValue(value);
        }
        else{
            getEntry.setNextEntry(new Entry(key, value));
            size++;
        }

        double newLf = ((1.0*size)/capacity);
        System.out.println(size + "::" + capacity);
        System.out.println("load factor becomes: "+newLf);

        if(newLf>loadFactor){
            System.out.println("rehashing");
            CustomHashMap newHashMap = reHash();
            this.hashMap = newHashMap.hashMap;
            this.capacity = newHashMap.capacity;
        }
    }

    public Object get(Object key){

        Entry getEntry = findEntry(key);

        if(getEntry.getKey().equals(key)){
            return getEntry.getValue();
        }

        return null;
    }


}
