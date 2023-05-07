package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangzhenyu
 * @date 2023/5/7
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class Test {

    @Value(value = "${app.params}")
    private String params;

    @RequestMapping
    public Object test() {
        return params;
    }
}
