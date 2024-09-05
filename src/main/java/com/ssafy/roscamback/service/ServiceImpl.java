package com.ssafy.roscamback.service;

import com.ssafy.roscamback.request.ThreatEntry;
import com.ssafy.roscamback.response.CheckResponse;
import com.ssafy.roscamback.util.ApiChecker;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements com.ssafy.roscamback.service.Service {

    @Autowired
    private ApiChecker apiChecker;

    @Override
    public CheckResponse checkUrlSafety(List<ThreatEntry> urls) {
        return apiChecker.checkUrl(urls);
    }

    @Override
    public boolean isUrlSafe(ThreatEntry url) {
        return apiChecker.checkUrl(Collections.singletonList(url)).getThreatMatches() == null;
    }
}
