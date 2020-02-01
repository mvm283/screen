package thread;

        import java.time.LocalDateTime;
        import java.util.Date;
        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;

public class SimpleThreadPool {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        LocalDateTime now = LocalDateTime.now();

        System.out.println("SSSSSSSSS : "+ now);
        for (int i = 0; i < 100; i++) {
            Runnable worker = new WorkerThread("" + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        LocalDateTime end = LocalDateTime.now();

        System.out.println("SSSSSSSSS : "+ end);
        System.out.println("Finished all threads");
    }
}