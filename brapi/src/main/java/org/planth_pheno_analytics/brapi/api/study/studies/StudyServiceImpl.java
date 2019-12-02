package org.planth_pheno_analytics.brapi.api.study.studies;

import org.planth_pheno_analytics.brapi.api.germplasm.Germplasm;
import org.planth_pheno_analytics.brapi.api.germplasm.GermplasmMapper;
import org.planth_pheno_analytics.brapi.api.germplasm.GermplasmProjectionResources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyServiceImpl implements StudyService {
    private final StudyProjectionResources studyProjectionResources;
    private final StudyMapper studyMapper;
    private final GermplasmProjectionResources germplasmProjectionResources;
    private final GermplasmMapper germplasmMapper;

    public StudyServiceImpl(StudyProjectionResources studyProjectionResources, StudyMapper studyMapper,
                            GermplasmProjectionResources germplasmProjectionResources, GermplasmMapper germplasmMapper) {
        this.studyProjectionResources = studyProjectionResources;
        this.studyMapper = studyMapper;
        this.germplasmProjectionResources = germplasmProjectionResources;
        this.germplasmMapper = germplasmMapper;
    }

    @Override
    public Page<Study> getPagedStudies(Pageable pageable) {
        return studyProjectionResources.getPagedStudiesProjections(pageable)
                .map(studyMapper::mapToStudy);
    }

    @Override
    public Page<Germplasm> getPagedStudiesGermplasmsByStudyId(String studyDbId, Pageable pageable) {
        Integer parsedStudyDbId = Integer.parseInt(studyDbId);
        return germplasmProjectionResources.getPagedStudiesGermplasmsByStudyDbId(parsedStudyDbId, pageable)
                .map(germplasmMapper::mapToGermplasm);
    }

    @Override
    public List<Study> getStudiesWithTrialDbId(Integer trialDbId) {
        return studyProjectionResources.getStudiesProjectionsWithTrialDbId(trialDbId).stream()
                .map(studyMapper::mapToStudy)
                .collect(Collectors.toList());
    }
}
