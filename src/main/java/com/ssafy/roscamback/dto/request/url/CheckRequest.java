package com.ssafy.roscamback.dto.request.url;

import com.ssafy.roscamback.dto.request.url.ThreatEntry;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckRequest {
    private List<ThreatEntry> urls;
}