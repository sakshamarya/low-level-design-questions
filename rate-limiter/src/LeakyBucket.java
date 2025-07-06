import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class LeakyBucket implements RateLimiter{
    /*
     *   Each user will have a bucket
     *   Bucket has a fixed outflow rate and fixed size
     *   Request is rejected if bucket is full
    */

    private int outflowRate = 3; // every second, 3 requests can be processed
    private int bucketCapacity = 5;
    ConcurrentHashMap<User, LinkedList<Request>> userBuckets;

    public LeakyBucket(){
        userBuckets = new ConcurrentHashMap<>();
        Scheduler.getSchedulerInstance().scheduleAtFixedRate(this::processRequests, 0, 1, java.util.concurrent.TimeUnit.SECONDS);
    }

    boolean isAllowed(User user){
        return !userBuckets.containsKey(user) || userBuckets.get(user).size() <= bucketCapacity;
    }

    @Override
    public void makeRequest(Request request){
        User user = request.getSentBy();
        if(!isAllowed(user)){
            System.out.println("Request rejected for user: " + user.getUserName() + " due to bucket overflow.");
            return;
        }

        userBuckets.compute(user, (key, bucket) -> {
            if(bucket == null) {
                bucket = new LinkedList<>();
            }
            bucket.add(request);
            return bucket;
        });
    }

    private void processRequests(){
        for(Map.Entry<User, LinkedList<Request>> entry : userBuckets.entrySet()) {
            userBuckets.computeIfPresent(entry.getKey(), (key, bucket) -> {
                int ctr = outflowRate;

                while(!bucket.isEmpty() && ctr > 0){
                    Request request = bucket.poll();
                    System.out.println("Processing request: " + request.getRequestMessage() + " from user: " + request.getSentBy().getUserName());
                    ctr--;
                }

                return bucket;
            });
        }
    }
}
