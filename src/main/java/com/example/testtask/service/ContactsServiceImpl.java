package com.example.testtask.service;

import com.example.testtask.Constants;
import com.example.testtask.DTO.Response;
import com.example.testtask.model.ContactsEntity;
import com.example.testtask.model.UsersEntity;
import com.example.testtask.util.HibernateUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Component
@Service
public class ContactsServiceImpl implements ContactsService {

    private static final Logger logger = LogManager.getLogger(ContactsServiceImpl.class);

    /**
     * Метод получения пользователей и дальнейшее формирование ответа
     * @return - лист с иерархиями контактов (при условии что БД не содержит циключеских контактов)
     */
    @Override
    public List<Response> getAllUsersToContact() {
        List<UsersEntity> usersEntities = getAllUsers();
        List<Response> responses = new ArrayList<>(usersEntities.size());
        for (UsersEntity user : usersEntities) {
            responses.add(setResponse(user));
        }
        Collections.sort(responses);
        return responses;
    }

    /**
     * Метод формирования иерархии
     * @param user - Entity человека
     * @return - Иерархия контактов передаваемого человека
     */
    Response setResponse(UsersEntity user) {
        Response userDTO = new Response(user.getFirstName(), user.getLastName());
        Response userLast;
        List<Response> responseList = new ArrayList<>();
        for (ContactsEntity contacts : user.getFirstUsers()) {
            UsersEntity usersEntity = getUserById(contacts.getSecond_user());
            userLast = setResponse(usersEntity);
            responseList.add(userLast);
        }
        if(responseList.isEmpty()) {
            userDTO.setResponse(null);
        }else {
            userDTO.setResponse(responseList);
            Collections.sort(responseList);
        }

        return userDTO;
    }

    /**
     * Метод получения всех людей из БД
     * @return - лист пользователей
     */
    List<UsersEntity> getAllUsers() {
        Transaction transaction = null;
        List<UsersEntity> usersEntity;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            usersEntity = session.createQuery("from UsersEntity ", UsersEntity.class).list();
            transaction.commit();
            logger.info(UsersEntity.class.getSimpleName() + Constants.ADDED);
            return usersEntity;
        } catch (Exception e) {
            logger.info(e.getClass() + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    /**
     * Метод получения человека по уникальному ID
     * @param id - уникальный ID человека
     * @return - Entity человека
     * */
    UsersEntity getUserById(Integer id) {
        Transaction transaction = null;
        UsersEntity usersEntity;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            usersEntity = session.get(UsersEntity.class, id);
            transaction.commit();
            logger.info(UsersEntity.class.getSimpleName() + Constants.ADDED);
            return usersEntity;
        } catch (Exception e) {
            logger.info(e.getClass() + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    /**
     * Метод получения сессии
     * @return - сессия с БД
     */
    Session getSession() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }
}
