package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().fillContactData(
                        new ContactData("Elena", "Nevzorova", "Earth", "elena.nevzorova@gmail.com", "test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
    }

}
