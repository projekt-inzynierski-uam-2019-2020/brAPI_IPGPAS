package org.brapi_igpas.brapi.calls.germplasm.germplasm;

import org.brapi_igpas.igpas.entity.Value;
import org.brapi_igpas.igpas.service.DbValuesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GermplasmDaoImpl implements GermplasmDao {
    private final DbValuesFacade dbValuesFacade;

    @Autowired
    public GermplasmDaoImpl(DbValuesFacade dbValuesFacade) {
        this.dbValuesFacade = dbValuesFacade;
    }

    public List<Germplasm> getAll() {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Infraspecific name");
        return getGermplasmFromValues(values);
    }

    private List<Germplasm> getGermplasmFromValues(List<Value> values) {
        List<Germplasm> germplasms = new ArrayList<>();
        for (Value value : values) {
            Germplasm germplasm = new Germplasm();
            germplasm.setGermplasmDbId(String.valueOf(value.getId()));
            if (value.getValue() != null) {
                germplasm.setAccessionNumber(value.getValue());
                germplasm.setDefaultDisplayName(value.getValue());
                germplasm.setGermplasmName(value.getValue());
            }
            germplasms.add(germplasm);
        }
        return germplasms;
    }
}
