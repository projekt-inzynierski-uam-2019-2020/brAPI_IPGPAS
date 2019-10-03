package org.brapi_igpas.brapi.calls.crops;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.brapi.utils.PaginationUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommoncropnamesService {
    private CommoncropnamesDao commoncropnamesDao;

    public CommoncropnamesService(CommoncropnamesDao commoncropnamesDao) {
        this.commoncropnamesDao = commoncropnamesDao;
    }

    BrApiDetailResponse getBrApiDetailResponse(int page, int pageSize) {
        List<String> commonCropNames = commoncropnamesDao.getAll();

        Pagination paginationInfo = PaginationUtils.getPaginationInfo(commonCropNames.size(), page, pageSize);
        int fromIndex = PaginationUtils.getFromIndex(commonCropNames.size(), page, pageSize);
        int toIndex = PaginationUtils.getToIndex(commonCropNames.size(), page, pageSize);
        commonCropNames = commonCropNames.subList(fromIndex, toIndex);

        return new BrApiDetailResponse(commonCropNames, paginationInfo);
    }
}
