package com.example.springbootdemo.persistence;

import com.example.springbootdemo.domain.Dairy;
import org.springframework.stereotype.Repository;

@Repository
public interface MBLogDAO {
    void insertDiary(Dairy dairy);
}
