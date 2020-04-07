package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.Dairy;
import com.example.springbootdemo.persistence.MBLogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private MBLogDAO mbLogDAO;
    @Override
    public void insertLog(Dairy dairy) {
        mbLogDAO.insertDiary(dairy);
    }
}
