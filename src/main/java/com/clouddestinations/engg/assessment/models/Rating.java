package com.clouddestinations.engg.assessment.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public Rating(int year, String assessedRating) {
        this.year = year;
        this.assessedRating = assessedRating;
    }
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
    public Rating(){

    }
    @Override
    public String toString() {
        return "Rating [assessedRating=" + assessedRating + ", id=" + id + ", year=" + year
                + "]";
    }

    
}
