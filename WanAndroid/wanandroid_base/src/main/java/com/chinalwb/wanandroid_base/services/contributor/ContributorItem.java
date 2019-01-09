package com.chinalwb.wanandroid_base.services.contributor;

public class ContributorItem {

    private String avatarUrl;

    private String author;

    private String componentName;

    private String componentDesc;

    private String github;

    public ContributorItem(String avatarUrl, String author, String componentName, String componentDesc, String github) {
        this.avatarUrl = avatarUrl;
        this.author = author;
        this.componentName = componentName;
        this.componentDesc = componentDesc;
        this.github = github;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentDesc() {
        return componentDesc;
    }

    public void setComponentDesc(String componentDesc) {
        this.componentDesc = componentDesc;
    }
}
