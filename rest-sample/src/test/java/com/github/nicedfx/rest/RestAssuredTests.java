package com.github.nicedfx.rest;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class RestAssuredTests extends TestBase{

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue()
                .withSubject("TestIssue")
                .withDescription("New test issue!");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();

        oldIssues.add(newIssue.withId(issueId));

        assertEquals(newIssues, oldIssues);
    }

    @Test
    public void testImportantBlockedBugify() throws IOException {
        skipIfNotFixed(1);

        assertTrue(true);
    }

    private Set<Issue> getIssues() throws IOException {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json").asString();

        JsonElement parsed = JsonParser.parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private int createIssue(Issue newIssue) throws IOException {
        String json = RestAssured
                .given().param("subject", newIssue.getSubject())
                .param("description", newIssue.getDescription())
                .post("https://bugify.stqa.ru/api/issues.json").asString();

        JsonElement parsed = JsonParser.parseString(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
