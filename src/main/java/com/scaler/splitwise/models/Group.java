package com.scaler.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;



@Getter
@Setter
@Entity(name="groupUser")
public class Group extends BaseModel {
    String name;
    @ManyToMany
    private List<User> members;
    @ManyToOne
    private User createdBy; //admin

    @OneToMany(mappedBy = "group")
    private List<Expense>expenses;

}
