package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void GroupDeletionTest() {
        gotoGroupPage();
        SelectGroup();
        DeleteSelectedGroups();
        returnToGroupPage();
    }
}
