package org.brapi_igpas.brapi.calls.studies;

import java.util.ArrayList;
import java.util.List;

public class StudyAdditionalInfo {
    private String description = "";
    private List<String> volumes;
    private List<String> typesOfFertiliser;
    private List<String> sowingDensities;
    private List<String> plotSizes;
    private List<String> pH;
    private List<String> numberOfPlantsPerContainer;
    private List<String> nightTemperatures;
    private List<String> nBeforeFertilisation;
    private List<String> lengthOfLightPeriods;
    private List<String> irrigationTypes;
    private List<String> frequencies;
    private List<String> factors;
    private List<String> experimentalUnits;
    private List<String> dayTemperatures;
    private List<String> dailyPhotonFluxes;
    private List<String> containerVolumes;
    private List<String> containerTypes;
    private List<String> containerDimensions;
    private List<String> characteristics;
    private List<String> assayTypes;
    private List<String> amountOfFertiliser;
    private List<String> airHumidity;

    public StudyAdditionalInfo() {
        volumes = new ArrayList<>();
        typesOfFertiliser = new ArrayList<>();
        sowingDensities = new ArrayList<>();
        plotSizes = new ArrayList<>();
        pH = new ArrayList<>();
        numberOfPlantsPerContainer = new ArrayList<>();
        nightTemperatures = new ArrayList<>();
        nBeforeFertilisation = new ArrayList<>();
        lengthOfLightPeriods = new ArrayList<>();
        irrigationTypes = new ArrayList<>();
        frequencies = new ArrayList<>();
        factors = new ArrayList<>();
        experimentalUnits = new ArrayList<>();
        dayTemperatures = new ArrayList<>();
        dailyPhotonFluxes = new ArrayList<>();
        containerVolumes = new ArrayList<>();
        containerTypes = new ArrayList<>();
        containerDimensions = new ArrayList<>();
        characteristics = new ArrayList<>();
        assayTypes = new ArrayList<>();
        amountOfFertiliser = new ArrayList<>();
        airHumidity = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<String> volumes) {
        this.volumes = volumes;
    }

    public List<String> getTypesOfFertiliser() {
        return typesOfFertiliser;
    }

    public void setTypesOfFertiliser(List<String> typesOfFertiliser) {
        this.typesOfFertiliser = typesOfFertiliser;
    }

    public List<String> getSowingDensities() {
        return sowingDensities;
    }

    public void setSowingDensities(List<String> sowingDensities) {
        this.sowingDensities = sowingDensities;
    }

    public List<String> getPlotSizes() {
        return plotSizes;
    }

    public void setPlotSizes(List<String> plotSizes) {
        this.plotSizes = plotSizes;
    }

    public List<String> getpH() {
        return pH;
    }

    public void setpH(List<String> pH) {
        this.pH = pH;
    }

    public List<String> getNumberOfPlantsPerContainer() {
        return numberOfPlantsPerContainer;
    }

    public void setNumberOfPlantsPerContainer(List<String> numberOfPlantsPerContainer) {
        this.numberOfPlantsPerContainer = numberOfPlantsPerContainer;
    }

    public List<String> getNightTemperatures() {
        return nightTemperatures;
    }

    public void setNightTemperatures(List<String> nightTemperatures) {
        this.nightTemperatures = nightTemperatures;
    }

    public List<String> getnBeforeFertilisation() {
        return nBeforeFertilisation;
    }

    public void setnBeforeFertilisation(List<String> nBeforeFertilisation) {
        this.nBeforeFertilisation = nBeforeFertilisation;
    }

    public List<String> getLengthOfLightPeriods() {
        return lengthOfLightPeriods;
    }

    public void setLengthOfLightPeriods(List<String> lengthOfLightPeriods) {
        this.lengthOfLightPeriods = lengthOfLightPeriods;
    }

    public List<String> getIrrigationTypes() {
        return irrigationTypes;
    }

    public void setIrrigationTypes(List<String> irrigationTypes) {
        this.irrigationTypes = irrigationTypes;
    }

    public List<String> getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(List<String> frequencies) {
        this.frequencies = frequencies;
    }

    public List<String> getFactors() {
        return factors;
    }

    public void setFactors(List<String> factors) {
        this.factors = factors;
    }

    public List<String> getExperimentalUnits() {
        return experimentalUnits;
    }

    public void setExperimentalUnits(List<String> experimentalUnits) {
        this.experimentalUnits = experimentalUnits;
    }

    public List<String> getDayTemperatures() {
        return dayTemperatures;
    }

    public void setDayTemperatures(List<String> dayTemperatures) {
        this.dayTemperatures = dayTemperatures;
    }

    public List<String> getDailyPhotonFluxes() {
        return dailyPhotonFluxes;
    }

    public void setDailyPhotonFluxes(List<String> dailyPhotonFluxes) {
        this.dailyPhotonFluxes = dailyPhotonFluxes;
    }

    public List<String> getContainerVolumes() {
        return containerVolumes;
    }

    public void setContainerVolumes(List<String> containerVolumes) {
        this.containerVolumes = containerVolumes;
    }

    public List<String> getContainerTypes() {
        return containerTypes;
    }

    public void setContainerTypes(List<String> containerTypes) {
        this.containerTypes = containerTypes;
    }

    public List<String> getContainerDimensions() {
        return containerDimensions;
    }

    public void setContainerDimensions(List<String> containerDimensions) {
        this.containerDimensions = containerDimensions;
    }

    public List<String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<String> characteristics) {
        this.characteristics = characteristics;
    }

    public List<String> getAssayTypes() {
        return assayTypes;
    }

    public void setAssayTypes(List<String> assayTypes) {
        this.assayTypes = assayTypes;
    }

    public List<String> getAmountOfFertiliser() {
        return amountOfFertiliser;
    }

    public void setAmountOfFertiliser(List<String> amountOfFertiliser) {
        this.amountOfFertiliser = amountOfFertiliser;
    }

    public List<String> getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(List<String> airHumidity) {
        this.airHumidity = airHumidity;
    }
}
