package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.IReportDao;
import com.mvc.footprints.entity.TCity;
import com.mvc.footprints.entity.TReport;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.IReportService;
@Transactional
@Service("reportService")
public class ReportServiceImpl implements IReportService {

	@Autowired
	private IReportDao reportDao;
	
	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return reportDao.findAll(clazz, param);
	}

	@Override
	public void save(Object object) {
		reportDao.save(object);
	}

	@Override
	public void delete(String id) {
		reportDao.delete(reportDao.findById(TCity.class, id));
	}

	@Override
	public void update(Object object) {
		reportDao.update(object);
	}

	@Override
	public Object findById(String id) {
		return reportDao.findById(TCity.class, id);
	}
	
	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return reportDao.findAllCount(clazz, param);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TReport> findAll(Class<?> clazz) {
		return (List<TReport>) reportDao.findAll(clazz);
	}
}
