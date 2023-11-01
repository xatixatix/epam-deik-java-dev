package com.epam.training.ticketservice.core.booking.entity;

import com.epam.training.ticketservice.core.screening.entity.Screening;
import com.epam.training.ticketservice.core.user.entity.MyUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private MyUser user;
    @ManyToOne
    private Screening screening;
    @ElementCollection(fetch = FetchType.EAGER)
    List<Seat> seats;

    public Booking(MyUser user, Screening screening, List<Seat> seats) {
        this.user = user;
        this.screening = screening;
        this.seats = seats;
    }
}
