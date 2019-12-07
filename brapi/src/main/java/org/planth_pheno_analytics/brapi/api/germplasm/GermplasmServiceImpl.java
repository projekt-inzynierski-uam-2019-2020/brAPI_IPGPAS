package org.planth_pheno_analytics.brapi.api.germplasm;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.planth_pheno_analytics.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class GermplasmServiceImpl implements GermplasmService {
    private final GermplasmProjectionResources germplasmProjectionResources;
    private final GermplasmMapper germplasmMapper;
    private final GermplasmFilter germplasmFilter;

    public GermplasmServiceImpl(GermplasmProjectionResources germplasmProjectionResources, GermplasmMapper germplasmMapper,
                                GermplasmFilter germplasmFilter) {
        this.germplasmProjectionResources = germplasmProjectionResources;
        this.germplasmMapper = germplasmMapper;
        this.germplasmFilter = germplasmFilter;
    }

    @Override
    public List<Germplasm> getFilteredGermplasms(GermplasmCriteria germplasmCriteria) {
        Stream<Germplasm> germplasmsStream = germplasmProjectionResources.getGermplasms().stream()
                .map(germplasmMapper::mapToGermplasm);

        String germplasmName = germplasmCriteria.getGermplasmName();
        if (isParameterPresent(germplasmName)) {
            germplasmsStream = germplasmFilter.filterByGermplasmName(germplasmsStream, germplasmName);
        }
        String germplasmDbId = germplasmCriteria.getGermplasmDbId();
        if (isParameterPresent(germplasmDbId)) {
            germplasmsStream = germplasmFilter.filterByGermplasmDbId(germplasmsStream, germplasmDbId);
        }
        String commonCropName = germplasmCriteria.getCommonCropName();
        if (isParameterPresent(commonCropName)) {
            germplasmsStream = germplasmFilter.filterByCommonCropName(germplasmsStream, commonCropName);
        }
        String germplasmPUI = germplasmCriteria.getGermplasmPUI();
        if (isParameterPresent(germplasmPUI)) {
            germplasmsStream = germplasmFilter.filterByGermplasmPUI(germplasmsStream, germplasmPUI);
        }

        return germplasmsStream.collect(Collectors.toList());
    }
}
