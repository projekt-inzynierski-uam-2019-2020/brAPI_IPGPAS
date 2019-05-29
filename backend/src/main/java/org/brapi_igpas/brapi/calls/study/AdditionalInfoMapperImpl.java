package org.brapi_igpas.brapi.calls.study;

import org.brapi_igpas.brapi.calls.study.studies.Study;
import org.brapi_igpas.brapi.calls.study.studies.StudyAdditionalInfo;
import org.brapi_igpas.igpas.entity.Value;
import org.brapi_igpas.igpas.service.DbValuesFacade;

import java.util.List;

public class AdditionalInfoMapperImpl implements AdditionalInfoMapper {
    private final DbValuesFacade dbValuesFacade;

    public AdditionalInfoMapperImpl(DbValuesFacade dbValuesFacade) {
        this.dbValuesFacade = dbValuesFacade;
    }

    @Override
    public void setAdditionalInfoForStudies(List<Study> studies) {
        setAdditionalInfoPHForStudies(studies);
        setAdditionalInfoVolumeForStudies(studies);
        setAdditionalInfoPlotSizeForStudies(studies);
        setAdditionalInfoAirHumidityForStudies(studies);
        setAdditionalInfoSowingDensityForStudies(studies);
        setAdditionalInfoFactorForStudies(studies);
        setAdditionalInfoTypeOfFertiliserForStudies(studies);
        setAdditionalInfoAmountOfFertiliserForStudies(studies);
        setAdditionalInfoFrequencyForStudies(studies);
        setAdditionalInfoAssayTypeForStudies(studies);
        setAdditionalInfoContainerTypeForStudies(studies);
        setAdditionalInfoDayTemperatureForStudies(studies);
        setAdditionalInfoIrrigationTypeForStudies(studies);
        setAdditionalInfoCharacteristicsForStudies(studies);
        setAdditionalInfoContainerVolumeForStudies(studies);
        setAdditionalInfoDailyPhotonFluxForStudies(studies);
        setAdditionalInfoNightTemperatureForStudies(studies);
        setAdditionalInfoExperimentalUnitForStudies(studies);
        setAdditionalInfoContainerDimensionForStudies(studies);
        setAdditionalInfoLengthOfLightPeriodForStudies(studies);
        setAdditionalInfoNBeforeFertilisationForStudies(studies);
        setAdditionalInfoNumberOfPlantsPerContainerForStudies(studies);
    }

    private void setAdditionalInfoPHForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("pH");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setpH(v.getValue()));
        });
    }

    private void setAdditionalInfoVolumeForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Volume");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setVolume(v.getValue()));
        });
    }

    private void setAdditionalInfoPlotSizeForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Plot size");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setPlotSize(v.getValue()));
        });
    }

    private void setAdditionalInfoAirHumidityForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Air humidity");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setAirHumidity(v.getValue()));
        });
    }

    private void setAdditionalInfoSowingDensityForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Sowing density");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setSowingDensity(v.getValue()));
        });
    }

    private void setAdditionalInfoFactorForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Factor");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setFactor(v.getValue()));
        });
    }

    private void setAdditionalInfoTypeOfFertiliserForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Type of fertiliser");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setTypeOfFertiliser(v.getValue()));
        });
    }

    private void setAdditionalInfoAmountOfFertiliserForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Amount of fertiliser");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setAmountOfFertiliser(v.getValue()));
        });
    }

    private void setAdditionalInfoFrequencyForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Frequency");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setFrequency(v.getValue()));
        });
    }

    private void setAdditionalInfoAssayTypeForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Assay type");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setAssayType(v.getValue()));
        });
    }

    private void setAdditionalInfoContainerTypeForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Container type");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setContainerType(v.getValue()));
        });
    }

    private void setAdditionalInfoDayTemperatureForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Day temperature");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setDayTemperature(v.getValue()));
        });
    }

    private void setAdditionalInfoIrrigationTypeForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Irrigation type");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setIrrigationType(v.getValue()));
        });
    }

    private void setAdditionalInfoCharacteristicsForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Characteristics");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setCharacteristics(v.getValue()));
        });
    }

    private void setAdditionalInfoContainerVolumeForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Container volume");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setContainerVolume(v.getValue()));
        });
    }

    private void setAdditionalInfoDailyPhotonFluxForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Daily photon flux");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setDailyPhotonFlux(v.getValue()));
        });
    }

    private void setAdditionalInfoNightTemperatureForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Night temperature");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setNightTemperature(v.getValue()));
        });
    }

    private void setAdditionalInfoExperimentalUnitForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Experimental unit");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setExperimentalUnit(v.getValue()));
        });
    }

    private void setAdditionalInfoContainerDimensionForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Container dimension");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setContainerDimension(v.getValue()));
        });
    }

    private void setAdditionalInfoLengthOfLightPeriodForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Length of light period");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setLengthOfLightPeriod(v.getValue()));
        });
    }

    private void setAdditionalInfoNBeforeFertilisationForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("N before fertilisation");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setnBeforeFertilisation(v.getValue()));
        });
    }

    private void setAdditionalInfoNumberOfPlantsPerContainerForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Number of plants per container");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            Object additionalInfo = study.getAdditionalInfo();
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> ((StudyAdditionalInfo) additionalInfo).setNumberOfPlantsPerContainer(v.getValue()));
        });
    }
}
