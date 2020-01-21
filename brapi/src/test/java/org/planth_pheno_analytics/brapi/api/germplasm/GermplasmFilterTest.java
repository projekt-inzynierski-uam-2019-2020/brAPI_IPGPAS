package org.planth_pheno_analytics.brapi.api.germplasm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

public class GermplasmFilterTest {

    private GermplasmFilter germplasmFilter;

    @Before
    public void init() {
        germplasmFilter = new GermplasmFilter();
    }

    @Test
    public void filterByGermplasmPUIShouldFilterAllGermplasmWithGivenGermplasmPUIFromGivenStream() {
        // given
        String germplasmPUI = "http://pui.per/accession/A000001";
        Stream<Germplasm> dataStream = createDataStream();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByGermplasmPUI(dataStream, germplasmPUI);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(1, filteredStream.count());
    }

    @Test
    public void filterByGermplasmPUIShouldReturnEmptyStreamWhenGivenGermplasmPUIIsNotPresentInGivenStream() {
        // given
        String germplasmPUI = "http://pui.per/accession/B000001";
        Stream<Germplasm> dataStream = createDataStream();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByGermplasmPUI(dataStream, germplasmPUI);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByGermplasmPUIShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String germplasmPUI = "http://pui.per/accession/A000001";
        Stream<Germplasm> dataStream = Stream.empty();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByGermplasmPUI(dataStream, germplasmPUI);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByGermplasmPUIShouldReturnEmptyStreamWhenGivenGermplasmPUIIsNull() {
        // given
        Stream<Germplasm> dataStream = createDataStream();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByGermplasmPUI(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByGermplasmDbIdShouldFilterAllGermplasmWithGivenGermplasmDbIdFromGivenStream() {
        // given
        String germplasmDbId = "1";
        Stream<Germplasm> dataStream = createDataStream();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByGermplasmDbId(dataStream, germplasmDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(1, filteredStream.count());
    }

    @Test
    public void filterByGermplasmDbIdShouldReturnEmptyStreamWhenGivenGermplasmDbIdIsNotPresentInGivenStream() {
        // given
        String germplasmDbId = "3141";
        Stream<Germplasm> dataStream = createDataStream();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByGermplasmDbId(dataStream, germplasmDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByGermplasmDbIdShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String germplasmDbId = "1";
        Stream<Germplasm> dataStream = Stream.empty();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByGermplasmDbId(dataStream, germplasmDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByGermplasmDbIdShouldReturnEmptyStreamWhenGivenGermplasmDbIdIsNull() {
        // given
        Stream<Germplasm> dataStream = createDataStream();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByGermplasmDbId(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByGermplasmNameShouldFilterAllGermplasmWithGivenGermplasmNameFromGivenStream() {
        // given
        String germplasmName = "G00:0012";
        Stream<Germplasm> dataStream = createDataStream();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByGermplasmName(dataStream, germplasmName);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(2, filteredStream.count());
    }

    @Test
    public void filterByGermplasmNameShouldReturnEmptyStreamWhenGivenGermplasmNameIsNotPresentInGivenStream() {
        // given
        String germplasmName = "G31:212";
        Stream<Germplasm> dataStream = createDataStream();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByGermplasmName(dataStream, germplasmName);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByGermplasmNameShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String germplasnName = "G00:0012";
        Stream<Germplasm> dataStream = Stream.empty();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByGermplasmName(dataStream, germplasnName);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByGermplasmNameShouldReturnEmptyStreamWhenGivenGermplasmNameIsNull() {
        // given
        Stream<Germplasm> dataStream = createDataStream();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByGermplasmName(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByCommonCropNameShouldFilterAllGermplasmWithGivenCommonCropNameFromGivenStream() {
        // given
        String commonCropName = "Hordeum Vulgare";
        Stream<Germplasm> dataStream = createDataStream();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByCommonCropName(dataStream, commonCropName);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(5, filteredStream.count());
    }

    @Test
    public void filterByCommonCropNameShouldReturnEmptyStreamWhenGivenCommonCropNameIsNotPresentInGivenStream() {
        // given
        String commonCropName = "Tomatillo";
        Stream<Germplasm> dataStream = createDataStream();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByCommonCropName(dataStream, commonCropName);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByCommonCropNameShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String commonCropName = "Hordeum Vulgare";
        Stream<Germplasm> dataStream = Stream.empty();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByCommonCropName(dataStream, commonCropName);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByCommonCropNameShouldReturnEmptyStreamWhenGivenCommonCropNameIsNull() {
        // given
        Stream<Germplasm> dataStream = createDataStream();

        // when
        Stream<Germplasm> filteredStream = germplasmFilter.filterByCommonCropName(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    private Stream<Germplasm> createDataStream() {
        return Stream.of(
                createGermplasm("http://pui.per/accession/A000001", "1", null, "Hordeum Vulgare"),
                createGermplasm("http://pui.per/accession/A000006", "6", "G00:0012", "Rye"),
                createGermplasm("http://pui.per/accession/A000007", "7", "G01:0124", "Rye"),
                createGermplasm("http://pui.per/accession/A000002", null, "G00:0012", "Hordeum Vulgare"),
                createGermplasm("http://pui.per/accession/A000005", "5", "G12:121", "Hordeum Vulgare"),
                createGermplasm("http://pui.per/accession/A000003", "3", "G51:0122", "Hordeum Vulgare"),
                createGermplasm("http://pui.per/accession/A0000251", "251", "G:001", null),
                createGermplasm("http://pui.per/accession/A0000531", "531", "G01:12", "Rye"),
                createGermplasm("http://pui.per/accession/A0000212", "212", "G012:21", "Rye"),
                createGermplasm(null, "98", "G02:211", "Hordeum Vulgare"));
    }

    // tests data set
    private Germplasm createGermplasm(String germplasmPUI, String germplasmDbId, String germplasmName, String commonCropName) {
        Germplasm germplasm = new Germplasm();
        germplasm.setGermplasmPUI(germplasmPUI);
        germplasm.setGermplasmDbId(germplasmDbId);
        germplasm.setGermplasmName(germplasmName);
        germplasm.setCommonCropName(commonCropName);
        return germplasm;
    }
}
