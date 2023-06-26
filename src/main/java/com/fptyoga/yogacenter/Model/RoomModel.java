package com.fptyoga.yogacenter.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomModel {
    private Long room_id;
    private String room_name;
    private String status;

}
