package com.main.Gtihub_Integration.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class GitHubCommitsController {
//____________________________________________________________create Headers Entity Method___________________________________________________________________________________________________

    public HttpEntity<String> createHeadersEntity() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return entity;
    }
//____________________________________________________________List of commits ___________________________________________________________________________________________________

    @GetMapping("/repos/{owner}/{repo}/commits")
    public ResponseEntity<String> getListCommits(
            @PathVariable String owner,
            @PathVariable String repo) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity =  createHeadersEntity();

        ResponseEntity<String> result = restTemplate.exchange("https://api.github.com/repos/" + owner +"/"+ repo + "/commits", HttpMethod.GET, entity, String.class);
        return result;
    }

//    //____________________________________________________________List pull requests associated with a commit ___________________________________________________________________________________________________
//
//    @GetMapping("/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
//    public ResponseEntity<String> getListCommits(
//            @PathVariable String owner,
//            @PathVariable String repo) {
//
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<String> entity =  createHeadersEntity();
//        ResponseEntity<String> result = restTemplate.exchange("https://api.github.com/repos/" + owner +"/"+ repo + "/commits", HttpMethod.GET, entity, String.class);
//        return result;
//    }





}
