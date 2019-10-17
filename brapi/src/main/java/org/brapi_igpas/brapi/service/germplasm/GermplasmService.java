package org.brapi_igpas.brapi.service.germplasm;

import org.brapi_igpas.brapi.domain.response.BrApiDetailResponse;

public interface GermplasmService {
    BrApiDetailResponse getBrApiDetailResponse(String germplasmPUI, String germplasmDbId, String germplasmName,
                                               String commonCropName, int page, int pageSize);
}
