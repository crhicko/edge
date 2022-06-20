package com.dom.edge.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class SportEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sport_seq")
    @SequenceGenerator(name = "sport_seq")
    private Long id;

    private Timestamp timestamp;

    private String sport;

    private String match_title;

    @Column(columnDefinition = "TEXT")
    private String data_event;

    public SportEvent(String sport, String match_title, String data_event) {
        this.sport = sport;
        this.match_title = match_title;
        this.data_event = data_event;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
}
