package com.hk.study.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * 文件描述: 定频定时器 / 变频定时器
 *
 * @author Jason.Chen
 * @date 2020年11月20日 9:37
 */
@Slf4j
@Component
@EnableScheduling
public class Task implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 任务内容.拉姆达表达式
        TriggerTask triggrtTask = new TriggerTask(
                () -> {
                    log.info("变频定时器 Task");
                },
                // 设置触发器，这里是一个拉姆达表达式，传入的TriggerContext类型，返回的是Date类型
                triggerContext -> {
                    // 2.3 返回执行周期(Date)
                    return new CronTrigger("0/2 * * * * ?").nextExecutionTime(triggerContext);
                });

        taskRegistrar.addTriggerTask(triggrtTask);
    }

    @Scheduled(fixedRate = 1000)
    public void task2() {
        log.info("定频定时器 Task");
    }
}
