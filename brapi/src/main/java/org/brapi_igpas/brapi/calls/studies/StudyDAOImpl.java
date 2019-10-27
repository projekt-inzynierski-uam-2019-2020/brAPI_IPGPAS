package org.brapi_igpas.brapi.calls.studies;

import org.brapi_igpas.igpas.entity.StudiesEntity;
import org.brapi_igpas.igpas.repository.StudiesEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudyDAOImpl implements StudyDAO {
    private final StudiesEntityRepository studiesEntityRepository;
    private final StudyMapper studyMapper;

    @Autowired
    public StudyDAOImpl(StudiesEntityRepository studiesEntityRepository, StudyMapper studyMapper) {
        this.studiesEntityRepository = studiesEntityRepository;
        this.studyMapper = studyMapper;
    }

    @Override
    public List<Study> getStudiesWithTrialDbId(String trialDbId) {
        List<Study> studies = getAll();
        return studies.stream().filter(study -> study.getTrialDbId().equals(trialDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Study> getAll() {
        List<StudiesEntity> studiesEntities = (List<StudiesEntity>) studiesEntityRepository.findAll();
        return studyMapper.mapStudiesEntitiesToStudies(studiesEntities);
    }
}
