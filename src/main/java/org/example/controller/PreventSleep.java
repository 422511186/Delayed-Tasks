package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author huangzhenyu
 * @date 2023/5/7
 */
@Slf4j
@RestController
@RequestMapping("/wake")
public class PreventSleep {

    @RequestMapping
    public Object wake() {
        return new HashMap<String, Object>() {{
            put("status", "ok");
        }};
    }
}
