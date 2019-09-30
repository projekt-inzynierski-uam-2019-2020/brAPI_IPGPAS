package org.brapi_igpas.brapi.calls.study.seasons;

import java.util.Objects;

public class Season {
    private String season;
    private String seasonDbId;
    private String year;

    public Season() {
    }

    public Season(String season, String seasonDbId, String year) {
        this.season = season;
        this.seasonDbId = seasonDbId;
        this.year = year;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Season season1 = (Season) o;
        return Objects.equals(season, season1.season) &&
                Objects.equals(seasonDbId, season1.seasonDbId) &&
                Objects.equals(year, season1.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(season, seasonDbId, year);
    }
}
