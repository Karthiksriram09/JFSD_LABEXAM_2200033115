package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Vehicle.class)
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Truck.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        try {
            session.beginTransaction();

            // Create and set properties for Car
            Car car = new Car();
            car.setName("Honda Civic");
            car.setType("Sedan");
            car.setMaxSpeed(220);
            car.setColor("Red");
            car.setNumberOfDoors(4);

            // Create and set properties for Truck
            Truck truck = new Truck();
            truck.setName("Volvo FH16");
            truck.setType("Truck");
            truck.setMaxSpeed(160);
            truck.setColor("Blue");
            truck.setLoadCapacity(20000);

            // Save the vehicles
            session.save(car);
            session.save(truck);

            // Commit the transaction
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
