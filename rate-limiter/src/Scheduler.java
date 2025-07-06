import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Scheduler {
    private static ScheduledExecutorService scheduler;

    private Scheduler() {
    }

    public static ScheduledExecutorService getSchedulerInstance() {
        if(scheduler == null){
            synchronized (Scheduler.class){
                if(scheduler == null) {
                    // Initialize the scheduler with a single thread
                    scheduler = new ScheduledThreadPoolExecutor(1);
                }
            }
        }

        return scheduler;
    }
}
