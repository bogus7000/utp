package com.brodskyi.assignment11.implementation.repository;

import com.brodskyi.assignment11.api.entity.Product;
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
public class ProductRepository implements IRepository<Product> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void addOrUpdate(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void delete(Product product) {
        entityManager.remove(product);
    }
}
