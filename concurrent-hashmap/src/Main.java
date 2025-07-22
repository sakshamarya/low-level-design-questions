import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) {
        CustomHashMap hashmap1 = new CustomHashMap(4);

        hashmap1.put("Saksham", 10);
        System.out.println(hashmap1.get("Saksham"));
        hashmap1.put("Saksham", 20);
        System.out.println(hashmap1.get("Saksham"));
        hashmap1.put("Saksham1", 100);
        hashmap1.put("Saksham2", 200);
        hashmap1.put("Saksham3", 300);
        hashmap1.put("Saksham4", 400);
        System.out.println(hashmap1.get("Saksham1"));
        System.out.println(hashmap1.get("Saksham2"));
        System.out.println(hashmap1.get("Saksham3"));
        System.out.println(hashmap1.get("Saksham4"));

    }
}