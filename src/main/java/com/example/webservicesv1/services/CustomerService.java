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
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    RoomRepo roomRepo;

    @RequestMapping(method = RequestMethod.POST, value = "/customer")
    public String addCustomer(@RequestBody Customer customer){
        this.customerRepo.save(customer);
        return "completed";
    }

    @RequestMapping("/customer")
    public String getAllCustomer(){
        return customerRepo.findAll().toString();
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/customer/{id}")
    public String deleteCustomer(@PathVariable int id){
        try{
            deleteCustomerFromRooms(id);
            customerRepo.deleteById(id);
            return "deleted";
    }catch (Exception e){
        return "not deleted";
        }
    }

    private void deleteCustomerFromRooms(int id) {
        roomRepo.findAll().forEach(room ->{
          room.getCustomers().forEach(customer -> {
              if(customer.getId()==id){
                  room.getCustomers().remove(customer);
                  roomRepo.save(room);
                  customerRepo.save(customer);
              }
          });
        });
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/customer/{id}")
    public String updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        if (customerRepo.existsById(id)&&customerRepo.existsById(customer.getId())&&id==customer.getId()){
            Customer temp = customerRepo.findById(id).get();

            if(customer.getFirstName()==null)
                customer.setFirstName(temp.getFirstName());
            if(customer.getLastName()==null)
                customer.setLastName(temp.getLastName());
            if(customer.getPhoneNumber()==null)
                customer.setPhoneNumber(temp.getPhoneNumber());
            customerRepo.save(customer);

            return "done";
        }
        return "Noooooooooooooooooooooooooooooo";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/customer/Tocustomer")
    public String bookCustomerARoom(@RequestBody RoomCustomerID room) {
        Customer tempC=customerRepo.findById(room.getCustomerId()).get();
        Room tempR=roomRepo.findById(room.getRoomId()).get();
        tempC.getRooms().add(tempR);
        tempR.getCustomers().add(tempC);


        roomRepo.save(tempR);
        customerRepo.save(tempC);

        return  room.getCustomerId()+" "+room.getRoomId();
    }

    @RequestMapping("/customer/notbooked")
    public String notBooked(){
        List<Customer> rooms= (List<Customer>) customerRepo.findAll();
        List<Customer> rez=new ArrayList<>();
        for (Customer r:rooms){
            if(r.getRooms().isEmpty()){
                rez.add(r);
            }
        }
        return rez.toString();
    }

    @RequestMapping("/customer/booked")
    public String booked(){
        List<Customer> rooms= (List<Customer>) customerRepo.findAll();
        List<Customer> rez=new ArrayList<>();
        for (Customer r:rooms){
            if(!r.getRooms().isEmpty()){
                rez.add(r);
            }
        }
        return rez.toString();
    }


}
