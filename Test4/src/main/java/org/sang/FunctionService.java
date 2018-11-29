package org.sang;

import org.springframework.stereotype.Service;


@Service
public class FunctionService {
    public String sayHello(String word) {
        return "你好" + word + "!";
    }
}
