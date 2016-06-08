package ru.stqa.pft.rest.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by User on 05.06.2016.
 */
public class RestTests extends TestBase {

    @Test(enabled = false)
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getAllIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getAllIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

    @Test
    public void formalTestWithFixedIssue() throws IOException {
        skipIfNotFixed(5);
        System.out.println("Issue is fixed so test is running");
        assertEquals(2 + 2, 4);
    }

    @Test
    public void formalTestWithOpenIssue() throws IOException {
        skipIfNotFixed(1);
        System.out.println("Issue is NOT fixed so test is NOT running");
    }


}
