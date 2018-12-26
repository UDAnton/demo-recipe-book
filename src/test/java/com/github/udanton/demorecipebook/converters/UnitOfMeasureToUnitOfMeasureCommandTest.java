package com.github.udanton.demorecipebook.converters;

import com.github.udanton.demorecipebook.commands.UnitOfMeasureCommand;
import com.github.udanton.demorecipebook.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private static final String DESCRIPTION = "description";
    private static final Long ID_VALUE = 1L;

    private UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObjectConvert() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObj() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID_VALUE);
        uom.setDescription(DESCRIPTION);
        //when
        UnitOfMeasureCommand unit = converter.convert(uom);

        //then
        assertEquals(ID_VALUE, unit.getId());
        assertEquals(DESCRIPTION, unit.getDescription());
    }
}