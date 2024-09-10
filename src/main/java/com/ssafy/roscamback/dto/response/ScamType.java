package com.ssafy.roscamback.dto.response;

import lombok.Getter;

@Getter
public enum ScamType {
    SCAM, NON_SCAM;

    private String isScam;
}
