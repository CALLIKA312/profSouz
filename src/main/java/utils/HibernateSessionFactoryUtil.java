package utils;

import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private static Configuration configuration;
    private static String curDBFilePath = "";

    public HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                configuration = new Configuration().configure("configures/hibernate.cfg.xml");
                configuration.addAnnotatedClass(UserModel.class);
                configuration.addAnnotatedClass(StudentModel.class);


                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Ошибка!" + e);
            }
        }
        return sessionFactory;
    }
}

