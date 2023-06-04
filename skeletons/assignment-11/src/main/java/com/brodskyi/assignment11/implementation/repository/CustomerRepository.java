package com.brodskyi.assignment11.implementation.repository;

import com.brodskyi.assignment11.api.entity.Customer;
import com.brodskyi.assignment11.api.repository.IRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CustomerRepository implements IRepository<Customer> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addOrUpdate(Customer entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Customer entity) {
        entityManager.remove(entity);
    }

    @Override
    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }
}
