package com.webshop.simplewebapplication.model;

import javax.persistence.*;

@Entity
@Table(name = "board_task")
public class BoardTask {

    public BoardTask() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Task task;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Board board;

    public BoardTask(int id, Task task, Board board) {
        this.id = id;
        this.task = task;
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public Task getTask() {
        return task;
    }
}