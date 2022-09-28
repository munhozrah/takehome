package com.munhozra.takehome.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MockAPIContactDTO {
    @JsonProperty
    private Long Id;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String email;

/*
    @JsonProperty
    private String avatar;

    @JsonProperty
    private LocalDateTime createdAt;
*/
}