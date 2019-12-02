package org.planth_pheno_analytics.brapi.api.study.seasons;

import org.planth_pheno_analytics.data.entity.ValueEntity;
import org.planth_pheno_analytics.data.repository.AttributeEntityRepository;
import org.planth_pheno_analytics.data.specifications.ValueSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeasonServiceImpl implements SeasonService {
    private final SeasonProjectionResources seasonProjectionResources;
    private final SeasonMapper seasonMapper;

    public SeasonServiceImpl(SeasonProjectionResources seasonProjectionResources, SeasonMapper seasonMapper) {
        this.seasonProjectionResources = seasonProjectionResources;
        this.seasonMapper = seasonMapper;
    }

    @Override
    public Page<Season> getPagedSeasons(Pageable pageable) {
        Page<SeasonProjection> seasonProjectionPage = seasonProjectionResources.getAll(pageable);
        return seasonProjectionPage.map(seasonMapper::mapToSeason);
    }

    @Override
    public List<Season> getSeasonsByStudyDbId(Integer studyDbId) {
        return seasonProjectionResources.getByStudyId(studyDbId).stream()
                .map(seasonMapper::mapToSeason)
                .collect(Collectors.toList());
    }
}
