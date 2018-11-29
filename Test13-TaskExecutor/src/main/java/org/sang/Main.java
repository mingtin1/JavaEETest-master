package org.sang;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 多线程
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        AsyncTaskService bean = context.getBean(AsyncTaskService.class);
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            bean.executeAsyncTask(i);
            bean.executeAsyncTask2(i);
        }
        context.close();
    }
}
