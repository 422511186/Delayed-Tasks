package org.example.controller.params;

import lombok.Data;

/**
 * @author huangzhenyu
 * @date 2023/5/7
 */
@Data
public class MessageParam {
    private String id;
    private String message;
    private String to;
}
