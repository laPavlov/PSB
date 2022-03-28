package com.example.testtask.util;

import com.example.testtask.Constants;
import com.example.testtask.model.ContactsEntity;
import com.example.testtask.model.UsersEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;

public class HibernateUtil {
    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

    public static SessionFactory getSessionFactory() {
        logger.info("start getSessionFactory()");
        File file = new File(Constants.DEFAULT_HBN_CONFIG_PATH);
        logger.debug("file name: " + file.getName());
        Configuration configuration = new Configuration().configure(file);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);

        metadataSources.addAnnotatedClass(ContactsEntity.class);
        metadataSources.addAnnotatedClass(UsersEntity.class);


        SessionFactory sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        logger.info("end getSessionFactory()");
        return sessionFactory;
    }
}