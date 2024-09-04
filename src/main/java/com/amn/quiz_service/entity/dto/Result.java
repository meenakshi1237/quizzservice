package com.amn.quiz_service.entity.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Result {
    private Integer id;
    private String answer;
}
