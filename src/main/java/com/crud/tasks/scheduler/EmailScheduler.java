package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailCreatorService;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    MailCreatorService mailCreatorService;

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    private String checkCount(){
        if(taskRepository.count() > 1) {
            return "tasks";
        }else{
            return "task";
        }
    }

    //@Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail(){
        long size = taskRepository.count();
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Currently in database you got: " + size + checkCount(),
                null
        ));
    }

    @Scheduled(cron = "0 0/30 8 * * *")
    public void dailyMail(){
        long size = taskRepository.count();
        String message = "Currently in database you got: " + size + checkCount();
        Context context = new Context();
        context.setVariable("daily-message", message);
        mailCreatorService.buildTrelloCardEmail(message);
    }
}
