package com.ssafy.roscamback.dto.request.url;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ThreatEntry {
    private String url;
}