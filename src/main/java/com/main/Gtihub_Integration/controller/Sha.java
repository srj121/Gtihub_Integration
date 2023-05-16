package com.main.Gtihub_Integration.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.main.Gtihub_Integration.entity.Commit;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sha")
public class Sha {

    static String gitUrl = "https://api.github.com/";
    CommonMehtods commonMehtods = new CommonMehtods();

    //____________________________________________________________sha of a user ___________________________________________________________________________________________________

    @GetMapping("/userCommit/sha/{owner}/{repo}/{commit_sha}/{username}")
    public ResponseEntity<Commit> userSHA(
            @PathVariable String owner,
            @PathVariable String repo,
            @PathVariable String commit_sha,
            @PathVariable String username) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = commonMehtods.createTokenHeaders();

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        HttpEntity<Commit> response = restTemplate.exchange(
                gitUrl + "repos/" + owner + "/" + repo + "/commits/" + commit_sha + "?author=" + username,
                HttpMethod.GET, entity, Commit.class);
        System.out.println(response);

        return ResponseEntity.ok().body(response.getBody());

    }
    //____________________________________________________________get the SHA of the latest commit___________________________________________________________________________________________________

    @GetMapping("latest/commit/{owner}/{repo}")
    public ResponseEntity<Commit[]> latestSHA(
            @PathVariable String owner,
            @PathVariable String repo) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = commonMehtods.createTokenHeaders();

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        HttpEntity<Commit[]> response = restTemplate.exchange(
                gitUrl + "repos/" + owner + "/" + repo + "/commits?per_page=1&sort=created&direction=desc",
                HttpMethod.GET, entity, Commit[].class);
        Commit commitData = null;
        if (response.getBody() != null) {
            commitData = response.getBody()[0];
            commitData.getSha();

            System.out.println(commitData.getSha());
        } else {
            System.out.println("No commits found in the repository.");
        }
        return ResponseEntity.ok().body(response.getBody());

    }
}



