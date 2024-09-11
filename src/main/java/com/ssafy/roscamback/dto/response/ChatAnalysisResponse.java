package com.ssafy.roscamback.dto.response;
import lombok.Getter;

//레코드 롬복말고 not lombok use Record
@Getter
public class ChatAnalysisResponse {

    private int predicted_index;
    private String predicted_name;
    private int result;

}
