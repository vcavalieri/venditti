package com.garage.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.garage.utils.SingletonHiberUtil;

@Component
public class TransactionManager<T> {

	@Autowired
	private ApplicationContext ctx;

	@SuppressWarnings("unused")
	private T t;

	public TransactionManager() {
	}

	public TransactionManager(T t) {
		this.t = t;
	}

	public T get(T t) {
		return t;
	}

	@SuppressWarnings({ "unchecked", "deprecation", "static-access" })
	public List<T> search(T example) throws Exception {

		List<T> toReturn = new ArrayList<T>();
		Session session = null;
		Transaction tx = null;
		try {
			session = ctx.getBean(SingletonHiberUtil.class).getSession();
			tx = session.beginTransaction();
			Example myExample = Example.create(example);
			Criteria criteria = session.createCriteria(example.getClass()).add(myExample);
			toReturn = criteria.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return toReturn;
	}
	
	@SuppressWarnings("static-access")
	public boolean update(T example) throws Exception {
		
		boolean flag = false;
		Session session = null;
		Transaction tx = null;
		try {
			session = ctx.getBean(SingletonHiberUtil.class).getSession();
			tx = session.beginTransaction();
			session.merge(example);
			flag = true;
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw e;
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public boolean insert(T example) throws Exception {

		boolean flag = false;
		Session session = null;
		Transaction tx = null;
		try {
			session = ctx.getBean(SingletonHiberUtil.class).getSession();
			tx = session.beginTransaction();
			session.persist(example);
			flag = true;
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw e;
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public boolean delete(T example) throws Exception {

		boolean flag = false;
		Session session = null;
		Transaction tx = null;
		try {
			session = ctx.getBean(SingletonHiberUtil.class).getSession();
			tx = session.beginTransaction();
			session.delete(example);
			flag = true;
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw e;
		}
		return flag;
	}

	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	public T searchByID(Class classofBean, Serializable key) {
		Session session = null;
		
		Transaction tx = null;
		T toReturn;
		try {
			session = ctx.getBean(SingletonHiberUtil.class).getSession();
			tx = session.beginTransaction();
			toReturn = (T)session.load(classofBean, key);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw e;
		}
		return toReturn;
	}
	
	
	@SuppressWarnings("unchecked")
	public T initializeAndUnproxy(T entity) {
	    if (entity == null) {
	        throw new 
	           NullPointerException("Entity passed for initialization is null");
	    }

	    Hibernate.initialize(entity);
	    if (entity instanceof HibernateProxy) {
	        entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
	                .getImplementation();
	    }
	    return entity;
	}

}
