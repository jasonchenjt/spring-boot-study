package com.hk.study.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2020年11月20日 11:45
 */
@Slf4j
@Component
public class AsyncTask {

    @Async
    public void asyncOperation() {
        log.info("异步方法执行, threadId:" + Thread.currentThread().getId());
    }
}
