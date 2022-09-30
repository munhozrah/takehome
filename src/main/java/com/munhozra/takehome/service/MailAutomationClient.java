package com.munhozra.takehome.service;

import com.munhozra.takehome.dto.MailChimpMemberDTO;

public interface MailAutomationClient {
    public void addOrUpdateListMember(MailChimpMemberDTO memberDTO);
}
