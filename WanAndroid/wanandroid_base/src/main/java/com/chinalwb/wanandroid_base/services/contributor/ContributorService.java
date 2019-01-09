package com.chinalwb.wanandroid_base.services.contributor;

import java.util.ArrayList;
import java.util.List;

public class ContributorService {

    private List<ContributorItem> contributorItemList = new ArrayList<>();

    private ContributorService() {}

    public static ContributorService getInstance() {
        return Inner.contributorService;
    }

    private static class Inner {
        private static ContributorService contributorService = new ContributorService();
    }

    public List<ContributorItem> getContributorItemList() {
        return this.contributorItemList;
    }
}
