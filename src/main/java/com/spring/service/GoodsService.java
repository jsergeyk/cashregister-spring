package com.spring.service;

import com.spring.persistence.IGoods;
import com.spring.persistence.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class GoodsService {

	private final IGoods goodsDAO;

    public Optional<Goods> findByCode(int code) {
        return goodsDAO.findByCode(code);
    }
   
    public Optional<Goods> findByName(String name) {
        return goodsDAO.findByName(name);
    }
    
    public Optional<Goods> findById(Long id) {
        return goodsDAO.findById(id);
    }

    /**
     * 
     * @param currentPage отображаемая страница
     * @param pageSize кол-во записей на страницу
     * @return Page<Goods> страница
     */
    public Page<Goods> view(int currentPage, int pageSize) {
    	PageRequest sortedByCode = PageRequest.of(currentPage - 1, pageSize, Sort.by("code"));
    	return goodsDAO.findAll(sortedByCode);
	}
    
    public void save(Goods goods) {
    	goodsDAO.save(goods);
    }

	/**
	 * Добавить товар в базу данных (если товара нет в наличии)
	 * @param code код товара
	 * @param name наименование
	 * @param quant количество товара
	 * @param price цена товара
	 * @param measure единица измерения
	 * @param comments комментарии к товару
	 * @return Id добавленно записи
	 */
	public Long addGoods(Integer code, String name, Double quant, Double price, String measure, String comments) {
		Goods goods = new Goods();
		goods.setCode(code);
		goods.setName(name);
		goods.setQuant(quant);
		goods.setPrice(price);
		goods.setMeasure(measure);
		goods.setComments(comments);
		Optional<Goods> existsGood = goodsDAO.findByCode(code);
		if (existsGood.isPresent()) {
			log.info("Товар с кодом " + code + " уже существует");
			return -1L;
		} else {
			existsGood = goodsDAO.findByName(name);		
			if (existsGood.isPresent()) {
				log.info("Товар " + name + " уже существует");
				return -2L;
			} else {
				log.info("Товар добавлен");
				goodsDAO.save(goods);
				return goods.getId();
			}
		}
	}

	/**
	 * Изменить товар по коду товара
	 * @param changecode код товара
	 * @param changequant количество
	 * @param changeprice цена
	 */
	public void changeGoods(Integer code, Double newQuant, Double newPrice) {
		Optional<Goods> goods = goodsDAO.findByCode(code);
		if (goods.isPresent()) {
			if (newQuant != null) {
				goods.get().setQuant(newQuant);
			}
			if (newPrice != null) {
				goods.get().setPrice(newPrice);
			}
			goodsDAO.save(goods.get());
		}		
	}
}
