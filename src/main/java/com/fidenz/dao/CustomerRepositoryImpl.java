package com.fidenz.dao;

import com.fidenz.model.Customers;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    public static final Logger LOGGER = Logger.getLogger(CustomerRepositoryImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<Customers> findAllCustomersById(String id) {
        List<Customers> customers = null;
        try {
            Session session = getSession();
            Query query = session.createQuery("FROM Customers c WHERE c.id=:id");
            query.setParameter("id", id);
            customers = query.getResultList();
        } catch (NoResultException e) {
            LOGGER.info("NO Result for findAllCustomersById");
        }
        return customers;
    }

    @Override
    public List<Customers> findAllCustomersByName(String name) {
        List<Customers> customers = null;
        try {
            Session session = getSession();
            Query query = session.createQuery("FROM Customers c WHERE c.name like :name");
            query.setParameter("name", "%"+name+"%");
            customers = query.getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
            LOGGER.info("NO Result for findAllCustomersByName");
        }
        return customers;
    }

    @Override
    public List<Customers> findAllCustomersByZipCode(String zipCode) {
        List<Customers> resultList = null;
        try {
            Session session = getSession();
            Query query = session.createQuery("FROM Customers c where c.zipcode=:zipCode ");
            query.setParameter("zipCode", zipCode);
            resultList = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return resultList;
    }

    @Override
    public List<String> findAllCustomersGroupByZipCode() {
        List<String> resultList = null;
        try {
            Session session = getSession();
            Query query = session.createQuery("select c.zipcode FROM Customers c GROUP BY c.zipcode");
            resultList = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return resultList;
    }
}
