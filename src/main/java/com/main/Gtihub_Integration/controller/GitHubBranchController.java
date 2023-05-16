package com.main.Gtihub_Integration.controller;

import com.main.Gtihub_Integration.entity.Branch;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class GitHubBranchController {

    static String gitUrl = "https://api.github.com/";
    CommonMehtods commonMehtods = new CommonMehtods();
    //____________________________________________________________List branches___________________________________________________________________________________________________

    @GetMapping("/repos/{owner}/{repo}/branches")
    public ResponseEntity<String> getListBranches(
            @PathVariable String owner,
            @PathVariable String repo) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity =  commonMehtods.createHeadersEntity();

        return restTemplate.exchange(gitUrl + "repos/" + owner +"/"+ repo + "/branches", HttpMethod.GET, entity, String.class);
    }
    //____________________________________________________________find particular branch___________________________________________________________________________________________________

    @GetMapping("/repos/{owner}/{repo}/branches/{branch}")
    public ResponseEntity<String> getBranch(
            @PathVariable String owner,
            @PathVariable String repo,
            @PathVariable String branch) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity =  commonMehtods.createHeadersEntity();

        return restTemplate.exchange(gitUrl + "repos/" + owner +"/"+ repo + "/branches/"+ branch, HttpMethod.GET, entity, String.class);
    }
    //____________________________________________________________rename particular branch___________________________________________________________________________________________________

    @PostMapping("/repos/{owner}/{repo}/branches/{branch}/rename")
    public ResponseEntity<String> RenameBranch(
            @PathVariable String owner,
            @PathVariable String repo,
            @PathVariable String branch) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = commonMehtods.createTokenHeaders();
        String resposeBody = commonMehtods.createRequestBodyNewName();
        HttpEntity<String> entity =  new HttpEntity<>(resposeBody, headers);


        return restTemplate.exchange(gitUrl + "repos/" + owner +"/"+ repo + "/branches/"+ branch + "/rename", HttpMethod.POST, entity, String.class);
    }
    //____________________________________________________________create org branch___________________________________________________________________________________________________

    @PostMapping("/repos/{owner}/{repo}/git/refs")
    public ResponseEntity<String> createBranch(
            @PathVariable String owner,
            @PathVariable String repo,
            @RequestBody Branch branch
    ) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = commonMehtods.createTokenHeaders();
        String resposeBody = commonMehtods.createRequestBodyNewBranch(branch);
        HttpEntity<String> entity =  new HttpEntity<>(resposeBody, headers);


        return restTemplate.exchange(gitUrl + "repos/"+ owner +"/"+ repo +"/git/refs", HttpMethod.POST, entity, String.class);
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


