package com.epam.training.ticketservice.core.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    private String title;
    private String genre;
    private int length;

    @Override
    public String toString(){
        return title + " (" + genre + ", " + length + " minutes)";
    }
}
