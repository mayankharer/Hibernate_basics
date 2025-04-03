package com.telusko;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Create a Student object
        Student s1 = new Student();

        // Initialize Hibernate and configure it with the Student class
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Student.class) // Add the Student entity class
                .configure() // Read configuration from hibernate.cfg.xml
                .buildSessionFactory(); // Build the SessionFactory

        // Open a new session
        Session session = sessionFactory.openSession();

        // Retrieve a student record from the database using its primary key (Roll No: 106)
        s1 = session.get(Student.class, 106);

        // Remove the retrieved student from the database
        if (s1 != null) {
            session.remove(s1); // Deletes the student if it exists
        } else {
            System.out.println("Student not found!");
        }

        // Begin transaction (Required for any update, delete, or insert operation)
        Transaction transaction = session.beginTransaction();
        transaction.commit(); // Commit changes to the database

        // Alternative to update or insert a student record
        // session.merge(s1); // Merge updates the data if exists, otherwise inserts a new record
        // session.persist(s1); // Persi st is preferred over save (save is deprecated)

        // Close the session
        session.close();
        // Close the SessionFactory (Best practice to free up resources)
        sessionFactory.close();
    }
}
