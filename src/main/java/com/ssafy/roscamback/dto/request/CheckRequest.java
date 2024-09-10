package com.ssafy.roscamback.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckRequest {
    private List<ThreatEntry> urls;
}