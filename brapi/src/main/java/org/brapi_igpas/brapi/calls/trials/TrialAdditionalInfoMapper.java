package org.brapi_igpas.brapi.calls.trials;

import org.brapi_igpas.igpas.entity.InvestigationsEntity;
import org.springframework.stereotype.Service;

@Service
public class TrialAdditionalInfoMapper {

    TrialAdditionalInfo mapInvestigationEntityToTrialAdditionalInfo(InvestigationsEntity investigationsEntity) {
        Object additionalInfo = new TrialAdditionalInfo();
        ((TrialAdditionalInfo) additionalInfo).setDescription(investigationsEntity.getDescription());
        ((TrialAdditionalInfo) additionalInfo).setFirstContactName(investigationsEntity.getFirstContactName());
        ((TrialAdditionalInfo) additionalInfo).setSubmissionDate(String.valueOf(investigationsEntity.getSubmissionDate()));
        return (TrialAdditionalInfo) additionalInfo;
    }
}
