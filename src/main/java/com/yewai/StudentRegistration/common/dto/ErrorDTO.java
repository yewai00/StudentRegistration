package com.yewai.StudentRegistration.common.dto;

import java.util.Map;

public record ErrorDTO(
        String error,
        String messages,
        Map<String,String> details
) {
}
