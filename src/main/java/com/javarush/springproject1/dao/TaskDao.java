package com.javarush.springproject1.dao;

import com.javarush.springproject1.domain.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TaskDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Task> getAllTasks(int offset, int limit) {
        TypedQuery<Task> query = entityManager.createQuery("select t from Task t", Task.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public Long pagesCount() {
        TypedQuery<Long> query = entityManager.createQuery("select count(t) from Task t", Long.class);
        return query.getSingleResult();
    }

    public Task getTaskById(int id) {
        TypedQuery<Task> query = entityManager.createQuery("select t from Task t where t.id = :ID", Task.class);
        query.setParameter("ID", id);
        return query.getSingleResult();
    }

    public void create(Task task) {
        entityManager.persist(task);
    }

    public void update(Task task) {
        entityManager.merge(task);
    }

    public void delete(Task task) {
        entityManager.remove(task);
    }

}
