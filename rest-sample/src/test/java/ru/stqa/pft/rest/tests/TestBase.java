package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

/**
 * Created by User on 08.06.2016.
 */
public class TestBase {

    public boolean isIssueOpen(int issueId) throws IOException {
        boolean isIssueOpen = true;
        Set<Issue> issueSet = getAllIssues();
        for (Issue issue : issueSet) {
            if (issue.getId() == issueId) {
                if (issue.getStateName().equals("Open") || issue.getStateName().equals("In Progress")) {
                    isIssueOpen = false;
                    break;
                }
            }
            isIssueOpen = true;
        }
        return isIssueOpen;
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }


    public Set<Issue> getAllIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> result = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
        return result;
    }


    public Executor getExecutor() {
        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

}
