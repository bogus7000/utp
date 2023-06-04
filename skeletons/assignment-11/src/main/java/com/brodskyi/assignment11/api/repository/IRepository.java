package com.brodskyi.assignment11.api.repository;

import com.brodskyi.assignment11.api.entity.EntityBase;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface IRepository<E extends EntityBase> {
        EntityManager entityManager = null;

        void addOrUpdate(E entity);

        void delete(E entity);

        E findById(Long id);

        List<E> findAll();
}
