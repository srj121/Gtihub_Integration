package com.main.Gtihub_Integration.controller;

import com.main.Gtihub_Integration.repository.Repositoryrequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class GitHubOrganisationController {

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

    //____________________________________________________________get org repo___________________________________________________________________________________________________

    @GetMapping("/orgs/{org}/repos")
    public ResponseEntity<String> getOrgRepo(@PathVariable String org) {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = createHeadersEntity();
        ResponseEntity<String> result = restTemplate.exchange(gitUrl + "orgs/" + org + "/repos", HttpMethod.GET, entity, String.class);
        return result;

    }

    //____________________________________________________________create org repo___________________________________________________________________________________________________
    @PostMapping("/orgs/{org}/repos")
    public ResponseEntity<String> createOrgRepository(
            @PathVariable String org
    ) {
        String requestBody = createRequestBody();

        HttpHeaders headers = createTokenHeaders();
        // Create the request entity
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // Send the request to the GitHub API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(gitUrl + "orgs/" + org + "/repos", HttpMethod.POST, entity, String.class);
        return response;
    }
    //____________________________________________________________update org repo___________________________________________________________________________________________________

    @PostMapping("/repos/{owner}/{repo}")
    public ResponseEntity<String> updateOrgRepo(
            @PathVariable String owner,
            @PathVariable String repo
    ) {

        Repositoryrequest request = new Repositoryrequest();
        request.setName(repo);
        request.setDescription("description is changed first time");
        request.setHasIssues(true);
        request.setHasProjects(true);
        request.setHasWiki(true);
        request.setPrivate(false);
        request.setHomepage("https://github.com");
        // Perform validation or additional processing if needed

        // Prepare the request body
        String requestBody = "{\"name\":\"" + request.getName() + "\",\"description\":\"" +
                request.getDescription() + "\",\"homepage\":\"" + request.getHomepage() +
                "\",\"private\":" + request.isPrivate() + ",\"has_issues\":" + request.isHasIssues() +
                ",\"has_projects\":" + request.isHasProjects() + ",\"has_wiki\":" +
                request.isHasWiki() + "}";

        HttpHeaders headers = createTokenHeaders();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(gitUrl + "repos/" + owner + "/" + repo, HttpMethod.POST, entity, String.class);
        return response;
    }

    //____________________________________________________________delete org repo___________________________________________________________________________________________________

    @DeleteMapping("/repos/{owner}/{repo}")
    public ResponseEntity<String> deleteOrgRepo(@PathVariable String owner, @PathVariable String repo) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = createTokenHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(gitUrl + "repos/orgdummy1/Hello-raj", HttpMethod.DELETE, entity, Void.class);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    //____________________________________________________________get org repo languages___________________________________________________________________________________________________
    @GetMapping("/repos/{org}/{repo}/languages")
    public ResponseEntity<String> getOrgRepositoryLang(
            @PathVariable String org,
            @PathVariable String repo) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = createHeadersEntity();
        ResponseEntity<String> result = restTemplate.exchange(gitUrl + "repos/" + org + "/" + repo + "/languages", HttpMethod.GET, entity, String.class);
        return result;
    }

    //____________________________________________________________get org repo tags___________________________________________________________________________________________________
    @GetMapping("/repos/{org}/{repo}/tags")
    public ResponseEntity<String> getOrgRepositorytags(
            @PathVariable String org,
            @PathVariable String repo) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = createHeadersEntity();
        ResponseEntity<String> result = restTemplate.exchange(gitUrl + "repos/" + org + "/" + repo + "/tags", HttpMethod.GET, entity, String.class);
        return result;
    }

    //____________________________________________________________get org repo teams___________________________________________________________________________________________________
    @GetMapping("/repos/{org}/{repo}/teams")
    public ResponseEntity<String> getOrgRepositoryteams(
            @PathVariable String org,
            @PathVariable String repo) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = createTokenHeaders();

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(gitUrl + "repos/" + org + "/" + repo + "/teams", HttpMethod.GET, entity, String.class);
        return result;
    }

    //____________________________________________________________get org repo topics___________________________________________________________________________________________________
    @GetMapping("/repos/{org}/{repo}/topics")
    public ResponseEntity<String> getOrgRepositorytopics(
            @PathVariable String org,
            @PathVariable String repo) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = createTokenHeaders();

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(gitUrl + "repos/" + org + "/" + repo + "/topics", HttpMethod.GET, entity, String.class);
        return result;
    }
    //____________________________________________________________list of repository contributor___________________________________________________________________________________________________

    @GetMapping("/repos/{owner}/{repo}/contributors")
    public ResponseEntity<String> getContributor(
            @PathVariable String owner,
            @PathVariable String repo) {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = createHeadersEntity();
        ResponseEntity<String> result = restTemplate.exchange(gitUrl + "repos/" + owner + "/" + repo + "/contributors", HttpMethod.GET, entity, String.class);
        return result;

    }


}
