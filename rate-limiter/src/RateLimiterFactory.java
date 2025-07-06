import java.util.HashMap;

public class RateLimiterFactory {
    private static HashMap<String, RateLimiter> rateLimiterFactory = new HashMap<>();

    public RateLimiterFactory(){
        rateLimiterFactory.put("token-bucket", new TokenBucket());
        rateLimiterFactory.put("leaky-bucket", new LeakyBucket());
    }

    public RateLimiter getRateLimiter(String type) {
        return rateLimiterFactory.getOrDefault(type, null);
    }
}
