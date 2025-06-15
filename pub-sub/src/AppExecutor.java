import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutor {
    private static ExecutorService executorService = null;

    private AppExecutor(){}
    public static ExecutorService getExecutorService() {
        if(executorService == null) {
            synchronized (AppExecutor.class) {
                if (executorService == null) {
                    // Initialize the executor service with a fixed thread pool
                    executorService = Executors.newFixedThreadPool(10);
                }
            }
        }
        return executorService;
    }
}
