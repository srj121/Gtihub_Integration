package com.main.Gtihub_Integration.controller;

import com.main.Gtihub_Integration.entity.Branch;
import com.main.Gtihub_Integration.repository.Repositoryrequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class GitHubBranchController {

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

    public String createRequestBodyNewName() {
        Branch branch = new Branch();
      branch.setnew_Name("RajasekharNewBranch");
        // Prepare the request body
        String requestBody = "{\"new_name\":\"" + branch.getnew_Name() + "\"}";
        return requestBody;
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



    //____________________________________________________________List branches___________________________________________________________________________________________________

    @GetMapping("/repos/{owner}/{repo}/branches")
    public ResponseEntity<String> getListBranches(
            @PathVariable String owner,
            @PathVariable String repo) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity =  createHeadersEntity();

        ResponseEntity<String> result = restTemplate.exchange(gitUrl + "repos/" + owner +"/"+ repo + "/branches", HttpMethod.GET, entity, String.class);
        return result;
    }

    //____________________________________________________________find particular branch___________________________________________________________________________________________________

    @GetMapping("/repos/{owner}/{repo}/branches/{branch}")
    public ResponseEntity<String> getBranch(
            @PathVariable String owner,
            @PathVariable String repo,
            @PathVariable String branch) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity =  createHeadersEntity();

        ResponseEntity<String> result = restTemplate.exchange(gitUrl + "repos/" + owner +"/"+ repo + "/branches/"+ branch, HttpMethod.GET, entity, String.class);
        return result;
    }

    //____________________________________________________________rename particular branch___________________________________________________________________________________________________

    @PostMapping("/repos/{owner}/{repo}/branches/{branch}/rename")
    public ResponseEntity<String> RenameBranch(
            @PathVariable String owner,
            @PathVariable String repo,
            @PathVariable String branch) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = createTokenHeaders();
        String resposeBody = createRequestBodyNewName();
        HttpEntity<String> entity =  new HttpEntity<>(resposeBody, headers);


        ResponseEntity<String> result = restTemplate.exchange(gitUrl + "repos/" + owner +"/"+ repo + "/branches/"+ branch + "/rename", HttpMethod.POST, entity, String.class);
        return result;
    }

}
//____________________________________________________________Merge a branch___________________________________________________________________________________________________

//    @PostMapping("/repos/{owner}/{repo}/merges")
//    public ResponseEntity<String> mergeBranch(
//            @PathVariable String owner,
//            @PathVariable String repo) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = createTokenHeaders();
//        String resposeBody = createRequestBodyNewName();
//        HttpEntity<String> entity =  new HttpEntity<>(resposeBody, headers);
//
//
//        ResponseEntity<String> result = restTemplate.exchange(gitUrl + "repos/" + owner +"/"+ repo + "/branches/"+ branch + "/rename", HttpMethod.POST, entity, String.class);
//        return result;
//    }


