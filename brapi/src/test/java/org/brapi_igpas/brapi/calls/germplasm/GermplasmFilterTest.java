package org.brapi_igpas.brapi.calls.germplasm;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class GermplasmFilterTest {

    private List<Germplasm> germplasms;
    private GermplasmFilter germplasmFilter;

    @Before
    public void init() {
        germplasms = new ArrayList<>();
        germplasmFilter = new GermplasmFilter();
    }

    @Test
    public void filterByGermplasmPUIShouldReturnFilteredListByGivenPUIWhenExists() {
        germplasms.addAll(createNGermplasmsWithGermplasmPUI(8, "PUI1"));
        germplasms.addAll(createNGermplasmsWithGermplasmPUI(10, "PUI5"));

        germplasms = germplasmFilter.filterByGermplasmPUI(germplasms, "PUI5");

        assertNotNull(germplasms);
        assertEquals(10, germplasms.size());
    }

    @Test
    public void filterByGermplasmPUIShouldReturnEmptyListWhenPUIDoesNotExist() {
        germplasms.addAll(createNGermplasmsWithGermplasmPUI(2, "PUI1"));
        germplasms.addAll(createNGermplasmsWithGermplasmPUI(2, "PUI5"));

        germplasms = germplasmFilter.filterByGermplasmPUI(germplasms, "PUI7");

        assertNotNull(germplasms);
        assertTrue(germplasms.isEmpty());
    }

    @Test
    public void filterByGermplasmDbIdShouldReturnFilteredListByGermplasmDbIdWhenExists() {
        germplasms.addAll(createNGermplasmsWithGermplasmDbId(1, "3513"));
        germplasms.addAll(createNGermplasmsWithGermplasmDbId(1, "1251"));

        germplasms = germplasmFilter.filterByGermplasmDbId(germplasms, "3513");

        assertNotNull(germplasms);
        assertEquals(1, germplasms.size());
    }

    @Test
    public void filterByGermplasmDbIdShouldReturnEmptyListWhenGermplasmDbIdDoesNotExist() {
        germplasms.addAll(createNGermplasmsWithGermplasmDbId(1, "12312"));
        germplasms.addAll(createNGermplasmsWithGermplasmDbId(2, "15212"));

        germplasms = germplasmFilter.filterByGermplasmDbId(germplasms, "5312");

        assertNotNull(germplasms);
        assertTrue(germplasms.isEmpty());
    }

    @Test
    public void filterByGermplasmNameShouldReturnFilteredListByGermplasmNameWhenExists() {
        germplasms.addAll(createNGermplasmsWithGermplasmName(4, "Germplasm1"));
        germplasms.addAll(createNGermplasmsWithGermplasmName(6, "Germplasm3"));

        germplasms = germplasmFilter.filterByGermplasmName(germplasms, "Germplasm3");

        assertNotNull(germplasms);
        assertEquals(6, germplasms.size());
    }

    @Test
    public void filterByGermplasmNameShouldReturnEmptyListWhenGermplasmNameDoesNotExist() {
        germplasms.addAll(createNGermplasmsWithGermplasmName(5, "Germplasm1"));
        germplasms.addAll(createNGermplasmsWithGermplasmName(3, "Germplasm3"));

        germplasms = germplasmFilter.filterByCommonCropName(germplasms, "Germplasm2");

        assertNotNull(germplasms);
        assertTrue(germplasms.isEmpty());
    }

    @Test
    public void filterByCommonCropNameShouldReturnFilteredListByGermplasmCommonCropNameWhenExists() {
        germplasms.addAll(createNGermplasmsWithCommonCropName(4, "Tomatillo"));
        germplasms.addAll(createNGermplasmsWithCommonCropName(6, "Hordeum Vulgare"));

        germplasms = germplasmFilter.filterByCommonCropName(germplasms, "Tomatillo");

        assertNotNull(germplasms);
        assertEquals(4, germplasms.size());
    }

    @Test
    public void filterByCommonCropNameShouldReturnEmptyListWhenGermplasmCommonCropNameDoesNotExist() {
        germplasms.addAll(createNGermplasmsWithCommonCropName(5, "Tomatillo"));
        germplasms.addAll(createNGermplasmsWithCommonCropName(3, "Hordeum Vulgare"));

        germplasms = germplasmFilter.filterByCommonCropName(germplasms, "Rye");

        assertNotNull(germplasms);
        assertTrue(germplasms.isEmpty());
    }

    private List<Germplasm> createNGermplasmsWithGermplasmPUI(int n, String germplasmPUI) {
        return Collections.nCopies(n, createGermplasmWithGermplasmPUI(germplasmPUI));
    }

    private Germplasm createGermplasmWithGermplasmPUI(String germplasmPUI) {
        Germplasm germplasm = new Germplasm();
        germplasm.setGermplasmPUI(germplasmPUI);
        return germplasm;
    }

    private List<Germplasm> createNGermplasmsWithGermplasmDbId(int n, String germplasmDbId) {
        return Collections.nCopies(n, createGermplasmWithGermplasmDbId(germplasmDbId));
    }

    private Germplasm createGermplasmWithGermplasmDbId(String germplasmDbId) {
        Germplasm germplasm = new Germplasm();
        germplasm.setGermplasmDbId(germplasmDbId);
        return germplasm;
    }

    private List<Germplasm> createNGermplasmsWithGermplasmName(int n, String germplasmName) {
        return Collections.nCopies(n, createGermplasmWithGermplasmName(germplasmName));
    }

    private Germplasm createGermplasmWithGermplasmName(String germplasmName) {
        Germplasm germplasm = new Germplasm();
        germplasm.setGermplasmName(germplasmName);
        return germplasm;
    }

    private List<Germplasm> createNGermplasmsWithCommonCropName(int n, String commonCropName) {
        return Collections.nCopies(n, createGermplasmWithCommonCropName(commonCropName));
    }

    private Germplasm createGermplasmWithCommonCropName(String germplasmName) {
        Germplasm germplasm = new Germplasm();
        germplasm.setCommonCropName(germplasmName);
        return germplasm;
    }
}
