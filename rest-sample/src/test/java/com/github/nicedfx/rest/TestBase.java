package com.github.nicedfx.rest;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.Set;

public class TestBase {

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    private Issue getIssue(int issueId) throws IOException {
        String json = RestAssured.get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId)).asString();

        JsonElement parsed = JsonParser.parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issuesSet = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
        issuesSet.iterator().next();
        return issuesSet.iterator().next();
    }


    boolean isIssueOpen(int issueId) throws IOException {

        return !getIssue(issueId).getState_name().equals("Closed");
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
