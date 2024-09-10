package com.ssafy.roscamback.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreatMatch {
    /**
     * 응답 형식
     * **/

    private String threatType;
    private String platformType;
    private Threat threat;
    private String threatEntryType;
}
