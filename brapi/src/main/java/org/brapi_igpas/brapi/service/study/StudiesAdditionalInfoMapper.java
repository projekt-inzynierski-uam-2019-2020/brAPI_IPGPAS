package org.brapi_igpas.brapi.service.study;

import org.brapi_igpas.brapi.domain.calls.study.Study;
import org.brapi_igpas.brapi.domain.calls.study.StudyAdditionalInfo;
import org.brapi_igpas.brapi.service.AdditionalInfoMapper;
import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.brapi_igpas.igpas.service.RepositoryFacade;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudiesAdditionalInfoMapper implements AdditionalInfoMapper {
    private final RepositoryFacade repositoryFacade;

    public StudiesAdditionalInfoMapper(RepositoryFacade repositoryFacade) {
        this.repositoryFacade = repositoryFacade;
    }

    @Override
    public void mapAdditionalInfo(List<Study> studies) {
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
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("pH");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> ph = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setpH(ph);
        }
    }

    private void setAdditionalInfoVolumesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Volumes");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> volumes = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setVolumes(volumes);
        }
    }

    private void setAdditionalInfoPlotSizesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Plot size");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> plotSizes = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setPlotSizes(plotSizes);
        }
    }

    private void setAdditionalInfoAirHumidityForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Air humidity");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> airHumidity = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setAirHumidity(airHumidity);
        }
    }

    private void setAdditionalInfoSowingDensitiesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Sowing density");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> sowingDensities = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setSowingDensities(sowingDensities);
        }
    }

    private void setAdditionalInfoFactorsForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Factor");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> factors = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setFactors(factors);
        }
    }

    private void setAdditionalInfoTypesOfFertiliserForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Type of fertiliser");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> typesOfFertiliser = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setTypesOfFertiliser(typesOfFertiliser);
        }
    }

    private void setAdditionalInfoAmountOfFertiliserForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Amount of fertiliser");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> amountOfFertiliser = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setAmountOfFertiliser(amountOfFertiliser);
        }
    }

    private void setAdditionalInfoFrequenciesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Frequency");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> frequencies = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setFrequencies(frequencies);
        }
    }

    private void setAdditionalInfoAssayTypesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Assay Type");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> assayTypes = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setAssayTypes(assayTypes);
        }
    }

    private void setAdditionalInfoContainerTypesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Container type");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> containerTypes = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setContainerTypes(containerTypes);
        }
    }

    private void setAdditionalInfoDayTemperaturesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Day temperature");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> dayTemperatures = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setDayTemperatures(dayTemperatures);
        }
    }

    private void setAdditionalInfoIrrigationTypesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Irrigation type");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> irrigationTypes = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setIrrigationTypes(irrigationTypes);
        }
    }

    private void setAdditionalInfoCharacteristicsForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Characteristics");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> characteristics = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setCharacteristics(characteristics);
        }
    }

    private void setAdditionalInfoContainerVolumesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Container volume");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> containerVolumes = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setContainerVolumes(containerVolumes);
        }
    }

    private void setAdditionalInfoDailyPhotonFluxesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Daily photon flux");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> dailyPhotonFluxes = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setDailyPhotonFluxes(dailyPhotonFluxes);
        }
    }

    private void setAdditionalInfoNightTemperaturesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Night temperature");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> nightTemperatures = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setNightTemperatures(nightTemperatures);
        }
    }

    private void setAdditionalInfoExperimentalUnitsForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Experimental unit");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> experimentalUnits = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setExperimentalUnits(experimentalUnits);
        }
    }

    private void setAdditionalInfoContainerDimensionsForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Container dimension");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> containerDimensions = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setContainerDimensions(containerDimensions);
        }
    }

    private void setAdditionalInfoLengthOfLightPeriodsForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Length of light period");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> lengthOfLightPeriods = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setLengthOfLightPeriods(lengthOfLightPeriods);
        }
    }

    private void setAdditionalInfoNBeforeFertilisationForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("N before fertilisation");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> nBeforeFertilisation = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setnBeforeFertilisation(nBeforeFertilisation);
        }
    }

    private void setAdditionalInfoNumberOfPlantsPerContainerForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Number of plants per container");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> numberOfPlantsPerContainer = getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(values, study);
            ((StudyAdditionalInfo) additionalInfo).setNumberOfPlantsPerContainer(numberOfPlantsPerContainer);
        }
    }

    private List<String> getAdditionalInfoValuesWithValuesWithAttributeDisplayedNameForStudy(List<ValuesEntity> valuesWithAttributeDisplayedName, Study study) {
        long studyId = Long.parseLong(study.getStudyDbId());

        List<ValuesEntity> valuesWithStudyId = new ArrayList<>();
        for (ValuesEntity valuesEntity : valuesWithAttributeDisplayedName) {
            if (valuesEntity.getStudyId() == studyId) {
                valuesWithStudyId.add(valuesEntity);
            }
        }

        List<String> additionalInfoValues = new ArrayList<>();
        for (ValuesEntity values : valuesWithStudyId) {
            if (values.getValue() != null) {
                additionalInfoValues.add(values.getValue());
            }
        }
        return additionalInfoValues;
    }
}
