package com.spring.service;

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.spring.persistence.*;
import com.spring.persistence.entity.Fiscal;
import com.spring.service.Report.Detail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс сервиса для отчетов
 * @author SergeyK
 */
@Service
@Slf4j
@AllArgsConstructor
public class ReportService {
	
	private final IFiscal fiscalDAO;
	private final IChec checkDAO;

	public Report getDataXReport() {
		Report rep = new Report();
		List<Detail> detail = rep.getDetail();
		List<Object[]> xReport = fiscalDAO.createXReport();
		for (Object[] field : xReport) {
			rep.setPrinttime((Timestamp) field[0]);
			rep.setCountCancelCheck((BigInteger)field[1]);
			rep.setCountCheck((BigDecimal)field[2]);
			rep.setSumTotal((Double)field[6]);
			rep.setSumNdsTotal((Double)field[7]);
			detail.add(rep.new Detail((Integer)field[3],
					(Double)field[4], 
					(Double)field[5]));
		}
		log.info("Х-отчет сформирован.");
		return rep;
	}
	
	@Transactional
	public Report getDataZReport() {
		Report rep = null;
		List<Object[]> zReport = fiscalDAO.createZReport();
		for (Object[] field : zReport) {
			Fiscal fiscal = new Fiscal();
			fiscal.setTotal((Double)field[6]);
			checkDAO.register(new Date());
			fiscalDAO.save(fiscal);
			Long repNumber = fiscal.getId();
			rep = new Report().new Builder()
				.addNumber(repNumber)
	            .addPrinttime((Timestamp) field[0])
	            .addCountCheck((BigDecimal)field[2])
	            .addCountCancelCheck((BigInteger)field[1])
	            .addTotalA(field[3] != null ? (Double)field[3] : 0.0)
	            .addTotalB(field[5] != null ? (Double)field[5] : 0.0)
	            .addTotalC(field[7] != null ? (Double)field[7] : 0.0)
	            .addNdsTotalA(field[4] != null ? (Double)field[4] : 0.0)
	            .addNdsTotalB(field[6] != null ? (Double)field[6] : 0.0)
	            .addNdsTotalC(field[8] != null ? (Double)field[8] : 0.0)
	            .addSumTotal(field[9] != null ? (Double)field[9] : 0.0)
	            .addSumNdsTotal(field[10] != null ? (Double)field[10] : 0.0)
	            .build();
		}
		return rep;
	}
}
