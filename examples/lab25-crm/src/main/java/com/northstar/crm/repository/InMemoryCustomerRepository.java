package com.northstar.crm.repository;

import com.northstar.crm.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {
  private final Map<String, Customer> store = new ConcurrentHashMap<>();

  public InMemoryCustomerRepository() {
    // TODO: seed Customer.amina() and Customer.ravi() into store
    store.put("CUS-1001", Customer.amina());
    store.put("CUS-1002", Customer.ravi());
  }

  @Override
  public Customer save(Customer customer) {
    // TODO: put and return
   store.put(customer.getId(), customer);
    return customer;
  }

  @Override
  public Optional<Customer> findById(String id) {
    // TODO: Optional.ofNullable(store.get(id))
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public List<Customer> findAll() {
    // TODO: return new ArrayList<>(store.values())
    return new ArrayList<>(store.values());
  }

  @Override
  public boolean existsById(String id) {
    // TODO: store.containsKey(id)
    return store.containsKey(id);
  }
}
