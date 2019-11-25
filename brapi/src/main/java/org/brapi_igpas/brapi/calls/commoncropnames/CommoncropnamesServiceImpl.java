package org.brapi_igpas.brapi.calls.commoncropnames;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.brapi_igpas.brapi.utils.PaginationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommoncropnamesServiceImpl implements CommoncropnamesService {
    private final CommoncropnamesDAO commoncropnamesDAO;
    private final PaginationService paginationService;

    public CommoncropnamesServiceImpl(CommoncropnamesDAO commoncropnamesDAO, PaginationService paginationService) {
        this.commoncropnamesDAO = commoncropnamesDAO;
        this.paginationService = paginationService;
    }

    public BrAPIDetailResponse getBrAPIDetailResponse(int page, int pageSize) {
        List<String> commoncropnames = commoncropnamesDAO.getAll();

        Pagination paginationInfo = paginationService.getPagination(commoncropnames.size(), page, pageSize);
        commoncropnames = paginationService.paginateList(commoncropnames, page, pageSize);

        return new BrAPIDetailResponse(paginationInfo, commoncropnames);
    }
}
