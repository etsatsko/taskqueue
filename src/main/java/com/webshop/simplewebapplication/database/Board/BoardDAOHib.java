package com.webshop.simplewebapplication.database.Board;

import com.webshop.simplewebapplication.database.HibernateSessionFactoryUtil;
import com.webshop.simplewebapplication.model.Board;
import com.webshop.simplewebapplication.model.BoardTask;
import com.webshop.simplewebapplication.model.Task;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardDAOHib implements BoardDAO {

    @Override
    public List<Task> findAllInBoard(Board board) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            List<BoardTask> boardTasks  = (List<BoardTask>) session.createQuery("From BoardTask where board = :board").setParameter("board",board).list();
            ArrayList<Task> tasks = new ArrayList<>();
            for (int i = 0; i < boardTasks.size(); i++){
                tasks.add(boardTasks.get(i).getTask());
            }
            return tasks;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Task> getFinished(Board board) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            List<BoardTask> boardTasks  = (List<BoardTask>) session.createQuery("From BoardTask where board = :board").setParameter("board",board).list();
            ArrayList<Task> tasks = new ArrayList<>();
            for (int i = 0; i < boardTasks.size(); i++){
                Task task = boardTasks.get(i).getTask();
                if (task.getStatus().equals("Finished")) {
                    tasks.add(task);
                }
            }
            return tasks;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Task> getInProgress(Board board) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            List<BoardTask> boardTasks  = (List<BoardTask>) session.createQuery("From BoardTask where board = :board").setParameter("board",board).list();
            ArrayList<Task> tasks = new ArrayList<>();
            for (int i = 0; i < boardTasks.size(); i++){
                Task task = boardTasks.get(i).getTask();
                if (task.getStatus().equals("InProgress")) {
                    tasks.add(task);
                }
            }
            return tasks;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Board findBoardByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM Board where name = :name");
            query.setParameter("name", name);
            return (Board) query.list().get(0);
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public void createBoard(Board board) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            session.save(board);
            transaction.commit();
        }catch (Exception e){
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void addTaskToBoard(BoardTask boardTask) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            session.save(boardTask);
            transaction.commit();
        }catch (Exception e){
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public BoardTask findByTask(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM BoardTask where task = :task");
            query.setParameter("task", task);
            return (BoardTask) query.list().get(0);
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public void deleteTaskFromBoard(BoardTask bt) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            session.delete(bt);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public List<Task> getTasksByPriority(Board board) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM BoardTask where board = :board");
            query.setParameter("board", board);
            List<BoardTask> boardTasks = (List<BoardTask>)query.list();
            ArrayList<Task> tasks = new ArrayList<Task>();
            for (int i = 0; i < boardTasks.size(); i++) {
                tasks.add(boardTasks.get(i).getTask());
            }
            tasks = (ArrayList<Task>) tasks.stream()
                    .sorted(Comparator.comparing(Task::getPriority))
                    .collect(Collectors.toList());
            return tasks;
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }
    }
}
