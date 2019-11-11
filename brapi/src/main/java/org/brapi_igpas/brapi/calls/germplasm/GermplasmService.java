package org.brapi_igpas.brapi.calls.germplasm;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;

public interface GermplasmService {
    BrAPIDetailResponse getBrAPIDetailResponse(String germplasmPUI, String germplasmDbId, String germplasmName,
                                               String commonCropName, int page, int pageSize);
}
