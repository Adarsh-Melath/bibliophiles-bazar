package com.adarsh.backend.feature.vendor.application.port;

import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import com.adarsh.backend.feature.vendor.domain.VendorApplication;

import java.util.Optional;

public interface VendorApplicationQueryRepositoryPort {
    Optional<VendorApplication> findById(Long id);

    Optional<VendorApplication> findByEmail(String email);

    PageResult<VendorApplication> search(VendorApplicationSearchCriteria criteria, PageQuery query);
}
