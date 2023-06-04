package com.brodskyi.assignment11.implementation.repository;

import com.brodskyi.assignment11.api.entity.Order;
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
public class OrderRepository implements IRepository<Order> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order findById(Long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void addOrUpdate(Order order) {
        entityManager.persist(order);
    }

    @Override
    public void delete(Order order) {
        entityManager.remove(order);
    }
}
