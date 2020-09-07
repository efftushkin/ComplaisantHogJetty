package com.efftushkin.ComplaisantHogJetty.repository;

import com.efftushkin.ComplaisantHogJetty.model.Task;

import java.util.List;

public interface TaskRepository {
    Task save(Task task);

    Task find(String id);

    List<Task> findAll();

    Task delete(String id);
}
