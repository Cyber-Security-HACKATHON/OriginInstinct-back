package com.ssafy.roscamback.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalyzeRequest {
    private String userId;
    private String otherId;
    private String data;
}
