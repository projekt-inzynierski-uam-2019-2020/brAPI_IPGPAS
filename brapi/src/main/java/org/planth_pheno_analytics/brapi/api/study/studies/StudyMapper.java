package org.planth_pheno_analytics.brapi.api.study.studies;

import org.planth_pheno_analytics.brapi.api.study.seasons.Season;
import org.planth_pheno_analytics.brapi.api.study.seasons.SeasonService;
import org.planth_pheno_analytics.data.repository.ValueEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyMapper {
    private final SeasonService seasonService;
    private final ValueEntityRepository valueEntityRepository;

    public StudyMapper(SeasonService seasonService, ValueEntityRepository valueEntityRepository) {
        this.seasonService = seasonService;
        this.valueEntityRepository = valueEntityRepository;
    }

    Study mapToStudy(StudyProjection projection) {
        Study study = new Study();
        study.setActive("false");
        study.setStudyDbId(projection.getStudyDbId());
        study.setTrialDbId(projection.getTrialDbId());
        study.setDocumentationURL("http://cropnet.pl/plantphenodb/index.php?id=" + study.getStudyDbId());
        study.setName(projection.getName());
        study.setStudyName(projection.getStudyName());
        study.setTrialName(projection.getTrialName());

        Integer studyId = Integer.valueOf(study.getStudyDbId());

        List<Season> seasons = seasonService.getSeasonsByStudyDbId(studyId);
        study.setSeasons(seasons);

        Optional<String> value = valueEntityRepository.getValueByAttributeDisplayedNameAndStudyId("Growth facility", studyId);
        value.ifPresent(study::setStudyType);

        value = valueEntityRepository.getValueByAttributeDisplayedNameAndStudyId("Geographic location", studyId);
        value.ifPresent(study::setLocationName);

        value = valueEntityRepository.getValueByAttributeDisplayedNameAndStudyId("Study start", studyId);
        value.ifPresent(study::setStartDate);

        value = valueEntityRepository.getValueByAttributeDisplayedNameAndStudyId("Organism", studyId);
        value.ifPresent(study::setCommonCropName);

        study.setAdditionalInfo(mapStudyAdditionalInfo(projection.getStudyAdditionalInfoDescription(),
                Integer.valueOf(study.getStudyDbId())
        ));

        return study;
    }

    private StudyAdditionalInfo mapStudyAdditionalInfo(String description, Integer studyDbId) {
        StudyAdditionalInfo studyAdditionalInfo = new StudyAdditionalInfo();
        studyAdditionalInfo.setDescription(description);

        List<String> values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("pH", studyDbId);
        studyAdditionalInfo.setpH(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Plot size", studyDbId);
        studyAdditionalInfo.setPlotSizes(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Volumes", studyDbId);
        studyAdditionalInfo.setVolumes(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Air humidity", studyDbId);
        studyAdditionalInfo.setAirHumidity(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Sowing density", studyDbId);
        studyAdditionalInfo.setSowingDensities(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Factor", studyDbId);
        studyAdditionalInfo.setFactors(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Type of fertiliser", studyDbId);
        studyAdditionalInfo.setTypesOfFertiliser(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Amount of fertiliser", studyDbId);
        studyAdditionalInfo.setAmountOfFertiliser(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Sowing density", studyDbId);
        studyAdditionalInfo.setSowingDensities(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Frequency", studyDbId);
        studyAdditionalInfo.setFrequencies(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Assay Type", studyDbId);
        studyAdditionalInfo.setAssayTypes(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Container type", studyDbId);
        studyAdditionalInfo.setContainerTypes(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Day temperature", studyDbId);
        studyAdditionalInfo.setDayTemperatures(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Irrigation type", studyDbId);
        studyAdditionalInfo.setIrrigationTypes(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Characteristics", studyDbId);
        studyAdditionalInfo.setCharacteristics(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Container type", studyDbId);
        studyAdditionalInfo.setContainerTypes(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Container volume", studyDbId);
        studyAdditionalInfo.setContainerVolumes(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Daily photon flux", studyDbId);
        studyAdditionalInfo.setDailyPhotonFluxes(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Night temperature", studyDbId);
        studyAdditionalInfo.setNightTemperatures(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Experimental unit", studyDbId);
        studyAdditionalInfo.setExperimentalUnits(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Container dimension", studyDbId);
        studyAdditionalInfo.setContainerDimensions(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Length of light period", studyDbId);
        studyAdditionalInfo.setLengthOfLightPeriods(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("N before fertilisation", studyDbId);
        studyAdditionalInfo.setnBeforeFertilisation(values);

        values = valueEntityRepository.getAllValuesByAttributeDisplayedNameAndStudyId("Number of plants per container", studyDbId);
        studyAdditionalInfo.setNumberOfPlantsPerContainer(values);

        return studyAdditionalInfo;
    }
}
