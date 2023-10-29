package com.epam.training.ticketservice.core.room.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    private String name;
    private int rows;
    private int columns;

    @Override
    public String toString(){
        return "Room " + name + " with " + (rows * columns) + " seats, " + rows + " rows and " + columns + " columns";
    }
}
