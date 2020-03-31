package com.example.springbootdemo.persistence;

import com.example.springbootdemo.domain.Sequence;
import org.springframework.stereotype.Repository;


@Repository
public interface MBSequenceDAO {
    Sequence getSequence(Sequence sequence);
    void updateSequence(Sequence sequence);
}
