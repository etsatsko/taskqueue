package com.webshop.simplewebapplication.database.User;

import com.webshop.simplewebapplication.database.HibernateSessionFactoryUtil;
import com.webshop.simplewebapplication.model.Usr;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class UserDAOHib implements UserDAO {

    @Override
    public Usr findByLogin(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM Usr where login = :login");
            query.setParameter("login", login);
            return (Usr) query.list().get(0);
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public void addUser(Usr usr) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            session.save(usr);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
