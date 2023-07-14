package com.fptyoga.yogacenter.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Room_table")
public class Room {
    @Id
    @Column(name = "room_id")
    private Long roomid;

    @Column(name = "room_name")
    private String roomname;

    @Column(name = "status")
    private String status;

}