package com.spring.persistence.springdata;

import com.spring.persistence.IFiscal;
import com.spring.persistence.entity.Fiscal;
import com.spring.persistence.repositories.FiscalRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FiscalImpl implements IFiscal {

	public FiscalImpl(FiscalRepository fiscalRepository) {
		this.fiscalRepository = fiscalRepository;
	}

	private final FiscalRepository fiscalRepository;

    @Override
    public List<Fiscal> findAllFiscal() {
        return fiscalRepository.findAll();
    }

    @Override
    public Optional<Fiscal> findById(Long id) {
        return fiscalRepository.findById(id);
    }

    @Override
    public Fiscal save(Fiscal fiscal) {
        return fiscalRepository.save(fiscal);
    }
    
    @Override
    public List<Object[]> createXReport() {
        return fiscalRepository.createXReport();
    }
    
    @Override
    public List<Object[]> createZReport() {
        return fiscalRepository.createZReport();
    }
}
