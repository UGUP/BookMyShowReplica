package com.example.BookMyShow.models;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel {
    private String name;
    @ManyToOne
    private  Region region;
    @OneToMany
    //@ElementCollection
    private List<Screen> screens;
}
