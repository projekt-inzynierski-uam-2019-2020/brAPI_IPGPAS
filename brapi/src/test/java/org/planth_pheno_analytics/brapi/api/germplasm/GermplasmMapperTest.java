package org.planth_pheno_analytics.brapi.api.germplasm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.planth_pheno_analytics.data.entity.ValueEntity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GermplasmMapperTest {

    private GermplasmMapper germplasmMapper;

    @Before
    public void init(){
        germplasmMapper = new GermplasmMapper();
    }

    @Test
    public void mapToGermplasmShouldMapValuesFromGivenGermplasmProjection(){
        // given
        GermplasmProjection germplasmProjection = mock(GermplasmProjection.class);
        when(germplasmProjection.getGermplasmDbId()).thenReturn("1");
        when(germplasmProjection.getAccessionNumber()).thenReturn("2");
        when(germplasmProjection.getDefaultDisplayName()).thenReturn("G00:03");
        when(germplasmProjection.getGermplasmName()).thenReturn("Germplasm1");

        // when
        Germplasm germplasm = germplasmMapper.mapToGermplasm(germplasmProjection);

        // then
        Assert.assertEquals("1", germplasm.getGermplasmDbId());
        Assert.assertEquals("2", germplasm.getAccessionNumber());
        Assert.assertEquals("G00:03", germplasm.getDefaultDisplayName());
        Assert.assertEquals("Germplasm1", germplasm.getGermplasmName());
    }

    @Test
    public void mapToGermplasmShouldMapValuesFromGivenValueEntity(){
        // given
        ValueEntity valueEntity = new ValueEntity();
        valueEntity.setValue("Germplasm1");

        // when
        Germplasm germplasm = germplasmMapper.mapToGermplasm(valueEntity);

        // then
        Assert.assertEquals("Germplasm1", germplasm.getGermplasmDbId());
        Assert.assertEquals("Germplasm1", germplasm.getAccessionNumber());
        Assert.assertEquals("Germplasm1", germplasm.getDefaultDisplayName());
        Assert.assertEquals("Germplasm1", germplasm.getGermplasmName());
    }
}
