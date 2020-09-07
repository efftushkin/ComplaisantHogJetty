package com.efftushkin.ComplaisantHogJetty.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGeneratorImpl implements IdGenerator {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}