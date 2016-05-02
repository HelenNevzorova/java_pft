package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().createContact(
                new ContactData("Elena", "Nevzorova", "Earth", "elena.nevzorova@gmail.com", "test1"));
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}
