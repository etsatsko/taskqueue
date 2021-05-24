package com.webshop.simplewebapplication.database.Board;

import com.webshop.simplewebapplication.model.Board;
import com.webshop.simplewebapplication.model.BoardTask;
import com.webshop.simplewebapplication.model.Task;

import java.util.List;

public interface BoardDAO {
    List<Task> findAllInBoard(Board board);
    List<Task> getFinished(Board board);
    List<Task> getInProgress(Board board);
    Board findBoardByName(String name);
    void createBoard(Board board);
    void addTaskToBoard(BoardTask boardTask);
    BoardTask findByTask(Task task);
    void deleteTaskFromBoard(BoardTask bt);
    List<Task> getTasksByPriority(Board board);
}
