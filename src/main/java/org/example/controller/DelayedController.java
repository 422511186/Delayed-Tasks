package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.example.controller.params.MessageParam;
import org.example.service.RabbitMQService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huangzhenyu
 * @date 2023/5/7
 */
@Slf4j
@RestController
@RequestMapping("/delayed")
public class DelayedController {

    @Resource
    private RabbitMQService rabbitMQService;


    @PostMapping("/send/{delayedTime}")
    public Map<String, Object> Test(@RequestBody MessageParam message, @PathVariable("delayedTime") int delayedTime)
            throws JsonProcessingException {
        rabbitMQService.sendDelayMessage(message, delayedTime);
        return new HashMap<String, Object>() {{
            put("status", "ok");
        }};
    }

}
