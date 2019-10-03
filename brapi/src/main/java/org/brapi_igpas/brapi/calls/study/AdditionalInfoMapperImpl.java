package org.brapi_igpas.brapi.calls.study;

import org.brapi_igpas.brapi.calls.study.studies.Study;
import org.brapi_igpas.brapi.calls.study.studies.StudyAdditionalInfo;
import org.brapi_igpas.igpas.entity.Value;
import org.brapi_igpas.igpas.service.DbValuesFacade;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdditionalInfoMapperImpl implements AdditionalInfoMapper {
    private final DbValuesFacade dbValuesFacade;

    public AdditionalInfoMapperImpl(DbValuesFacade dbValuesFacade) {
        this.dbValuesFacade = dbValuesFacade;
    }

    @Override
    public void setAdditionalInfoForStudies(List<Study> studies) {
        setAdditionalInfoPHForStudies(studies);
        setAdditionalInfoVolumesForStudies(studies);
        setAdditionalInfoPlotSizesForStudies(studies);
        setAdditionalInfoAirHumidityForStudies(studies);
        setAdditionalInfoSowingDensitiesForStudies(studies);
        setAdditionalInfoFactorsForStudies(studies);
        setAdditionalInfoTypesOfFertiliserForStudies(studies);
        setAdditionalInfoAmountOfFertiliserForStudies(studies);
        setAdditionalInfoFrequenciesForStudies(studies);
        setAdditionalInfoAssayTypesForStudies(studies);
        setAdditionalInfoContainerTypesForStudies(studies);
        setAdditionalInfoDayTemperaturesForStudies(studies);
        setAdditionalInfoIrrigationTypesForStudies(studies);
        setAdditionalInfoCharacteristicsForStudies(studies);
        setAdditionalInfoContainerVolumesForStudies(studies);
        setAdditionalInfoDailyPhotonFluxesForStudies(studies);
        setAdditionalInfoNightTemperaturesForStudies(studies);
        setAdditionalInfoExperimentalUnitsForStudies(studies);
        setAdditionalInfoContainerDimensionsForStudies(studies);
        setAdditionalInfoLengthOfLightPeriodsForStudies(studies);
        setAdditionalInfoNBeforeFertilisationForStudies(studies);
        setAdditionalInfoNumberOfPlantsPerContainerForStudies(studies);
    }

    private void setAdditionalInfoPHForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("pH");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> ph = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setpH(ph);
        }
    }

    private void setAdditionalInfoVolumesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Volumes");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> volumes = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setVolumes(volumes);
        }
    }

    private void setAdditionalInfoPlotSizesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Plot size");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> plotSizes = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setPlotSizes(plotSizes);
        }
    }

    private void setAdditionalInfoAirHumidityForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Air humidity");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> airHumidity = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setAirHumidity(airHumidity);
        }
    }

    private void setAdditionalInfoSowingDensitiesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Sowing density");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> sowingDensities = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setSowingDensities(sowingDensities);
        }
    }

    private void setAdditionalInfoFactorsForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Factor");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> factors = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setFactors(factors);
        }
    }

    private void setAdditionalInfoTypesOfFertiliserForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Type of fertiliser");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> typesOfFertiliser = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setTypesOfFertiliser(typesOfFertiliser);
        }
    }

    private void setAdditionalInfoAmountOfFertiliserForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Amount of fertiliser");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> amountOfFertiliser = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setAmountOfFertiliser(amountOfFertiliser);
        }
    }

    private void setAdditionalInfoFrequenciesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Frequency");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> frequencies = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setFrequencies(frequencies);
        }
    }

    private void setAdditionalInfoAssayTypesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Assay Type");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> assayTypes = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setAssayTypes(assayTypes);
        }
    }

    private void setAdditionalInfoContainerTypesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Container type");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> containerTypes = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setContainerTypes(containerTypes);
        }
    }

    private void setAdditionalInfoDayTemperaturesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Day temperature");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> dayTemperatures = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setDayTemperatures(dayTemperatures);
        }
    }

    private void setAdditionalInfoIrrigationTypesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Irrigation type");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> irrigationTypes = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setIrrigationTypes(irrigationTypes);
        }
    }

    private void setAdditionalInfoCharacteristicsForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Characteristics");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> characteristics = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setCharacteristics(characteristics);
        }
    }

    private void setAdditionalInfoContainerVolumesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Container volume");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> containerVolumes = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setContainerVolumes(containerVolumes);
        }
    }

    private void setAdditionalInfoDailyPhotonFluxesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Daily photon flux");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> dailyPhotonFluxes = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setDailyPhotonFluxes(dailyPhotonFluxes);
        }
    }

    private void setAdditionalInfoNightTemperaturesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Night temperature");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> nightTemperatures = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setNightTemperatures(nightTemperatures);
        }
    }

    private void setAdditionalInfoExperimentalUnitsForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Experimental unit");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> experimentalUnits = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setExperimentalUnits(experimentalUnits);
        }
    }

    private void setAdditionalInfoContainerDimensionsForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Container dimension");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> containerDimensions = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setContainerDimensions(containerDimensions);
        }
    }

    private void setAdditionalInfoLengthOfLightPeriodsForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Length of light period");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> lengthOfLightPeriods = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setLengthOfLightPeriods(lengthOfLightPeriods);
        }
    }

    private void setAdditionalInfoNBeforeFertilisationForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("N before fertilisation");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> nBeforeFertilisation = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setnBeforeFertilisation(nBeforeFertilisation);
        }
    }

    private void setAdditionalInfoNumberOfPlantsPerContainerForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Number of plants per container");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> numberOfPlantsPerContainer = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setNumberOfPlantsPerContainer(numberOfPlantsPerContainer);
        }
    }

    private List<String> getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(List<Value> valuesWithAttributeDisplayedName, Study study) {
        long studyId = Long.parseLong(study.getStudyDbId());
        List<Value> valuesWithStudyId = dbValuesFacade.getAllValuesWithStudyIdFromValuesWithAttributeDisplayedName(studyId, valuesWithAttributeDisplayedName);

        List<String> additionalInfoValues = new ArrayList<>();
        for (Value value : valuesWithStudyId) {
            if (value.getValue() != null) {
                additionalInfoValues.add(value.getValue());
            }
        }
        return additionalInfoValues;
    }
}
