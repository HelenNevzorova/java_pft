package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by User on 21.04.2016.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
       click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactData(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedGroup() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void selectContactForEditing() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitEditing() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void createContact(ContactData contactData) {
        NavigationHelper getNavigationHelper = new NavigationHelper(wd);
        getNavigationHelper.gotoContactPage();
        fillContactData(contactData, true);
        submitContactCreation();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
