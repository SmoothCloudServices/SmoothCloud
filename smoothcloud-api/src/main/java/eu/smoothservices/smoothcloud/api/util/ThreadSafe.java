package eu.smoothservices.smoothcloud.api.util;

import java.util.concurrent.*;

public class ThreadSafe<T> {

    private final ExecutorService executor;

    public ThreadSafe(Future<T> future) {
        this.future = future;
        this.executor = Executors.newCachedThreadPool();
    }

    public static <T> ThreadSafe<T> supply(Callable<T> task) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<T> future = executor.submit(task);
        return new ThreadSafe<>(executor, future);
    }

    public static ThreadSafe<Void> run(Runnable task) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Void> future = executor.submit(() -> {
            task.run();
            return null;
        });
        return new ThreadSafe<>(executor, future);
    }

    private ThreadSafe(ExecutorService executor, Future<T> future) {
        this.executor = executor;
        this.future = future;
    }

    public T get() throws InterruptedException, ExecutionException {
        return future.get();
    }

    public void await() throws InterruptedException, ExecutionException {
        future.get();
    }

    public void shutdown() {
        // Beende den ExecutorService
        executor.shutdown();
    }

    private Future<T> future;

}
