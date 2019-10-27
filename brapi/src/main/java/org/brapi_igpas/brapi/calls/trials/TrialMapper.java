package org.brapi_igpas.brapi.calls.trials;

import org.brapi_igpas.brapi.calls.studies.Study;
import org.brapi_igpas.brapi.calls.studies.StudyDAO;
import org.brapi_igpas.igpas.entity.InvestigationsEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrialMapper {
    private final StudyDAO studyDAO;
    private final StudyToTrialStudyMapper studyToTrialStudyMapper;
    private final TrialAdditionalInfoMapper trialAdditionalInfoMapper;

    public TrialMapper(StudyDAO studyDAO, StudyToTrialStudyMapper studyToTrialStudyMapper,
                       TrialAdditionalInfoMapper trialAdditionalInfoMapper) {
        this.studyDAO = studyDAO;
        this.studyToTrialStudyMapper = studyToTrialStudyMapper;
        this.trialAdditionalInfoMapper = trialAdditionalInfoMapper;
    }

    List<Trial> mapInvestigationsEntitiesToTrials(List<InvestigationsEntity> investigationsEntities) {
        List<Trial> trials = new ArrayList<>();
        for (InvestigationsEntity investigationsEntity : investigationsEntities) {
            Trial trial = mapInvestigationsEntityToTrial(investigationsEntity);
            trials.add(trial);
        }
        return trials;
    }

    Trial mapInvestigationsEntityToTrial(InvestigationsEntity investigationsEntity) {
        Trial trial = new Trial();
        trial.setAdditionalInfo(trialAdditionalInfoMapper.mapInvestigationEntityToTrialAdditionalInfo(investigationsEntity));

        trial.setTrialDbId(String.valueOf(investigationsEntity.getId()));
        trial.setTrialName(investigationsEntity.getTitle());
        trial.setDocumentationURL("http://cropnet.pl/plantphenodb/index.php?id=" + trial.getTrialDbId());

        List<Study> studiesWithTrialDbId = studyDAO.getStudiesWithTrialDbId(trial.getTrialDbId());
        trial.setStudies(studyToTrialStudyMapper.mapToTrialStudies(studiesWithTrialDbId));

        String commonCropName = studiesWithTrialDbId.get(0).getCommonCropName();
        trial.setCommonCropName(commonCropName);
        return trial;
    }
}
