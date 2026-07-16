package com.adarsh.backend.feature.vendor.application.interactor;

import com.adarsh.backend.feature.vendor.application.dto.result.GetVendorApplicationsResult;
import com.adarsh.backend.feature.vendor.application.interactor.constant.VendorInteractorLogConstants;
import com.adarsh.backend.feature.vendor.application.usecase.GetVendorApplicationsUseCase;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import com.adarsh.backend.feature.vendor.application.port.VendorApplicationQueryRepositoryPort;
import com.adarsh.backend.feature.vendor.application.port.VendorApplicationSearchCriteria;
import com.adarsh.backend.feature.vendor.domain.ApplicationStatus;
import com.adarsh.backend.feature.vendor.domain.VendorApplication;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetVendorApplicationsInteractor implements GetVendorApplicationsUseCase {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(GetVendorApplicationsInteractor.class);

    private final VendorApplicationQueryRepositoryPort vendorApplicationQueryRepositoryPort;


    @Override
    public PageResult<GetVendorApplicationsResult> execute(String keyword, ApplicationStatus status, int page, int size) {
        logger.info(VendorInteractorLogConstants.GET_VENDOR_APPLICATIONS_REQUEST, keyword, status, page, size);

        PageQuery query = new PageQuery(page, size);
        VendorApplicationSearchCriteria criteria = new VendorApplicationSearchCriteria(keyword, status);

        PageResult<VendorApplication> domainPage = vendorApplicationQueryRepositoryPort.search(criteria, query);

        PageResult<GetVendorApplicationsResult> result = domainPage.map(GetVendorApplicationsResult::fromDomain);

        logger.info(VendorInteractorLogConstants.GET_VENDOR_APPLICATIONS_FETCHED, result.totalElements());

        return result;
    }
}
