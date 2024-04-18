package eu.smoothcloudservices.smoothcloud.wrapper.util;

import java.util.concurrent.*;

public class ThreadSafe<T> {

    private final ExecutorService executor;

    public ThreadSafe() {
        this.executor = Executors.newCachedThreadPool();
    }

    public ThreadSafe<T> supply(Callable<T> task) {
        Future<T> future = executor.submit(task);
        return new ThreadSafe<>(executor, future);
    }

    public ThreadSafe<Void> run(Runnable task) {
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

    private final Future<T> future;

}
