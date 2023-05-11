package com.main.Gtihub_Integration.controller;

import com.main.Gtihub_Integration.repository.Repositoryrequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class GitHubCommitsController {

    static String gitUrl = "https://api.github.com/";

//____________________________________________________________create Headers Entity Method___________________________________________________________________________________________________

    public HttpEntity<String> createHeadersEntity() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return entity;
    }
    //____________________________________________________________create token Headers Entity Method___________________________________________________________________________________________________

    public HttpHeaders createTokenHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer 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");
        headers.set("Accept", "application/vnd.github+json");
        headers.set("X-GitHub-Api-Version", "2022-11-28");

        return headers;
    }
    //____________________________________________________________create  Request Body Method___________________________________________________________________________________________________

    public String createRequestBody() {
        Repositoryrequest request = new Repositoryrequest();
        request.setName("first 12");
        request.setDescription("deumy");
        request.setHasIssues(true);
        request.setHasProjects(true);
        request.setHasWiki(true);
        request.setPrivate(false);
        request.setHomepage("https://github.com");
        // Prepare the request body
        String requestBody = "{\"name\":\"" + request.getName() + "\",\"description\":\"" +
                request.getDescription() + "\",\"homepage\":\"" + request.getHomepage() +
                "\",\"private\":" + request.isPrivate() + ",\"has_issues\":" + request.isHasIssues() +
                ",\"has_projects\":" + request.isHasProjects() + ",\"has_wiki\":" +
                request.isHasWiki() + "}";
        return requestBody;
    }


//____________________________________________________________List of commits ___________________________________________________________________________________________________

    @GetMapping("/repos/{owner}/{repo}/commits")
    public ResponseEntity<String> getListCommits(
            @PathVariable String owner,
            @PathVariable String repo) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity =  createHeadersEntity();

        ResponseEntity<String> result = restTemplate.exchange(gitUrl + "repos/" + owner +"/"+ repo + "/commits", HttpMethod.GET, entity, String.class);
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
//        ResponseEntity<String> result = restTemplate.exchange(gitUrl + "repos/" + owner +"/"+ repo + "/commits", HttpMethod.GET, entity, String.class);
//        return result;
//    }
//    //____________________________________________________________List branches for HEAD commit___________________________________________________________________________________________________


    @GetMapping("/repos/{owner}/{repo}/commits/{commit_sha}/branches-where-head")
    public ResponseEntity<String> getBranchesHeadCommits(
            @PathVariable String owner,
            @PathVariable String repo,
            @PathVariable String commit_sha) {

        HttpHeaders headers = createTokenHeaders();
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);
        ResponseEntity<String> result = restTemplate.exchange(
                gitUrl + "repos/" + owner + "/" + repo +"/commits/" + commit_sha + "/branches-where-head",
                HttpMethod.GET, entity,  String.class);
        return result;

    }
    //____________________________________________________________List pull requests associated with a commit ___________________________________________________________________________________________________

    @GetMapping("/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public ResponseEntity<String> pullRequestCommit(
            @PathVariable String owner,
            @PathVariable String repo,
            @PathVariable String commit_sha) {

        RestTemplate restTemplate  = new RestTemplate();
        HttpHeaders headers = createTokenHeaders();

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(
                gitUrl + "repos/" + owner + "/" + repo +"/commits/" + commit_sha + "/pulls",
                HttpMethod.GET, entity,  String.class);
        return result;

    }
    //____________________________________________________________Get a commit___________________________________________________________________________________________________

    @GetMapping("/repos/{owner}/{repo}/commits/{ref}")
    public ResponseEntity<String> getCommit(
            @PathVariable String owner,
            @PathVariable String repo,
            @PathVariable String ref) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = createTokenHeaders();
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(
                gitUrl + "repos/"+ owner +"/"+ repo +"/commits/"+ ref,
                HttpMethod.GET, entity,  String.class);
        return result;
    }
    //____________________________________________________________Compare two commits___________________________________________________________________________________________________

    @GetMapping("/repos/{owner}/{repo}/compare/{basehead}")
    public ResponseEntity<String> compareTwoCommit(
            @PathVariable String owner,
            @PathVariable String repo,
            @PathVariable String basehead) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = createTokenHeaders();
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(
                gitUrl + "repos/"+ owner +"/"+ repo +"/compare/"+ basehead,
                HttpMethod.GET, entity,  String.class);
        return result;
    }
    //**********************************prnding*********************************



}
