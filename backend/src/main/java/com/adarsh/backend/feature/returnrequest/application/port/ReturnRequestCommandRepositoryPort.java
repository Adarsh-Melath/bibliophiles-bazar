package com.adarsh.backend.feature.returnrequest.application.port;

import com.adarsh.backend.feature.returnrequest.domain.model.ReturnRequest;

public interface ReturnRequestCommandRepositoryPort {
    ReturnRequest save(ReturnRequest returnRequest);
}