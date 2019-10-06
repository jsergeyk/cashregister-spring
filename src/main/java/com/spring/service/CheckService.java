package com.spring.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.persistence.IChec;
import com.spring.persistence.ICheckspec;
import com.spring.persistence.IGoods;
import com.spring.persistence.entity.Chec;
import com.spring.persistence.entity.Checkspec;
import com.spring.persistence.entity.Goods;
import com.spring.persistence.entity.User;

import lombok.AllArgsConstructor;


/**
 * Класс сервиса для чеков
 * @author SergeyK
 */
@Service
@Transactional
@AllArgsConstructor
public class CheckService {

	//private final static Logger logger = LoggerFactory.getLogger(CheckService.class);
	private final IChec checDAO;
	private final ICheckspec checkspecDAO;
	private final IGoods goodsDAO;
	
    @Transactional(readOnly = true)
    public List<Chec> findAllChec() {
        return checDAO.findAllChec();
    }

	/**
     * @param page информация о пейджинации и сортировке
     * @return модель и шаблон со списком заявок
     */
    @Transactional(readOnly = true)
	public Object findAll(Pageable page) {
        return checDAO.findAllChec();
	}

	/**
	 * Сформировать спецификацию по найденному коду товара или наименованию товара 
	 * @param xcode код товара
	 * @param xname наименование товара
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
			//spec.setname(existsGoods.get().getName());
			//spec.setXcode(existsGoods.get().getCode());
			spec.setQuant(quant);
			spec.setPrice(existsGoods.get().getPrice());						
			spec.setTotal(BigDecimal.valueOf(quant).multiply(BigDecimal.valueOf(spec.getPrice())).doubleValue());
			spec.setNds(nds != null ? nds : 0);
			spec.setNdstotal(BigDecimal.valueOf(spec.getTotal()).multiply(BigDecimal.valueOf(spec.getNds())).divide(new BigDecimal(100)).doubleValue());
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
		checkspecs.stream().forEach(o -> {
			o.setCheck(check);
			Goods goods = o.getGoods();
			if (goods != null) {
				goodsDAO.reduceQuant(goods.getId(), o.getQuant());				
			}
		});
		checDAO.save(check);
		checkspecDAO.saveAll(checkspecs);
	}
}
