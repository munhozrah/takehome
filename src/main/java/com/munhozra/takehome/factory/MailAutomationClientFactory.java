package com.munhozra.takehome.factory;

import com.munhozra.takehome.service.MailAutomationClient;
import com.munhozra.takehome.service.impl.MailChimpClientImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MailAutomationClientFactory {
    @Autowired
    private BeanFactory beanFactory;
    public MailAutomationClient createMailClient(String mailType) {
        if (Objects.isNull(mailType) || mailType.isEmpty()) return null;
        if (MailChimpClientImpl.IMPL_NAME.equals(mailType)) {
            return beanFactory.getBean(MailChimpClientImpl.IMPL_NAME, MailChimpClientImpl.class);
        }
        throw new IllegalArgumentException("Error creating Mail RestTemplate: Unknown type: " + mailType);
    }
}
