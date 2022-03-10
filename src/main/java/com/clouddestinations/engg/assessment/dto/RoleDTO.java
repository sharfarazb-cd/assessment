package com.clouddestinations.engg.assessment.dto;

public class RoleDTO {
    private final int id;
    private final String name;

    public RoleDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {return id;}

    public String getName() {return name;}
}
