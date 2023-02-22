package com.example.repository;


import com.example.entity.Expert;
import com.example.utils.SearchCriteria;
import com.example.utils.ExpertSearchQueryCriteriaConsumer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpertSearch {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Expert> searchUser(List<SearchCriteria> params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Expert> query = builder.createQuery(Expert.class);
        Root<Expert> r = query.from(Expert.class);

        Predicate predicate = builder.conjunction();

        ExpertSearchQueryCriteriaConsumer searchConsumer = new ExpertSearchQueryCriteriaConsumer(predicate, builder, r);
        params.forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }
}
