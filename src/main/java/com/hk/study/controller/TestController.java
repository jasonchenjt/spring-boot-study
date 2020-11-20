package com.hk.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 * @desc
 * @date 2020/11/19
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/exce")
    public String exception() {
        log.info("exception");
        int i = 10 / 0;
        return "Nice";
    }
}
