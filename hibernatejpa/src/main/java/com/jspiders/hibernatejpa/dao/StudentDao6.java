package com.jspiders.hibernatejpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jspiders.hibernatejpa.dto.StudentDto;

public class StudentDao6 {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

	public static void main(String[] args) {
		openConnection();
		entityTransaction.begin();
		List<StudentDto> students=findAll();
		for (StudentDto student : students) {
			
			System.out.println(student);
		}
		entityTransaction.commit();
		closeConnection();

	}
	private static List<StudentDto> findAll(){
		Query query=entityManager.createQuery("SELECT student FROM StudentDto student WHERE name LIKE '%esh'");
		@SuppressWarnings("unchecked")
		List<StudentDto> students=query.getResultList();
		return students;
	}

	private static void openConnection() {
		entityManagerFactory=Persistence.createEntityManagerFactory("student");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();

	}
	private static void closeConnection() {
		if(entityManagerFactory!=null) {
			entityManagerFactory.close();
		}
		if(entityManager!=null) {
			entityManager.close();
		}
		if(entityTransaction!=null) {
			if(entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}
	}


}
