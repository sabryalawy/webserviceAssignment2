package com.example.webservicesv1.customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo  extends CrudRepository<Customer,Integer> {
}
