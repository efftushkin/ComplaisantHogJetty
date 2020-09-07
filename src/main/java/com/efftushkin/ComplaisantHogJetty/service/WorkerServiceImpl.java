package com.efftushkin.ComplaisantHogJetty.service;

import com.efftushkin.ComplaisantHogJetty.model.Worker;
import com.efftushkin.ComplaisantHogJetty.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService {
    private IdGenerator idGenerator;
    private WorkerRepository workerRepository;

    @Autowired
    public WorkerServiceImpl(IdGenerator idGenerator, WorkerRepository workerRepository) {
        this.idGenerator = idGenerator;
        this.workerRepository = workerRepository;
    }

    @Override
    public Worker createWorker(String firstName, String lastName) {
        String id = idGenerator.generateId();

        Worker worker = new Worker(id, firstName, lastName);

        workerRepository.save(worker);
        return worker;
    }

    @Override
    public Worker findWorkerByFirstNameAndLastName(String firstName, String lastName) {
        List<Worker> workers = workerRepository.findAll();
        Optional<Worker> worker = workers
                .stream()
                .filter(wor -> wor.getFirstName().equals(firstName)
                        && wor.getLastName().equals(lastName))
                .findFirst();

        return worker.orElse(null);

    }

    @Override
    public List<Worker> findAll() {
        return workerRepository.findAll();
    }
}
