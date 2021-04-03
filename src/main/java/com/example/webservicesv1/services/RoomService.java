package com.example.webservicesv1.services;

import com.example.webservicesv1.customer.Customer;
import com.example.webservicesv1.customer.CustomerRepo;
import com.example.webservicesv1.room.Room;
import com.example.webservicesv1.room.RoomRepo;
import config.RoomCustomerID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoomService {

    @Autowired
    RoomRepo roomRepo;

    @Autowired
    CustomerRepo customerRepo;

    @RequestMapping(method = RequestMethod.POST,value = "/room")
    public String addRoom(@RequestBody Room room){
        roomRepo.save(room);
        return "done!";
    }

    @RequestMapping("/room")
    public String getAll(){
        return roomRepo.findAll().toString();
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/room/{id}")
    public String deleteRoom(@PathVariable int id ){
//        deleteRoomFromCustomer(id);
        roomRepo.deleteById(id);
        return "Done!";
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/room/{id}")
    public String updateRoom(@PathVariable int id,@RequestBody Room room){
        if (roomRepo.existsById(id)&&roomRepo.existsById(room.getRoomId())&&room.getRoomId()==id){
            Room temp=roomRepo.findById(id).get();
            if (room.getBeds()==0)
                room.setBeds(temp.getBeds());
            roomRepo.save(room);
            return "done!";
        }
        return "noooo";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/room/Tocustomer")
    public String bookCustomerARoom(@RequestBody RoomCustomerID room) {
        Customer tempC=customerRepo.findById(room.getCustomerId()).get();
        Room tempR=roomRepo.findById(room.getRoomId()).get();
        tempC.getRooms().add(tempR);
        tempR.getCustomers().add(tempC);


        roomRepo.save(tempR);
        customerRepo.save(tempC);

        return  room.getCustomerId()+" "+room.getRoomId();
    }

    @RequestMapping("/room/notbooked")
    public String notBooked(){
        List<Room> rooms= (List<Room>) roomRepo.findAll();
        List<Room> rez=new ArrayList<>();
        for (Room r:rooms){
            if(r.getCustomers().isEmpty()){
                rez.add(r);
            }
        }
        return rez.toString();
    }

    @RequestMapping("/room/booked")
    public String booked(){
        List<Room> rooms= (List<Room>) roomRepo.findAll();
        List<Room> rez=new ArrayList<>();
        for (Room r:rooms){
            if(!r.getCustomers().isEmpty()){
                rez.add(r);
            }
        }
        return rez.toString();
    }
}
