package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by User on 28.05.2016.
 */
public class RegistrationTests extends TestBase {

    @Test
    public void testRegistration() {
        app.registration().start("user1", "user1@localhost.localdomain");
    }
}
