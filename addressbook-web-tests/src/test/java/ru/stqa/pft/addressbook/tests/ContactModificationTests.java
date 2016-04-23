package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by User on 21.04.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(
                    new ContactData("Elena", "Nevzorova", "Earth", "elena.nevzorova@gmail.com", "test1"), true);
        }
        app.getContactHelper().selectContactForEditing();
        app.getContactHelper().fillContactData(
                new ContactData("Elena", "Nevzorova", "Earth", "elena.nevzorova@gmail.com", null), false);
        app.getContactHelper().submitEditing();
        app.getContactHelper().returnToHomePage();
    }
}
