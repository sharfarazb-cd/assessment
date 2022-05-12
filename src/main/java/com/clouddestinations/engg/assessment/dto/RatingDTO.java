package com.clouddestinations.engg.assessment.dto;


public class RatingDTO {

    private int id;
    private int year;
    private String assessedRating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAssessedRating() {
        return assessedRating;
    }

    public void setAssessedRating(String assessedRating) {
        this.assessedRating = assessedRating;
    }
}
