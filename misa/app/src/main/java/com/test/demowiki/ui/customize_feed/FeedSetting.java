package com.test.demowiki.ui.customize_feed;

public class FeedSetting {
    String feedType;
    String feedTypeDesc;
    boolean status;

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getFeedTypeDesc() {
        return feedTypeDesc;
    }

    public void setFeedTypeDesc(String feedTypeDesc) {
        this.feedTypeDesc = feedTypeDesc;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
