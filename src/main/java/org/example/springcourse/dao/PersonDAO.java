package org.example.springcourse.dao;

import org.example.springcourse.models.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {
        Session session = entityManager.unwrap(Session.class);

//        // 1 Query
//        List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();
//
//        // N Queries
//        for (Person person : people) {
//            System.out.println("Person " + person.getName() + " has: " + person.getItems());
//        }

        // Solution
        // SQL: A LEFT JOIN B -> The resulting combined table

        Set<Person> people = new HashSet<>(session.createQuery(
                "select p from Person p left join fetch p.items", Person.class).getResultList());

        for (Person person : people) {
            System.out.println("Person " + person.getName() + " has: " + person.getItems());
        }

    }

}
