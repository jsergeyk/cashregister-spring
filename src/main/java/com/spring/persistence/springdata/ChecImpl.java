package com.spring.persistence.springdata;

import com.spring.persistence.IChec;
import com.spring.persistence.entity.Chec;
import com.spring.persistence.repositories.ChecRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChecImpl implements IChec {

	private final ChecRepository checRepository;
	
	public ChecImpl(ChecRepository checRepository) {
		this.checRepository = checRepository;
	}

    @Override
    public List<Chec> findAllChec() {
        return checRepository.findAll();
    }

    @Override
    public Optional<Chec> findById(Long id) {
        return checRepository.findById(id);
    }

    @Override
    public Chec save(Chec chec) {
        return checRepository.save(chec);
    }
}
