package com.main.Gtihub_Integration.controller;

import com.main.Gtihub_Integration.entity.RepositoryRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/Organisation")
public class GitHubOrganisationController {
    CommonMehtods commonMehtods = new CommonMehtods();

    //____________________________________________________________get org repo___________________________________________________________________________________________________

    @GetMapping("/get/repository/{org}")
    public ResponseEntity<String> getOrgRepo(@PathVariable String org) {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = commonMehtods.createHeadersEntity();
        return restTemplate.exchange(commonMehtods.gitUrl + "orgs/" + org + "/repos", HttpMethod.GET, entity, String.class);

    }

    //____________________________________________________________create org repo___________________________________________________________________________________________________
    @PostMapping("create/repository/{org}")
    public ResponseEntity<String> createOrgRepository(
            @PathVariable String org,
            @RequestBody RepositoryRequest repositoryRequest
    ) {
        String requestBody = commonMehtods.createRequestBody(repositoryRequest);

        HttpHeaders headers = commonMehtods.createTokenHeaders();

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(commonMehtods.gitUrl + "orgs/" + org + "/repos", HttpMethod.POST, entity, String.class);
    }
    //____________________________________________________________update org repo___________________________________________________________________________________________________

    @PostMapping("update/repository/{owner}/{repo}")
    public ResponseEntity<String> updateOrgRepo(
            @PathVariable String owner,
            @PathVariable String repo,
              @RequestBody RepositoryRequest repositoryRequest
    ) {
        String newName = commonMehtods.createRequestBody(repositoryRequest);


        HttpHeaders headers = commonMehtods.createTokenHeaders();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(newName, headers);
        return restTemplate.exchange(commonMehtods.gitUrl + "repos/" + owner + "/" + repo, HttpMethod.POST, entity, String.class);
    }

    //____________________________________________________________delete org repo___________________________________________________________________________________________________

    @DeleteMapping("delete/repository{owner}/{repo}")
    public ResponseEntity<String> deleteOrgRepo(@PathVariable String owner, @PathVariable String repo) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = commonMehtods.createTokenHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(commonMehtods.gitUrl + "repos/"+ owner +"/"+ repo, HttpMethod.DELETE, entity, Void.class);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    //____________________________________________________________get org repo languages___________________________________________________________________________________________________
    @GetMapping("/repository/languages/{org}/{repo}")
    public ResponseEntity<String> getOrgRepositoryLang(
            @PathVariable String org,
            @PathVariable String repo) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = commonMehtods.createHeadersEntity();
        return restTemplate.exchange(commonMehtods.gitUrl + "repos/" + org + "/" + repo + "/languages", HttpMethod.GET, entity, String.class);
    }

    //____________________________________________________________get org repo tags___________________________________________________________________________________________________
    @GetMapping("/repository/tags/{org}/{repo}")
    public ResponseEntity<String> getOrgRepositoryTags(
            @PathVariable String org,
            @PathVariable String repo) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = commonMehtods.createHeadersEntity();
        return restTemplate.exchange(commonMehtods.gitUrl + "repos/" + org + "/" + repo + "/tags", HttpMethod.GET, entity, String.class);
    }

    //____________________________________________________________get org repo teams___________________________________________________________________________________________________
    @GetMapping("/repository/teams/{org}/{repo}")
    public ResponseEntity<String> getOrgRepositoryTeams(
            @PathVariable String org,
            @PathVariable String repo) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = commonMehtods.createTokenHeaders();

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return restTemplate.exchange(commonMehtods.gitUrl + "repos/" + org + "/" + repo + "/teams", HttpMethod.GET, entity, String.class);
    }

    //____________________________________________________________get org repo topics___________________________________________________________________________________________________
    @GetMapping("/repository/topics/{org}/{repo}")
    public ResponseEntity<String> getOrgRepositoryTopics(
            @PathVariable String org,
            @PathVariable String repo) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = commonMehtods.createTokenHeaders();

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return restTemplate.exchange(commonMehtods.gitUrl + "repos/" + org + "/" + repo + "/topics", HttpMethod.GET, entity, String.class);
    }
    //____________________________________________________________list of repository contributor___________________________________________________________________________________________________

    @GetMapping("/repository/contributors/{owner}/{repo}")
    public ResponseEntity<String> getContributor(
            @PathVariable String owner,
            @PathVariable String repo) {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = commonMehtods.createHeadersEntity();
        return restTemplate.exchange(commonMehtods.gitUrl + "repos/" + owner + "/" + repo + "/contributors", HttpMethod.GET, entity, String.class);

    }


}
