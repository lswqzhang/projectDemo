package com.lswq.completion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangsw on 2017/7/12.
 */
public class CompletionServiceDemo {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletionServiceDemo demo = new CompletionServiceDemo();
        demo.exec();
    }


    private void exec() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setName("Task-" + atomicInteger.incrementAndGet());
            return t;
        });
        Collection<Callable<Result>> solvers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            solvers.add(() -> {
                Result result = new Result();
                result.setName(Thread.currentThread().getName() + "---task---" + (finalI + 1));
                return result;
            });
        }
        solve1(service, solvers);
        service.shutdown();
    }

    class Result {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    void solve1(Executor e, Collection<Callable<Result>> solvers) throws InterruptedException, ExecutionException {
        CompletionService<Result> ecs = new ExecutorCompletionService<>(e);
        for (Callable<Result> s : solvers)
            ecs.submit(s);
        int n = solvers.size();
        for (int i = 0; i < n; ++i) {
            Result r = ecs.take().get();
            if (r != null)
                use(r);
        }
    }


    void solve2(Executor e, Collection<Callable<Result>> solvers) throws InterruptedException {
        CompletionService<Result> ecs = new ExecutorCompletionService<>(e);
        int n = solvers.size();
        List<Future<Result>> futures = new ArrayList<>(n);
        Result result = null;
        try {
            for (Callable<Result> s : solvers)
                futures.add(ecs.submit(s));
            for (int i = 0; i < n; ++i) {
                try {
                    Result r = ecs.take().get();
                    if (r != null) {
                        result = r;
                        break;
                    }
                } catch (ExecutionException ignore) {

                }
            }
        } finally {
            for (Future<Result> f : futures)
                f.cancel(true);
        }

        if (result != null)
            use(result);
    }


    private void use(Result result) {
        System.err.println("result === " + result.getName());
    }
}
