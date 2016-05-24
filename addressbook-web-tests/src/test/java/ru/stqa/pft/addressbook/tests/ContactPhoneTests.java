package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by User on 08.05.2016.
 */
public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().isEmpty()) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("Elena").withLastName("Nevzorova")
                    .withEmail("elena@gmail.com").withEmail2("elena2@gmail.com").withEmail3("elena3@gmail.com")
                    .withHomePhone("(123)").withMobilePhone("22-22").withWorkPhone("33 33").withGroup("test1")
                    .withAddress("SUITE 5A-1204 799 E DRAGRAM TUCSON AZ 85705 USA"));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
       return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
               .stream().filter((s) -> ! s.equals(""))
               .map(ContactPhoneTests:: cleaned)
               .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
