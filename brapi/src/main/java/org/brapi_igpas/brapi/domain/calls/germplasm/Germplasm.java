package org.brapi_igpas.brapi.domain.calls.germplasm;

import org.brapi_igpas.brapi.domain.calls.germplasm.taxonId.TaxonId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Germplasm {
    private String accessionNumber;
    private String acquisitionDate;
    private String biologicalStatusOfAccessionCode;
    private String breedingMethodDbId;
    private String commonCropName;
    private String countryOfOriginCode;
    private String defaultDisplayName;
    private String documentationURL;
    private List<String> donors;
    private String genus;
    private String germplasmDbId;
    private String germplasmGenus;
    private String germplasmName;
    private String germplasmPUI;
    private String germplasmSpecies;
    private String instituteCode;
    private String instituteName;
    private String pedigree;
    private String seedSource;
    private String species;
    private String speciesAuthority;
    private String subtaxa;
    private String subtaxaAuthority;
    private List<String> synonyms;
    private List<TaxonId> taxonIds;
    private List<String> typeOfGermplasmStorageCode;

    public Germplasm() {
        taxonIds = new ArrayList<>();
    }

    public Germplasm(String commonCropName, String germplasmDbId, String germplasmName, String germplasmPUI) {
        this();
        this.commonCropName = commonCropName;
        this.germplasmDbId = germplasmDbId;
        this.germplasmName = germplasmName;
        this.germplasmPUI = germplasmPUI;
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getBiologicalStatusOfAccessionCode() {
        return biologicalStatusOfAccessionCode;
    }

    public void setBiologicalStatusOfAccessionCode(String biologicalStatusOfAccessionCode) {
        this.biologicalStatusOfAccessionCode = biologicalStatusOfAccessionCode;
    }

    public String getBreedingMethodDbId() {
        return breedingMethodDbId;
    }

    public void setBreedingMethodDbId(String breedingMethodDbId) {
        this.breedingMethodDbId = breedingMethodDbId;
    }

    public String getCommonCropName() {
        return commonCropName;
    }

    public void setCommonCropName(String commonCropName) {
        this.commonCropName = commonCropName;
    }

    public String getCountryOfOriginCode() {
        return countryOfOriginCode;
    }

    public void setCountryOfOriginCode(String countryOfOriginCode) {
        this.countryOfOriginCode = countryOfOriginCode;
    }

    public String getDefaultDisplayName() {
        return defaultDisplayName;
    }

    public void setDefaultDisplayName(String defaultDisplayName) {
        this.defaultDisplayName = defaultDisplayName;
    }

    public String getDocumentationURL() {
        return documentationURL;
    }

    public void setDocumentationURL(String documentationURL) {
        this.documentationURL = documentationURL;
    }

    public List<String> getDonors() {
        return donors;
    }

    public void setDonors(List<String> donors) {
        this.donors = donors;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getGermplasmDbId() {
        return germplasmDbId;
    }

    public void setGermplasmDbId(String germplasmDbId) {
        this.germplasmDbId = germplasmDbId;
    }

    public String getGermplasmGenus() {
        return germplasmGenus;
    }

    public void setGermplasmGenus(String germplasmGenus) {
        this.germplasmGenus = germplasmGenus;
    }

    public String getGermplasmName() {
        return germplasmName;
    }

    public void setGermplasmName(String germplasmName) {
        this.germplasmName = germplasmName;
    }

    public String getGermplasmPUI() {
        return germplasmPUI;
    }

    public void setGermplasmPUI(String germplasmPUI) {
        this.germplasmPUI = germplasmPUI;
    }

    public String getGermplasmSpecies() {
        return germplasmSpecies;
    }

    public void setGermplasmSpecies(String germplasmSpecies) {
        this.germplasmSpecies = germplasmSpecies;
    }

    public String getInstituteCode() {
        return instituteCode;
    }

    public void setInstituteCode(String instituteCode) {
        this.instituteCode = instituteCode;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getPedigree() {
        return pedigree;
    }

    public void setPedigree(String pedigree) {
        this.pedigree = pedigree;
    }

    public String getSeedSource() {
        return seedSource;
    }

    public void setSeedSource(String seedSource) {
        this.seedSource = seedSource;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSpeciesAuthority() {
        return speciesAuthority;
    }

    public void setSpeciesAuthority(String speciesAuthority) {
        this.speciesAuthority = speciesAuthority;
    }

    public String getSubtaxa() {
        return subtaxa;
    }

    public void setSubtaxa(String subtaxa) {
        this.subtaxa = subtaxa;
    }

    public String getSubtaxaAuthority() {
        return subtaxaAuthority;
    }

    public void setSubtaxaAuthority(String subtaxaAuthority) {
        this.subtaxaAuthority = subtaxaAuthority;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<TaxonId> getTaxonIds() {
        return taxonIds;
    }

    public void setTaxonIds(List<TaxonId> taxonIds) {
        this.taxonIds = taxonIds;
    }

    public List<String> getTypeOfGermplasmStorageCode() {
        return typeOfGermplasmStorageCode;
    }

    public void setTypeOfGermplasmStorageCode(List<String> typeOfGermplasmStorageCode) {
        this.typeOfGermplasmStorageCode = typeOfGermplasmStorageCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Germplasm germplasm = (Germplasm) o;
        return Objects.equals(accessionNumber, germplasm.accessionNumber) &&
                Objects.equals(acquisitionDate, germplasm.acquisitionDate) &&
                Objects.equals(biologicalStatusOfAccessionCode, germplasm.biologicalStatusOfAccessionCode) &&
                Objects.equals(breedingMethodDbId, germplasm.breedingMethodDbId) &&
                Objects.equals(commonCropName, germplasm.commonCropName) &&
                Objects.equals(countryOfOriginCode, germplasm.countryOfOriginCode) &&
                Objects.equals(defaultDisplayName, germplasm.defaultDisplayName) &&
                Objects.equals(documentationURL, germplasm.documentationURL) &&
                Objects.equals(donors, germplasm.donors) &&
                Objects.equals(genus, germplasm.genus) &&
                Objects.equals(germplasmDbId, germplasm.germplasmDbId) &&
                Objects.equals(germplasmGenus, germplasm.germplasmGenus) &&
                Objects.equals(germplasmName, germplasm.germplasmName) &&
                Objects.equals(germplasmPUI, germplasm.germplasmPUI) &&
                Objects.equals(germplasmSpecies, germplasm.germplasmSpecies) &&
                Objects.equals(instituteCode, germplasm.instituteCode) &&
                Objects.equals(instituteName, germplasm.instituteName) &&
                Objects.equals(pedigree, germplasm.pedigree) &&
                Objects.equals(seedSource, germplasm.seedSource) &&
                Objects.equals(species, germplasm.species) &&
                Objects.equals(speciesAuthority, germplasm.speciesAuthority) &&
                Objects.equals(subtaxa, germplasm.subtaxa) &&
                Objects.equals(subtaxaAuthority, germplasm.subtaxaAuthority) &&
                Objects.equals(synonyms, germplasm.synonyms) &&
                Objects.equals(taxonIds, germplasm.taxonIds) &&
                Objects.equals(typeOfGermplasmStorageCode, germplasm.typeOfGermplasmStorageCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessionNumber, acquisitionDate, biologicalStatusOfAccessionCode, breedingMethodDbId, commonCropName, countryOfOriginCode, defaultDisplayName, documentationURL, donors, genus, germplasmDbId, germplasmGenus, germplasmName, germplasmPUI, germplasmSpecies, instituteCode, instituteName, pedigree, seedSource, species, speciesAuthority, subtaxa, subtaxaAuthority, synonyms, taxonIds, typeOfGermplasmStorageCode);
    }
}
