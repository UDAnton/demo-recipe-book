package com.github.udanton.demorecipebook.converters;

import com.github.udanton.demorecipebook.commands.NotesCommand;
import com.github.udanton.demorecipebook.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private static final String ID_VALUE = UUID.randomUUID().toString();
    private static final String RECIPE_NOTES = "Notes";
    private NotesToNotesCommand notesConverter;

    @Before
    public void setUp() {
        notesConverter = new NotesToNotesCommand();
    }

    @Test
    public void testNull() {
        assertNull(notesConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(notesConverter.convert(new Notes()));
    }

    @Test
    public void convert() {
        //given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand notesCommand = notesConverter.convert(notes);

        //then
        assertEquals(ID_VALUE, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }
}