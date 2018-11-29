package org.sang;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 事件（Application Event）
 *
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        DemoPublish demoPublish = context.getBean(DemoPublish.class);
        demoPublish.publish("Hello sang !");
        context.close();
    }
}
