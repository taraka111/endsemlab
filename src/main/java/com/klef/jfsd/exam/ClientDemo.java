package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(Vehicle.class);
        config.addAnnotatedClass(Car.class);
        config.addAnnotatedClass(Truck.class);

        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Insert a Car record
            Car car = new Car();
            car.setName("Tesla Model S");
            car.setType("Electric");
            car.setMaxSpeed(250);
            car.setColor("Red");
            car.setNumberOfDoors(4);

            Truck truck = new Truck();
            truck.setName("Ford F-150");
            truck.setType("Diesel");
            truck.setMaxSpeed(120);
            truck.setColor("Blue");
            truck.setLoadCapacity(10.5);
            session.save(car);
            session.save(truck);

            transaction.commit();
            System.out.println("Records inserted successfully.");
            System.out.println("Inserted Car Record:");
            System.out.println("ID: " + car.getId());
            System.out.println("Name: " + car.getName());
            System.out.println("Type: " + car.getType());
            System.out.println("Max Speed: " + car.getMaxSpeed());
            System.out.println("Color: " + car.getColor());
            System.out.println("Number of Doors: " + car.getNumberOfDoors());

            System.out.println("\nInserted Truck Record:");
            System.out.println("ID: " + truck.getId());
            System.out.println("Name: " + truck.getName());
            System.out.println("Type: " + truck.getType());
            System.out.println("Max Speed: " + truck.getMaxSpeed());
            System.out.println("Color: " + truck.getColor());
            System.out.println("Load Capacity: " + truck.getLoadCapacity());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
