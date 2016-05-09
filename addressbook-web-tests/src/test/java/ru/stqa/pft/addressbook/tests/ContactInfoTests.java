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
public class ContactInfoTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().isEmpty()) {
            app.contact().create(new ContactData().withFirstName("Elena").withLastName("Nevzorova")
                    .withAddress("SUITE 5A-1204 799 E DRAGRAM TUCSON AZ 85705 USA")
                    .withEmail("elena.nevzorova@mail.com").withEmail2("234@mail.com").withEmail3("345@mail.com")
                    .withHomePhone("(123)").withMobilePhone("22-22").withWorkPhone("33 33").withGroup("test1"));
        }
    }

    @Test
    public void testContactInfo() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData detailedContactInfo = app.contact().detailedInfo(contact);

        String group = detailedContactInfo.getGroup();
        String contactFromEditPage = mergeInfoFromEditPage(contactInfoFromEditForm);
        String detailedContactPage = cleanDetailedInfo(detailedContactInfo.getFullInfo(), group);

        assertThat(detailedContactPage, equalTo(contactFromEditPage));
    }

    private String mergeInfoFromEditPage(ContactData contact) {
        return Arrays.asList(contact.getFirstName(), contact.getLastName(), contact.getAddress(),
                contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining(" "));
    }

    private static String cleanDetailedInfo(String contactInfo, String group) {
        return contactInfo.replaceAll("(H: |M: |W: )|( \\(www.mail.com\\))", " ")
                .replaceAll("(\\s+)", " ").replaceAll(group, "").trim();
    }
}
