package org.brapi_igpas.brapi.calls.commoncropnames;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginatedList;
import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginationInfo;

@Service
public class CommoncropnamesServiceImpl implements CommoncropnamesService {
    private final CommoncropnamesDAO commoncropnamesDAO;

    public CommoncropnamesServiceImpl(CommoncropnamesDAO commoncropnamesDAO) {
        this.commoncropnamesDAO = commoncropnamesDAO;
    }

    public BrAPIDetailResponse getBrAPIDetailResponse(int page, int pageSize) {
        List<String> commoncropnames = commoncropnamesDAO.getAll();

        Pagination paginationInfo = getPaginationInfo(commoncropnames.size(), page, pageSize);
        commoncropnames = getPaginatedList(commoncropnames, page, pageSize);

        return new BrAPIDetailResponse(paginationInfo, commoncropnames);
    }
}
