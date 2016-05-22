package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by User on 08.05.2016.
 */
public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().isEmpty()) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("Elena").withLastName("Nevzorova")
                    .withEmail("elena.nevzorova@gmail.com").withGroup("test1")
                    .withAddress("SUITE 5A-1204 799 E DRAGRAM TUCSON AZ 85705 USA"));
        }
    }

    @Test
    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
    }

    public static String cleaned(String address) {
        return address.replaceAll("\n", " ");
    }

}
