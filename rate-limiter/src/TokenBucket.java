import java.util.concurrent.ConcurrentHashMap;

public class TokenBucket implements RateLimiter {
    private int tokenRefreshRate = 2;
    private int maxTokens = 5;

    private ConcurrentHashMap<User, Integer> userTokens;

    public TokenBucket(){
        userTokens = new ConcurrentHashMap<>();
        Scheduler.getSchedulerInstance().scheduleAtFixedRate(this::refreshTokens, 0, 1, java.util.concurrent.TimeUnit.SECONDS);
    }

    boolean isAllowed(User user) {
        return userTokens.getOrDefault(user, 0) > 0;
    }
    @Override
    public void makeRequest(Request request) {
        if(!isAllowed(request.getSentBy())) {
            System.out.println("Request rejected for user: " + request.getSentBy().getUserName() + " due to token overflow.");
            return;
        }

        userTokens.computeIfPresent(request.getSentBy(), (key, tokens) -> {
            tokens--;
            System.out.println("Processing request: " + request.getRequestMessage() + " from user: " + request.getSentBy().getUserName());
            return tokens;
        });
    }

    public void refreshTokens() {
        userTokens.forEach((user, tokens) -> {
            userTokens.computeIfPresent(user, (key, currentTokens) -> {
                return Math.min(maxTokens, currentTokens+tokenRefreshRate);
            });
        });
    }
}
