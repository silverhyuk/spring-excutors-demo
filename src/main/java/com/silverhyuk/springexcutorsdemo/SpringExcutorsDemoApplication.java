package com.silverhyuk.springexcutorsdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringExcutorsDemoApplication {
    private final SimpleAsyncTaskExecutor asyncTaskExecutor;
    private final SyncTaskExecutor syncTaskExecutor;
    private final TaskExecutorAdapter taskExecutorAdapter;
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private final DemonstrationRunnable task;

    @Autowired
    public SpringExcutorsDemoApplication(SimpleAsyncTaskExecutor asyncTaskExecutor, SyncTaskExecutor syncTaskExecutor, TaskExecutorAdapter taskExecutorAdapter, ThreadPoolTaskExecutor threadPoolTaskExecutor, DemonstrationRunnable task) {
        this.asyncTaskExecutor = asyncTaskExecutor;
        this.syncTaskExecutor = syncTaskExecutor;
        this.taskExecutorAdapter = taskExecutorAdapter;
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.task = task;
    }
    @PostConstruct
    public void submitJobs() {
        //syncTaskExecutor.execute(task);
        /*taskExecutorAdapter.submit(task);
        asyncTaskExecutor.submit(task);
*/
        for (int i = 0; i < 500; i++) {
            threadPoolTaskExecutor.submit(task);
        }

    }
    public static void main(String[] args) {

        SpringApplication.run(SpringExcutorsDemoApplication.class, args);

    }

}
