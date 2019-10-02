package com.spring.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.persistence.IChec;
import com.spring.persistence.entity.Chec;

import lombok.AllArgsConstructor;

/**
 * @author SergeyK
 */
@Service
@Transactional
@AllArgsConstructor
public class CheckService {

	private final IChec iChec;
	
    @Transactional(readOnly = true)
    public List<Chec> findAllChec() {
        return iChec.findAllChec();
    }

	/**
     * @param page информация о пейджинации и сортировке
     * @return модель и шаблон со списком заявок
     */
    @Transactional(readOnly = true)
	public Object findAll(Pageable page) {
        return iChec.findAllChec();
	}
}
