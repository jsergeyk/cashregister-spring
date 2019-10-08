package com.spring.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

import org.springframework.stereotype.Service;

import com.spring.persistence.*;
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
}
