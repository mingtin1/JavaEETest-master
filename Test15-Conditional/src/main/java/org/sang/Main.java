package org.sang;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 3.4 条件注解@Conditional
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        ListService bean = context.getBean(ListService.class);
        System.out.println(bean.showListCmd());
        context.close();
    }
}
