package com.spring.learningRest;

import com.spring.learningRest.model.RegistrationForm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationFormTest {

    @Test
    public void testEquals() {
        RegistrationForm form1 = new RegistrationForm();
        form1.setUsername("user1");
        form1.setPassword("password123");
        form1.setRepeatPassword("password123");

        RegistrationForm form2 = new RegistrationForm();
        form2.setUsername("user1");
        form2.setPassword("password123");
        form2.setRepeatPassword("password123");

        assertEquals(form1, form2);
    }

    @Test
    public void testHashCode() {
        RegistrationForm form = new RegistrationForm();
        form.setUsername("user1");
        form.setPassword("password123");
        form.setRepeatPassword("password123");

        int expectedHashCode = form.hashCode();
        assertEquals(expectedHashCode, form.hashCode());
    }

    @Test
    public void testToString() {
        RegistrationForm form = new RegistrationForm();
        form.setUsername("user1");
        form.setPassword("password123");
        form.setRepeatPassword("password123");

        String expectedToString = "RegistrationForm(username=user1, password=password123, repeatPassword=password123)";
        assertEquals(expectedToString, form.toString());
    }

    @Test
    public void testGetUsername() {
        RegistrationForm form = new RegistrationForm();
        form.setUsername("user1");
        assertEquals("user1", form.getUsername());
    }

    @Test
    public void testGetPassword() {
        RegistrationForm form = new RegistrationForm();
        form.setPassword("password123");
        assertEquals("password123", form.getPassword());
    }

    @Test
    public void testGetRepeatPassword() {
        RegistrationForm form = new RegistrationForm();
        form.setRepeatPassword("password123");
        assertEquals("password123", form.getRepeatPassword());
    }
}
