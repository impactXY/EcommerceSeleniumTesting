package com.example.setup;

public enum sortOption {
    // //*[@id="main-container"]/section[1]/div/div[3]/div[2]/div[1]/div[4]/div/div/div[1]/div[1] - dropdown
    POPULARITY("popularity_v_opt", "desc"),
    NEWEST("id", "desc"),
    PRICE_ASCENDING("price", "asc"),
    PRICE_DESCENDING("price", "desc"),
    REVIEWS("reviews", "desc"),
    DISCOUNT("discount", "desc");

    private final String sortId;
    private final String sortDir;

    sortOption(String sortId, String sortDir) {
        this.sortId = sortId;
        this.sortDir = sortDir;
    }

    public String getSortId() {
        return sortId;
    }

    public String getSortDir() {
        return sortDir;
    }
}
