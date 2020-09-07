package com.efftushkin.ComplaisantHogJetty.repository;

import com.efftushkin.ComplaisantHogJetty.model.Task;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MemoryTaskRepository implements TaskRepository {
    private Map<String, Task> taskIdToTask = new HashMap<>();

    @Override
    public Task save(Task task) {
        taskIdToTask.put(task.getId(), task);
        return task;
    }

    @Override
    public Task find(String id) {
        return taskIdToTask.get(id);
    }

    @Override
    public List<Task> findAll() {
        return taskIdToTask.values().stream().collect(Collectors.toList());
    }

    @Override
    public Task delete(String id) {
        return taskIdToTask.remove(id);
    }
}
