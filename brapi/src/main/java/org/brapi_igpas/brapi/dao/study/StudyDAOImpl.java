package org.brapi_igpas.brapi.dao.study;

import org.brapi_igpas.brapi.domain.calls.study.Study;
import org.brapi_igpas.brapi.service.AdditionalInfoMapper;
import org.brapi_igpas.igpas.entity.StudiesEntity;
import org.brapi_igpas.igpas.repository.StudiesRepository;
import org.brapi_igpas.igpas.service.mapper.study.StudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudyDAOImpl implements StudyDAO {
    private final StudyMapper studyMapper;
    private final StudiesRepository studiesRepository;
    private final AdditionalInfoMapper additionalInfoMapper;

    @Autowired
    public StudyDAOImpl(StudyMapper studyMapper, StudiesRepository studiesRepository, AdditionalInfoMapper additionalInfoMapper) {
        this.studyMapper = studyMapper;
        this.studiesRepository = studiesRepository;
        this.additionalInfoMapper = additionalInfoMapper;
    }

    @Override
    public List<Study> getAll() {
        List<Study> studies = studyMapper.mapToStudies((List<StudiesEntity>) studiesRepository.findAll());
        additionalInfoMapper.mapAdditionalInfo(studies);
        return studies;
    }
}
