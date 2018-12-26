package com.github.udanton.demorecipebook.converters;

import com.github.udanton.demorecipebook.commands.NotesCommand;
import com.github.udanton.demorecipebook.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {
    private static final Long ID_VALUE = 1L;
    private static final String RECIPE_NOTES = "Notes";
    private NotesCommandToNotes notesConverter;

    @Before
    public void setUp() {
        notesConverter = new NotesCommandToNotes();
    }

    @Test
    public void testNullParameter() {
        assertNull(notesConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(notesConverter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = notesConverter.convert(notesCommand);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}