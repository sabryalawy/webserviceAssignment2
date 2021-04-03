package com.example.webservicesv1.customer;


import com.example.webservicesv1.room.Room;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;

    String firstName;
    String lastName;
    String phoneNumber;

    @ManyToMany
    List<Room> rooms=new ArrayList<>();

    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, String phoneNumber, List<Room> rooms) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.rooms = rooms;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
