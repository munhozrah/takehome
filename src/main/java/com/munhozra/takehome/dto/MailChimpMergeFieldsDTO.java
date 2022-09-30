package com.munhozra.takehome.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailChimpMergeFieldsDTO {
    @JsonProperty("FNAME")
    private String firstName;
    @JsonProperty("LNAME")
    private String lastName;
}
