package com.main.Gtihub_Integration.controller;

import com.main.Gtihub_Integration.repository.Repositoryrequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
@RestController
public class GitHubUserController {

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
//____________________________________________________________get user___________________________________________________________________________________________________
    @GetMapping("/users/{userName}")
    public ResponseEntity<String> getUserInfo(@PathVariable String userName ) {
        RestTemplate restTemplate = new RestTemplate();
       HttpEntity<String> entity = createHeadersEntity();
        ResponseEntity<String> result = restTemplate.exchange(gitUrl + "users/" + userName, HttpMethod.GET, entity, String.class);
        return result;
    }
    //____________________________________________________________repositories of Authenticated user___________________________________________________________________________________________________
    @GetMapping("/user/repos")
    public ResponseEntity<String> getRepoOfAuthenticatedUser() {
        RestTemplate restTemplate = new RestTemplate();

       HttpHeaders headers = createTokenHeaders();
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(gitUrl + "user/repos", HttpMethod.GET, entity, String.class);
        return result;
    }
//____________________________________________________________get user repo___________________________________________________________________________________________________
    @GetMapping("/users/{userName}/repos")
    public ResponseEntity<String> getUserRepository(@PathVariable String userName) {
        RestTemplate restTemplate = new RestTemplate();
      HttpEntity<String> entity = createHeadersEntity();
        ResponseEntity<String> result = restTemplate.exchange(gitUrl + "users/" + userName + "/repos" , HttpMethod.GET, entity, String.class);
        return result;
    }
    //____________________________________________________________create repo for Authenticated user___________________________________________________________________________________________________
    @PostMapping("/user/repos")
    public ResponseEntity<String> createAtuhUserRepository() {

        String requestBody = createRequestBody();
       HttpHeaders headers = createTokenHeaders();

        // Create the request entity
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // Send the request to the GitHub API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(gitUrl + "user/repos",HttpMethod.POST, entity, String.class);
        return response;
    }
}


