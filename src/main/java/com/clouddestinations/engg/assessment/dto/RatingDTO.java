package com.clouddestinations.engg.assessment.dto;


public class RatingDTO {

    private final int id;
    private final int year;
    private final String assessedRating;

    public RatingDTO(int id, int year, String assessedRating) {
        this.id = id;
        this.year = year;
        this.assessedRating = assessedRating;
    }

    public int getId() {return id;}

    public int getYear() {return year;}

    public String getAssessedRating() {return assessedRating;}
}
