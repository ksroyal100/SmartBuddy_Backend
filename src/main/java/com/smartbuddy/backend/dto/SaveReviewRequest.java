package com.smartbuddy.backend.dto;

public class SaveReviewRequest {

    private String code;
    private String reviewHtml;

    public SaveReviewRequest() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReviewHtml() {
        return reviewHtml;
    }

    public void setReviewHtml(String reviewHtml) {
        this.reviewHtml = reviewHtml;
    }
}
