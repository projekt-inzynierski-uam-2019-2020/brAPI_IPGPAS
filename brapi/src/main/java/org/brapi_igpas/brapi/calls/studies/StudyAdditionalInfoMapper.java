package org.brapi_igpas.brapi.calls.studies;

import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.brapi_igpas.igpas.repository.RepositoryFacade;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//TODO poprawic cala klase
@Service
public class StudyAdditionalInfoMapper {
    private final RepositoryFacade repositoryFacade;

    public StudyAdditionalInfoMapper(RepositoryFacade repositoryFacade) {
        this.repositoryFacade = repositoryFacade;
    }

    public void mapAdditionalInfoForList(List<Study> studies) {
        mapAdditionalInfoPHForStudies(studies);
        mapAdditionalInfoVolumesForStudies(studies);
        mapAdditionalInfoPlotSizesForStudies(studies);
        mapAdditionalInfoAirHumidityForStudies(studies);
        mapAdditionalInfoSowingDensitiesForStudies(studies);
        mapAdditionalInfoFactorsForStudies(studies);
        mapAdditionalInfoTypesOfFertiliserForStudies(studies);
        mapAdditionalInfoAmountOfFertiliserForStudies(studies);
        mapAdditionalInfoFrequenciesForStudies(studies);
        mapAdditionalInfoAssayTypesForStudies(studies);
        mapAdditionalInfoContainerTypesForStudies(studies);
        mapAdditionalInfoDayTemperaturesForStudies(studies);
        mapAdditionalInfoIrrigationTypesForStudies(studies);
        mapAdditionalInfoCharacteristicsForStudies(studies);
        mapAdditionalInfoContainerVolumesForStudies(studies);
        mapAdditionalInfoDailyPhotonFluxesForStudies(studies);
        mapAdditionalInfoNightTemperaturesForStudies(studies);
        mapAdditionalInfoExperimentalUnitsForStudies(studies);
        mapAdditionalInfoContainerDimensionsForStudies(studies);
        mapAdditionalInfoLengthOfLightPeriodsForStudies(studies);
        mapAdditionalInfoNBeforeFertilisationForStudies(studies);
        mapAdditionalInfoNumberOfPlantsPerContainerForStudies(studies);
    }

    private void mapAdditionalInfoPHForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("pH");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> ph = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setpH(ph);
        }
    }

    private void mapAdditionalInfoVolumesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Volumes");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> volumes = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setVolumes(volumes);
        }
    }

    private void mapAdditionalInfoPlotSizesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Plot size");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> plotSizes = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setPlotSizes(plotSizes);
        }
    }

    private void mapAdditionalInfoAirHumidityForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Air humidity");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> airHumidity = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setAirHumidity(airHumidity);
        }
    }

    private void mapAdditionalInfoSowingDensitiesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Sowing density");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> sowingDensities = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setSowingDensities(sowingDensities);
        }
    }

    private void mapAdditionalInfoFactorsForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Factor");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> factors = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setFactors(factors);
        }
    }

    private void mapAdditionalInfoTypesOfFertiliserForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Type of fertiliser");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> typesOfFertiliser = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setTypesOfFertiliser(typesOfFertiliser);
        }
    }

    private void mapAdditionalInfoAmountOfFertiliserForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Amount of fertiliser");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> amountOfFertiliser = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setAmountOfFertiliser(amountOfFertiliser);
        }
    }

    private void mapAdditionalInfoFrequenciesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Frequency");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> frequencies = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setFrequencies(frequencies);
        }
    }

    private void mapAdditionalInfoAssayTypesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Assay Type");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> assayTypes = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setAssayTypes(assayTypes);
        }
    }

    private void mapAdditionalInfoContainerTypesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Container type");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> containerTypes = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setContainerTypes(containerTypes);
        }
    }

    private void mapAdditionalInfoDayTemperaturesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Day temperature");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> dayTemperatures = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setDayTemperatures(dayTemperatures);
        }
    }

    private void mapAdditionalInfoIrrigationTypesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Irrigation type");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> irrigationTypes = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setIrrigationTypes(irrigationTypes);
        }
    }

    private void mapAdditionalInfoCharacteristicsForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Characteristics");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> characteristics = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setCharacteristics(characteristics);
        }
    }

    private void mapAdditionalInfoContainerVolumesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Container volume");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> containerVolumes = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setContainerVolumes(containerVolumes);
        }
    }

    private void mapAdditionalInfoDailyPhotonFluxesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Daily photon flux");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> dailyPhotonFluxes = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setDailyPhotonFluxes(dailyPhotonFluxes);
        }
    }

    private void mapAdditionalInfoNightTemperaturesForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Night temperature");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> nightTemperatures = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setNightTemperatures(nightTemperatures);
        }
    }

    private void mapAdditionalInfoExperimentalUnitsForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Experimental unit");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> experimentalUnits = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setExperimentalUnits(experimentalUnits);
        }
    }

    private void mapAdditionalInfoContainerDimensionsForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Container dimension");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> containerDimensions = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setContainerDimensions(containerDimensions);
        }
    }

    private void mapAdditionalInfoLengthOfLightPeriodsForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Length of light period");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> lengthOfLightPeriods = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setLengthOfLightPeriods(lengthOfLightPeriods);
        }
    }

    private void mapAdditionalInfoNBeforeFertilisationForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("N before fertilisation");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> nBeforeFertilisation = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setnBeforeFertilisation(nBeforeFertilisation);
        }
    }

    private void mapAdditionalInfoNumberOfPlantsPerContainerForStudies(List<Study> studies) {
        List<ValuesEntity> values = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Number of plants per container");
        for (Study study : studies) {
            Object additionalInfo = study.getAdditionalInfo();
            List<String> numberOfPlantsPerContainer = getAdditionalInfoValuesFromValuesWithStudyId(values, Long.parseLong(study.getStudyDbId()));
            ((StudyAdditionalInfo) additionalInfo).setNumberOfPlantsPerContainer(numberOfPlantsPerContainer);
        }
    }

    private List<String> getAdditionalInfoValuesFromValuesWithStudyId(List<ValuesEntity> values, long studyId) {
        List<ValuesEntity> valuesWithStudyId = new ArrayList<>();
        for (ValuesEntity valuesEntity : values) {
            if (valuesEntity.getStudyId() == studyId) {
                valuesWithStudyId.add(valuesEntity);
            }
        }

        List<String> additionalInfoValues = new ArrayList<>();
        for (ValuesEntity value : valuesWithStudyId) {
            if (value.getValue() != null) {
                additionalInfoValues.add(value.getValue());
            }
        }
        return additionalInfoValues;
    }
}
