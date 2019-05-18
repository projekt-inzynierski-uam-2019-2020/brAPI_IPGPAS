package org.brapi_igpas.brapi.calls.study.seasons;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;
import org.brapi_igpas.brapi.PaginationUtils;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.igpas.entity.Attribute;
import org.brapi_igpas.igpas.entity.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class SeasonDao {
    @PersistenceContext
    private EntityManager em;

    public BrApiDetailPayloadResponse getAll(String seasonDbId, String season, String year, int page, int pageSize) {
        int studyStartAttributeId = getStudyStartAttributeId();
        List<Value> values = getValuesWithStudyStartAttributeId(studyStartAttributeId);

        List<Season> seasons = getSeasonsFromValues(values);

        if (seasonDbId != null && !seasonDbId.isEmpty()) {
            seasons = getSeasonWithSeasonDbId(seasons, seasonDbId);
        }

        if (season != null && !season.isEmpty()) {
            seasons = getSeasonWithSeasonName(seasons, season);
        }

        if (year != null && !year.isEmpty()) {
            seasons = getSeasonWithYear(seasons, year);
        }

        Pagination paginationInfo = PaginationUtils.getPaginationInfo(seasons.size(), page, pageSize);

        int fromIndex = PaginationUtils.getFromIndex(seasons.size(), page, pageSize);
        int toIndex = PaginationUtils.getToIndex(seasons.size(), page, pageSize);

        seasons = seasons.subList(fromIndex, toIndex);

        return new BrApiDetailPayloadResponse(seasons, paginationInfo);
    }

    private int getStudyStartAttributeId() {
        TypedQuery<Attribute> studyStartAttributeQuery = em.createQuery("select a from Attribute a where a.displayedName = 'Study start'", Attribute.class);
        return studyStartAttributeQuery.getSingleResult().getId();
    }

    private List<Value> getValuesWithStudyStartAttributeId(int studyStartAttributeId) {
        TypedQuery<Value> valuesQuery = em.createQuery("select v from Value v where v.attributeId = :id", Value.class);
        valuesQuery.setParameter("id", studyStartAttributeId);

        return valuesQuery.getResultList();
    }

    private List<Season> getSeasonsFromValues(List<Value> values) {
        return values.stream().map(temp -> {
            Season season = new Season();
            season.setSeason("Season E");
            season.setYear(temp.getValue());
            season.setSeasonDbId(String.valueOf(temp.getId()));
            return season;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Season> getSeasonWithSeasonDbId(List<Season> seasons, String seasonDbId) {
        for (Season season : seasons) {
            if (season.getSeasonDbId().equals(seasonDbId)) {
                return new ArrayList<>(Collections.singletonList(season));
            }
        }
        return Collections.emptyList();
    }

    private List<Season> getSeasonWithSeasonName(List<Season> seasons, String seasonName) {
        List<Season> result = new ArrayList<>();
        for (Season season : seasons) {
            if (season.getSeason().equals(seasonName)) {
                result.add(season);
            }
        }
        return result;
    }

    private List<Season> getSeasonWithYear(List<Season> seasons, String year) {
        List<Season> result = new ArrayList<>();
        for (Season season : seasons) {
            if (season.getYear().equals(year)) {
                result.add(season);
            }
        }
        return result;
    }
}
