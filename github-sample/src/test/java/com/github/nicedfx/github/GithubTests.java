package com.github.nicedfx.github;

import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Collections;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("git personal access token here");
        Repo commits = github.repos().get(new Coordinates.Simple("nicedfx", "jft_0"));

        for (RepoCommit commit: commits.commits().iterate(Collections.emptyMap()))
            System.out.println(new RepoCommit.Smart(commit).message());
    }
}
