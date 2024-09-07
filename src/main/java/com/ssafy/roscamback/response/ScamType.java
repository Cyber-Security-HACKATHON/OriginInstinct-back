package com.ssafy.roscamback.response;

import lombok.Getter;

@Getter
public enum ScamType {
    SCAM, NON_SCAM;

    private String isScam;
}
