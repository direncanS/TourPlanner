package com.example.tourapp.Model;
import com.example.tourapp.TourApp;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import java.util.HashMap;
import java.util.Map;

public class HibranateUtil {


    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;

    static {
        try {

            if (sessionFactory == null)
            {
                standardServiceRegistry = new StandardServiceRegistryBuilder().configure(TourApp.class.getResource("hibernate.cfg.xml")).build();
                MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
                Metadata metadata = metadataSources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            }

        } catch (Exception e) {
            if (standardServiceRegistry != null)
            {
                StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
            }
            System.out.println(e);
        }

    }
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

}