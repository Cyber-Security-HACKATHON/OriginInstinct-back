package com.ssafy.roscamback.service;

import com.ssafy.roscamback.request.ThreatEntry;
import com.ssafy.roscamback.response.CheckResponse;
import java.util.List;

public interface Service {

    CheckResponse checkUrlSafety(List<ThreatEntry> urls);
    boolean isUrlSafe(ThreatEntry url);
}
