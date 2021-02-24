package com.restservice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GitHubRestController {

    @Autowired
    private GitHubClient gitHubClient;

    @GetMapping("/repositories/{owner}/{repositoryName}")
    public RepositoryDetails repositories(@PathVariable String owner, @PathVariable String repositoryName) {
        return gitHubClient.getRepoDetailsFromGitHub(owner, repositoryName);
    }
}