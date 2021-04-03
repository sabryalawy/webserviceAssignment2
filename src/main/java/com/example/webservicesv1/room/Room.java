package com.example.webservicesv1.room;

import com.example.webservicesv1.customer.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int roomId;

    int beds;

    @ManyToMany
    List<Customer> customers = new ArrayList<>();

    public Room(int roomId, int beds, List<Customer> customers) {

        this.roomId = roomId;
        this.beds = beds;
        this.customers = customers;
    }

    public Room() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", beds=" + beds +
                '}';
    }
}
