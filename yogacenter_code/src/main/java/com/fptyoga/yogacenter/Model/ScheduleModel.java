package com.fptyoga.yogacenter.Model;

import com.fptyoga.yogacenter.Entity.Class;
import com.fptyoga.yogacenter.Entity.Role;
import com.fptyoga.yogacenter.Entity.Room;
import com.fptyoga.yogacenter.Entity.Time;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScheduleModel {
    private Long schedule_Id;
    private Role role_id;
    private Class class_sh;
    private Time time_id;
    private Room room_sh;

}
