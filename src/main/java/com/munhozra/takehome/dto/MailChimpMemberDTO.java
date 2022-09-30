package com.munhozra.takehome.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailChimpMemberDTO {
    @JsonProperty("email_address")
    private String email;

    @JsonProperty("status_if_new")
    private String statusIfNew;

    @JsonProperty("merge_fields")
    private MailChimpMergeFieldsDTO mergeFieldsDTO;
}
