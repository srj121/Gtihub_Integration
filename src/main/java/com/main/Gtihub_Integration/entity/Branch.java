package com.main.Gtihub_Integration.entity;

public class Branch {

    String new_Name;
    String ref = "refs/heads/";
    String sha;

    public String getNew_Name() {
        return ref + new_Name;
    }

    public void setNew_Name(String new_Name) {
        this.new_Name = new_Name;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

}
