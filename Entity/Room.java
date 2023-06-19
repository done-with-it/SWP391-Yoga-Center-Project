package com.fptyoga.yogacenter.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Room_table")
public class Room {
    @Id
    @Column(name = "room_id")
    private Long room_Id;

    @Column(name = "room_name")
    private String room_Name;

    @Column(name = "status")
    private String status;

}
