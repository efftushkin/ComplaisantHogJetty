package com.efftushkin.ComplaisantHogJetty.repository;

import com.efftushkin.ComplaisantHogJetty.model.Worker;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class JsonWorkerRepository implements WorkerRepository {
    private Map<String, Worker> workerIdToWorker = new HashMap<>();
    private String PATH_TO_JSON_WORKERS = "d:/_JavaProjects/IdeaProjects/ComplaisantHogJetty/src/main/resources/workers.json";

    @Override
    public void save(Worker worker) {
        workerIdToWorker.put(worker.getId(), worker);

        saveToFile();
    }

    @Override
    public Worker find(String id) {
        return workerIdToWorker.get(id);
    }

    @Override
    public List<Worker> findAll() {
        return workerIdToWorker.values().stream().collect(Collectors.toList());
    }

    @Override
    public Worker delete(String id) {
        Worker deletedWorker = workerIdToWorker.remove(id);
        saveToFile();
        return deletedWorker;
    }

    private void saveToFile() {
        ObjectMapper objectMapper = new ObjectMapper();

        try (OutputStream outputStream = new FileOutputStream(PATH_TO_JSON_WORKERS)) {
            objectMapper.writeValue(outputStream, workerIdToWorker.values().toArray());
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @PostConstruct
    private void readFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();

        Worker[] workers;

        try (InputStream inputStream = new FileInputStream(PATH_TO_JSON_WORKERS)) {
            workers = objectMapper.readValue(inputStream, Worker[].class);

            for (Worker worker : workers) {
                workerIdToWorker.put(worker.getId(), worker);
            }

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
