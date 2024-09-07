package com.ssafy.roscamback.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalyzeResponse {

    /**
     * 응답 데이터 목록
     * 1. 스캠 위험 여부
     * 2. 원본 채팅 내용
     * 3. 악성 URL 포함 여부
     * **/
    private ScamType isScam;
    private List<String> chatList;
    private boolean isBadUrl;
}
