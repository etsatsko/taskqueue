package com.webshop.simplewebapplication.Service;

import com.webshop.simplewebapplication.database.Board.BoardDAOHib;
import com.webshop.simplewebapplication.model.Board;
import com.webshop.simplewebapplication.model.BoardTask;
import com.webshop.simplewebapplication.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardDAOHib boardDao = new BoardDAOHib();

    public List<Task> findAllInBoard(Board board) {
        return boardDao.findAllInBoard(board);
    }

    public List<Task> getFinished(Board board) {
        return boardDao.getFinished(board);
    }

    public List<Task> getInProgress(Board board) {
        return boardDao.getInProgress(board);
    }

    public void createBoard(Board board) {
        boardDao.createBoard(board);
    }

    public void addTaskToBoard(BoardTask bt) {
        boardDao.addTaskToBoard(bt);
    }

    public Board findBoardByName(String name) {
        return boardDao.findBoardByName(name);
    }

    public BoardTask findByTask(Task task) {
        return boardDao.findByTask(task);
    }

    public void deleteTaskFromBoard(BoardTask bt) {
        boardDao.deleteTaskFromBoard(bt);
    }

    public List<Task> getTasksByPriority(Board board) {
        return boardDao.getTasksByPriority(board);
    }
}
