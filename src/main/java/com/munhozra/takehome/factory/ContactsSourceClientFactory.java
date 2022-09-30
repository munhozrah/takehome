package com.munhozra.takehome.factory;

import com.munhozra.takehome.service.ContactsSourceClient;
import com.munhozra.takehome.service.impl.MockContactsSourceClientImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ContactsSourceClientFactory {
    @Autowired
    private BeanFactory beanFactory;
    public ContactsSourceClient createContactsSourceClient(String sourceType) {
        if (Objects.isNull(sourceType) || sourceType.isEmpty()) return null;
        if (MockContactsSourceClientImpl.IMPL_NAME.equals(sourceType)) {
            return beanFactory.getBean(MockContactsSourceClientImpl.IMPL_NAME, MockContactsSourceClientImpl.class);
        }
        throw new IllegalArgumentException("Error creating Mock Client: Unknown type: " + sourceType);
    }
}