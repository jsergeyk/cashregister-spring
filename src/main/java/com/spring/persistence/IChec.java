package com.spring.persistence;

import java.util.List;
import java.util.Optional;

import com.spring.persistence.entity.Chec;

public interface IChec {

    List<Chec> findAllChec();

    Optional<Chec> findById(Long id);

    Chec save(Chec chec);
}
