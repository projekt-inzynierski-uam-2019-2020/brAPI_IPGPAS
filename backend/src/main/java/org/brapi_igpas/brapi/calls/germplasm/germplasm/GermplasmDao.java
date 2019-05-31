package org.brapi_igpas.brapi.calls.germplasm.germplasm;

import org.brapi_igpas.brapi.BrApiDetailResponse;

public interface GermplasmDao {
    BrApiDetailResponse getAll(String germplasmPUI, String germplasmDbId, String germplasmName,
                               String commonCropName, int page, int pageSize);
}
