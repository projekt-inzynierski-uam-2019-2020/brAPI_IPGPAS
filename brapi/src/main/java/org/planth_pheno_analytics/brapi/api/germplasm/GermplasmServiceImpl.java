package org.planth_pheno_analytics.brapi.api.germplasm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GermplasmServiceImpl implements GermplasmService {
    private final GermplasmProjectionResources germplasmProjectionResources;
    private final GermplasmMapper germplasmMapper;

    public GermplasmServiceImpl(GermplasmProjectionResources germplasmProjectionResources, GermplasmMapper germplasmMapper) {
        this.germplasmProjectionResources = germplasmProjectionResources;
        this.germplasmMapper = germplasmMapper;
    }

    @Override
    public Page<Germplasm> getPagedGermplasms(Pageable pageable) {
        Page<GermplasmProjection> germplasmProjections = germplasmProjectionResources.getPagedGermplasmProjections(pageable);
        return germplasmProjections.map(germplasmMapper::mapToGermplasm);
    }
}
