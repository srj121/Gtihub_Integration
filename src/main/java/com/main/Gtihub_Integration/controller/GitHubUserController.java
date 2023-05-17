package com.main.Gtihub_Integration.controller;

import com.main.Gtihub_Integration.entity.Branch;
import com.main.Gtihub_Integration.entity.RepositoryRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class GitHubUserController {
    CommonMehtods commonMehtods = new CommonMehtods();

    //____________________________________________________________get user___________________________________________________________________________________________________
    @GetMapping("findUser/{userName}")
    public ResponseEntity<String> getUserInfo(@PathVariable String userName) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = commonMehtods.createHeadersEntity();
        return restTemplate.exchange(commonMehtods.gitUrl + "users/" + userName, HttpMethod.GET, entity, String.class);
    }

    //____________________________________________________________repositories of Authenticated user___________________________________________________________________________________________________
    @GetMapping("/repositories")
    public ResponseEntity<String> getRepoOfAuthenticatedUser() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = commonMehtods.createTokenHeaders();
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return restTemplate.exchange(commonMehtods.gitUrl + "user/repos", HttpMethod.GET, entity, String.class);
    }

    //____________________________________________________________get user repo___________________________________________________________________________________________________
    @GetMapping("/{userName}/repos")
    public ResponseEntity<String> getUserRepository(@PathVariable String userName) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = commonMehtods.createHeadersEntity();
        return restTemplate.exchange(commonMehtods.gitUrl + "users/" + userName + "/repos", HttpMethod.GET, entity, String.class);
    }

    //____________________________________________________________create repo for Authenticated user___________________________________________________________________________________________________
    @PostMapping("/create/repo")
    public ResponseEntity<String> createAtuhUserRepository(@RequestBody RepositoryRequest repositoryRequest) {

        String requestBody = commonMehtods.createRequestBody(repositoryRequest);
        HttpHeaders headers = commonMehtods.createTokenHeaders();

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(commonMehtods.gitUrl + "user/repos", HttpMethod.POST, entity, String.class);
    }
    //____________________________________________________________create branch in user repo___________________________________________________________________________________________________

    @PostMapping("createBranch/{owner}/{repo}")
    public ResponseEntity<String> createUserBranch(
            @PathVariable String owner,
            @PathVariable String repo,
            @RequestBody Branch branch
    ) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = commonMehtods.createTokenHeaders();
        String resposeBody = commonMehtods.createRequestBodyNewBranch(branch);
        HttpEntity<String> entity = new HttpEntity<>(resposeBody, headers);


        return restTemplate.exchange(commonMehtods.gitUrl + "repos/" + owner + "/" + repo + "/git/refs", HttpMethod.POST, entity, String.class);
    }


//    @DeleteMapping("user/repo/delete/Branch/{owner}/")

}


