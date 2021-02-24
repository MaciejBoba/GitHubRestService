package com.restservice;

import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@NoArgsConstructor 
public class GitHubClient {

    public RepositoryDetails getRepoDetailsFromGitHub(String owner, String repoName) {
        final String uri = ("https://api.github.com/repos/" + owner + "/" + repoName);
        RestTemplate restTemplate = new RestTemplate();
        try {
            String repositoryDescription = (restTemplate.getForObject(uri, String.class));
            return repositoryDetails(repositoryDescription);
        } catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
        }
        return null;
    }

    private RepositoryDetails repositoryDetails(String repositoryDetails) {
        JSONObject githubInfo = new JSONObject(repositoryDetails);
        return new RepositoryDetails(githubInfo.getString("full_name"),
                githubInfo.get("description").toString(),
                githubInfo.getString("clone_url"),
                githubInfo.getInt("stargazers_count"),
                githubInfo.getString("created_at"));
    }

}
