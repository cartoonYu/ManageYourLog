package org.manageyourlog.demo;

import org.manageyourlog.facade.annotation.UploadLogAnnotation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cartoon
 * @date 2021/11/6 17:46
 */
@RestController
public class DemoController {

    @PostMapping("/query")
    public void query(){

    }
}
