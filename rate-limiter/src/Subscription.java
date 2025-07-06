public enum Subscription {

    FREE("FREE", 1),
    BASIC("BASIC", 5),
    PRO("PRO", 10),
    ENTERPRISE("ENTERPRISE", 20);

    private String subscription;
    private int requestsPerSecond;

    Subscription(String subscription, int requestsPerSecond) {
        this.subscription = subscription;
        this.requestsPerSecond = requestsPerSecond;
    }

    public String getSubscription() {
        return subscription;
    }

    public int getRequestsPerSecond() {
        return requestsPerSecond;
    }
}
