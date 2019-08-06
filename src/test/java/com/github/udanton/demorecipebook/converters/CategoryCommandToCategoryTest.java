package com.github.udanton.demorecipebook.converters;

import com.github.udanton.demorecipebook.commands.CategoryCommand;
import com.github.udanton.demorecipebook.domain.Category;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryCommandToCategoryTest {

    private static final String DESCRIPTION = "description";
    private static final String ID_VALUE = UUID.randomUUID().toString();

    private CategoryCommandToCategory categoryConverter;

    @Before
    public void setUp() {
        categoryConverter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() {
        assertNull(categoryConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(categoryConverter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = categoryConverter.convert(categoryCommand);

        //then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}