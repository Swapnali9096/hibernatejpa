package com.jspiders.hibernatejpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.hibernatejpa.dto.StudentDto;

public class StudentDao {
private static EntityManagerFactory entityManagerFactory;
private static EntityManager entityManager;
private static EntityTransaction entityTransaction;

public static void main(String[] args) {
	StudentDto studentDto=new StudentDto();
	studentDto.setId(4);
	studentDto.setName("Vijay");
	studentDto.setEmail("vijay@gmailcom");
	studentDto.setMobile(9879543425l);
	studentDto.setAge(22);
openConnection();
entityTransaction.begin();
entityManager.persist(studentDto);
entityTransaction.commit();
closeConnection();
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
