package org.planth_pheno_analytics.brapi.api.study.seasons;

import javax.validation.constraints.Size;

public class SeasonCriteria {

    @Size(max = 150, message = "seasonDbId value is too long")
    private final String seasonDbId;
    @Size(max = 150, message = "season value is too long")
    private final String season;
    @Size(max = 4, message = "year value is too long")
    private final String year;

    public SeasonCriteria(String seasonDbId, String season, String year) {
        this.seasonDbId = seasonDbId;
        this.season = season;
        this.year = year;
    }

    public String getSeasonDbId() {
        return seasonDbId;
    }

    public String getSeason() {
        return season;
    }

    public String getYear() {
        return year;
    }
}
