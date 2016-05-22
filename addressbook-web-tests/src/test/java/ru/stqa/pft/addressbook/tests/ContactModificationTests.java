package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by User on 21.04.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("Elena").withLastName("Nevzorova").withAddress("Earth")
                    .withEmail("elena@gmail.com").withEmail2("elena2@gmail.com").withEmail3("elena3@gmail.com")
                    .withHomePhone("(123)").withMobilePhone("22-22").withWorkPhone("33 33").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("ElenaNew")
                .withLastName("Nevzorova").withAddress("Earth").withEmail("elena@gmail.com")
                .withEmail2("elena2@gmail.com").withEmail3("elena3@gmail.com").withHomePhone("(123)")
                .withMobilePhone("22-22").withWorkPhone("33 33");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }
}
