package com.webshop.simplewebapplication.database.Task;

import com.webshop.simplewebapplication.database.HibernateSessionFactoryUtil;
import com.webshop.simplewebapplication.model.Task;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskDAOHib implements TaskDAO {

    @Override
    public void addTask(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public List<Task> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            List<Task> tasks = (List<Task>) session.createQuery("from Task").list();
            return tasks;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Task> findAllFinished() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            Query tasks = session.createQuery("from Task where status = :status");
            tasks.setParameter("status", "Finished");
            tasks.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return (List<Task>)tasks.list();
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Task findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM Task where id = :id");
            query.setParameter("id", id);
            return (Task)query.list().get(0);
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public void deleteTask(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            session.delete(task);
            transaction.commit();
        }catch (Exception e){
           e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public int countOfTasks() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            List<Task> tasks = (List<Task>) session.createQuery("From Task").list();
            return tasks.size();
        }catch (Exception e){
            return 0;
        }finally {
            session.close();
        }
    }

    @Override
    public void changeTask(Task task, String name, String description, int priority) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            task.setName(name);
            task.setDescription(description);
            task.setPriority(priority);
            session.update(task);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void changeStatus(Task task, String status) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            task.setStatus(status);
            session.update(task);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
