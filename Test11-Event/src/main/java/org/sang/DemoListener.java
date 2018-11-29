package org.sang;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *事件监听器
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {
    public void onApplicationEvent(DemoEvent demoEvent) {
        System.out.println("我收到DemoEvent的事件了:"+demoEvent.getMsg());
    }
}
