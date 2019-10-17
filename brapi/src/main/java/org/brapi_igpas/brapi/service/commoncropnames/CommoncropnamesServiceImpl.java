package org.brapi_igpas.brapi.service.commoncropnames;

import org.brapi_igpas.brapi.dao.commoncropnames.CommoncropnamesDAO;
import org.brapi_igpas.brapi.domain.response.BrApiDetailResponse;
import org.brapi_igpas.brapi.domain.response.metadata.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginatedList;
import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginationInfo;

@Service
public class CommoncropnamesServiceImpl implements CommoncropnamesService {
    private CommoncropnamesDAO commoncropnamesDAO;

    public CommoncropnamesServiceImpl(CommoncropnamesDAO commoncropnamesDAO) {
        this.commoncropnamesDAO = commoncropnamesDAO;
    }

    public BrApiDetailResponse getBrApiDetailResponse(int page, int pageSize) {
        List<String> commonCropNames = commoncropnamesDAO.getAll();

        Pagination paginationInfo = getPaginationInfo(commonCropNames.size(), page, pageSize);
        commonCropNames = getPaginatedList(commonCropNames, page, pageSize);

        return new BrApiDetailResponse(paginationInfo, commonCropNames);
    }
}
