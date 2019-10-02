package com.spring.persistence.springdata;

import com.spring.persistence.ICheckspec;
import com.spring.persistence.entity.Checkspec;
import com.spring.persistence.repositories.CheckspecRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CheckspecImpl implements ICheckspec {

	private final CheckspecRepository checkspecRepository;
	
	public CheckspecImpl(CheckspecRepository checkspecRepository) {
		this.checkspecRepository = checkspecRepository;
	}

    @Override
    public List<Checkspec> findAllCheckspec() {
        return checkspecRepository.findAll();
    }

    @Override
    public Optional<Checkspec> findById(Long id) {
        return checkspecRepository.findById(id);
    }

    @Override
    public Checkspec save(Checkspec checkspec) {
        return checkspecRepository.save(checkspec);
    }
}
