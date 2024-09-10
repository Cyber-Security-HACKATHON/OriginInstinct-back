package com.ssafy.roscamback.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ThreatInfo {
    /**
     * 위협 정보
     * 위협 목록 : https://developers.google.com/safe-browsing/v4/lists?hl=ko
     * **/
    private final String[] threatTypes={"MALWARE", "SOCIAL_ENGINEERING"};
    private final String[] platformTypes={"WINDOWS"};
    private final String[] threatEntryTypes={"URL"};
    private ThreatEntry threatEntries;
}
