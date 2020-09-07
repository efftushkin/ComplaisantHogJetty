package com.efftushkin.ComplaisantHogJetty.service;

import com.efftushkin.ComplaisantHogJetty.model.Task;
import com.efftushkin.ComplaisantHogJetty.model.Worker;

public interface AssignService {
    Task assignTask(Worker worker, Task task);
}
