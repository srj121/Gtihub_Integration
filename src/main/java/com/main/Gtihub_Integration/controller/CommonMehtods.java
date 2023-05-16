package com.main.Gtihub_Integration.controller;

import com.main.Gtihub_Integration.entity.Branch;
import com.main.Gtihub_Integration.entity.RepositoryRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

public class CommonMehtods {

    //____________________________________________________________create Headers Entity Method___________________________________________________________________________________________________

    public HttpEntity<String> createHeadersEntity() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return new HttpEntity<String>("parameters", headers);
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
    //____________________________________________________________create  Request Body for ref sha Method___________________________________________________________________________________________________

    public String createRequestBodyNewBranch(Branch branch) {

        // Prepare the request body
        return "{\"ref\":\"" + branch.getNew_Name() + "\",\"sha\":\"" + branch.getSha() + "\"}";
    }

    //____________________________________________________________create  Request Body Method___________________________________________________________________________________________________

    public String createRequestBody(RepositoryRequest repositoryrequest) {

        // Prepare the request body
        String requestBody = "{\"name\":\"" + repositoryrequest.getName() + "\",\"description\":\"" +
                repositoryrequest.getDescription() + "\",\"private\":" + repositoryrequest.isPrivate() + "}";
        return requestBody;
    }
    //____________________________________________________________create  Request Body for Name Method___________________________________________________________________________________________________

    public String createRequestBodyNewName() {
        Branch branch = new Branch();
        branch.setNew_Name("RajasekharNewBranch");
        // Prepare the request body
        String requestBody = "{\"new_name\":\"" + branch.getNew_Name() + "\"}";
        return requestBody;
    }
}
