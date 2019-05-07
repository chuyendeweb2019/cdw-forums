package com.chuyendeweb.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.chuyendeweb.entity.Post;

public class Test {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db_springboot_forum");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
 
        Post project  = em.find(Post.class, 1);
 
        System.out.println("Project name: " + project.getTitle());
        project.getUser().forEach(
            (user) -> System.out.println(user.getUsername())
        );
 
        em.close();
        emf.close();
    }
}

