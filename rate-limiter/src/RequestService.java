public class RequestService {
    public void makeRequest(Request request, String algo){
        RateLimiterFactory rateLimiterFactory = new RateLimiterFactory();
        RateLimiter rateLimiter = rateLimiterFactory.getRateLimiter(algo);

        if(rateLimiter == null) {
            System.out.println("Invalid rate limiter type: " + algo);
            return;
        }

        rateLimiter.makeRequest(request);
    }
}
