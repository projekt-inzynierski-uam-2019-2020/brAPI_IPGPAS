package org.brapi_igpas.brapi.calls.study.seasons;

public class Season {
    private String season;
    private String seasonDbId;
    private String year;

    public Season(){
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSeasonDbId() {
        return seasonDbId;
    }

    public void setSeasonDbId(String seasonDbId) {
        this.seasonDbId = seasonDbId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
