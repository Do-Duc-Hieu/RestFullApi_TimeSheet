package com.example.devTimesheet.config;

import com.example.devTimesheet.service.PunishmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@EnableScheduling
@Configuration
@RequiredArgsConstructor
public class DynamicSchedulerConfig implements SchedulingConfigurer {
    private final PunishmentService punishmentService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                punishmentService::createPunishmentSchedule,
                triggerContext -> {
                    String cron = fetchCronExpressionFromDB();
                    return new CronTrigger(cron).nextExecutionTime(triggerContext).toInstant();
                }
        );
    }

    private String fetchCronExpressionFromDB() {
        String filePath = "C:\\devTimesheet\\devTimesheet\\src\\main\\resources\\static\\Schedule.txt";
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            if (!lines.isEmpty()) {
                return lines.get(0); // Lấy cron expression từ dòng đầu tiên của file
            } else {
                throw new RuntimeException("Cron expression file is empty");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read cron expression from file", e);
        }
    }
}
