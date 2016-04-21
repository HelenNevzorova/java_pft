package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        gotoContactPage();
        fillContactData(new ContactData("Elena", "Nevzorova", "Earth", "elena.nevzorova@gmail.com"));
        submitContactCreation();
        returnToHomePage();
    }

}
