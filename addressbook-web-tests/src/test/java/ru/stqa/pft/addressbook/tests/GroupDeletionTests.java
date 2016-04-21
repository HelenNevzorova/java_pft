package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void GroupDeletionTest() {
        app.gotoGroupPage();
        app.SelectGroup();
        app.DeleteSelectedGroups();
        app.returnToGroupPage();
    }
}
