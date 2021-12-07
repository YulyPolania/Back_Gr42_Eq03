package com.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SequenceGeneratorService {

  public Long generateLongSequence(List<Long> list) {
    Long i = 1L;
    while (list.contains(i)) {
      i++;
    }
    return i;
  }

  public Integer generateIntegerSequence(List<Integer> list) {
    Integer i = 1;
    while (list.contains(i)) {
      i++;
    }
    return i;
  }
}