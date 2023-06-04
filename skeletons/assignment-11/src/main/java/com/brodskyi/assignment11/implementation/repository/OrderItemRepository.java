package com.brodskyi.assignment11.implementation.repository;

import com.brodskyi.assignment11.api.entity.OrderItem;
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
public class OrderItemRepository implements IRepository<OrderItem> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public OrderItem findById(Long id) {
        return entityManager.find(OrderItem.class, id);
    }

    @Override
    public List<OrderItem> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderItem> query = cb.createQuery(OrderItem.class);
        Root<OrderItem> root = query.from(OrderItem.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void addOrUpdate(OrderItem orderItem) {
        entityManager.persist(orderItem);
    }

    @Override
    public void delete(OrderItem orderItem) {
        entityManager.remove(orderItem);
    }
}
