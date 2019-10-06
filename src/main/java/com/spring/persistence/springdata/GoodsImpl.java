package com.spring.persistence.springdata;

import com.spring.persistence.IGoods;
import com.spring.persistence.entity.Goods;
import com.spring.persistence.repositories.GoodsRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GoodsImpl implements IGoods {

	private final GoodsRepository goodsRepository;
	
	public GoodsImpl(GoodsRepository goodsRepository) {
		this.goodsRepository = goodsRepository;
	}

    @Override
    public List<Goods> findAllGoods() {
        return goodsRepository.findAll();
    }

    @Override
    public Optional<Goods> findByCode(int code) {
        return goodsRepository.findByCode(code);
    }
    
    @Override
    public Optional<Goods> findById(Long id) {
        return goodsRepository.findById(id);
    }

	@Override
	public Optional<Goods> findByName(String name) {
		return goodsRepository.findByName(name);
	}
	
    @Override
    public Goods save(Goods goods) {
        return goodsRepository.save(goods);
    }

	@Override
	public List<Goods> findAll() {		
		return goodsRepository.findAll();
	}

	@Override
	public Page<Goods> findAll(Pageable p) {
		return goodsRepository.findAll(p);
	}

	@Override
	public Integer reduceQuant(Long id, double quant) {
		return goodsRepository.reduceQuant(id, quant);
		
	}
}
