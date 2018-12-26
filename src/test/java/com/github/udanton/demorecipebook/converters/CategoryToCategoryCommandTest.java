package com.github.udanton.demorecipebook.converters;

import com.github.udanton.demorecipebook.commands.CategoryCommand;
import com.github.udanton.demorecipebook.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {
    private static final String DESCRIPTION = "description";
    private static final Long ID_VALUE = 1L;
    private CategoryToCategoryCommand categoryConverter;

    @Before
    public void setUp() {
        categoryConverter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(categoryConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(categoryConverter.convert(new Category()));
    }

    @Test
    public void convert() {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = categoryConverter.convert(category);

        //then
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}