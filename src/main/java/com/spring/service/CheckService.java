package com.spring.service;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.persistence.*;
import com.spring.persistence.entity.*;

import lombok.AllArgsConstructor;

/**
 * Класс сервиса для чеков
 * @author SergeyK
 */
@Service
@Transactional
@AllArgsConstructor
public class CheckService {

	private final IChec checDAO;
	private final ICheckspec checkspecDAO;
	private final IGoods goodsDAO;

	/**
	 * Сформировать спецификацию по найденному коду товара или наименованию товара 
	 * @param code код товара
	 * @param name наименование товара
	 * @param quant количество товара
	 * @param nds ставка НДС
	 * @return спецификация чека
	 */
    @Transactional(readOnly = true)
	public Checkspec addCheckSpec(Integer code, String name, Double quant, Integer nds) {
		Optional<Goods> existsGoods = Optional.empty();
		if (code != null) {
			existsGoods = goodsDAO.findByCode(code);
		} else if (name != null && !name.isEmpty()) {					
			existsGoods = goodsDAO.findByName(name);
		}					
		if (existsGoods.isPresent()) {
			Checkspec spec = new Checkspec();
			spec.setGoods(existsGoods.get());
			spec.setQuant(quant);
			spec.setPrice(existsGoods.get().getPrice());						
			spec.setTotal(BigDecimal.valueOf(quant).multiply(BigDecimal.valueOf(spec.getPrice())).doubleValue());
			spec.setNds(nds != null ? nds : 0);
			spec.setNdstotal(BigDecimal.valueOf(spec.getTotal()).multiply(BigDecimal.valueOf(spec.getNds())).divide(new BigDecimal(100)).doubleValue());
			spec.setCanceled(0);
			return spec;
		}
		return null;
	}

	/**
	 * Добавить чек со спецификациями (в транзакции)
	 * @param user пользователь, который создал чек
	 * @param checkspecs список спецификаций чека
	 * @return кол-во добавленных записей
	 */
	public void addCheck(User user, List<Checkspec> checkspecs) {

		Chec check = new Chec();		
		check.setCreator(user);		
		double total = checkspecs.stream().mapToDouble(o -> o.getTotal()).sum();
		check.setTotal(total);
		check.setCheckspecCollection(checkspecs);
		check.setCanceled(0);
		checkspecs.stream().forEach(o -> {
			o.setCheck(check);
			Goods goods = o.getGoods();
			if (goods != null) {
				goodsDAO.reduceQuant(goods.getId(), o.getQuant());				
			}
		});
		checDAO.save(check);
		//checkspecDAO.saveAll(checkspecs);
		//throw new RuntimeException();
	}

	/**
	 * Найти чек по номеру
	 * @param checkId номер чека
	 * @return 
	 */
	@Transactional(readOnly = true)
	public Optional<Chec> findById(Long checkId) {
		return checDAO.findById(checkId);		
	}

	/**
	 * Отменить спецификацию чека и пересчитать сумму чека
	 * @param checkspec спецификация чека
	 */
	public void cancelCheckSpec(List<Checkspec> checkspecs, Integer count) {
    	if (checkspecs != null && checkspecs.size() >= count && count > 0) {
			Checkspec checkspec = checkspecs.get(count -1);
			checkspec.setCanceled(1);
			checkspecDAO.save(checkspec);
			double total = checkspecs.stream()
					.filter(spec -> spec.getCanceled() == 0)
					.mapToDouble(o -> o.getTotal()).sum();
			Chec check = checkspec.getCheck();
			check.setTotal(total);
			checDAO.save(check);
    	}
	}

	/**
	 * Отменить чек
	 * @param check чек
	 */
	public void cancelCheckSpec(Chec check) {
		if (check != null) {
			check.setCanceled(1);
			checDAO.save(check);
		}
	}
}
