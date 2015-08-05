package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.IAboutDao;
import com.mvc.footprints.dao.INoticeDao;
import com.mvc.footprints.dao.IProtocolDao;
import com.mvc.footprints.entity.TAbout;
import com.mvc.footprints.entity.TNotice;
import com.mvc.footprints.entity.TProtocol;
import com.mvc.footprints.service.ICommonService;
@Transactional
@Service("commonService")
public class CommonServiceImpl implements ICommonService {

	@Autowired
	private IAboutDao aboutDao;
	
	@Autowired
	private IProtocolDao protocolDao;
	
	@Autowired
	private INoticeDao noticeDao;
	

	@Override
	public void saveAbout(Object object) {
		aboutDao.save(object);
	}

	@Override
	public void saveNotice(Object object) {
		noticeDao.save(object);
	}
	
	@Override
	public void saveProtocol(Object object) {
		protocolDao.save(object);
	}
	
	@Override
	public TAbout findAbout(){
		return aboutDao.findAbout();
	}
	
	@Override
	public TProtocol findProtocol(){
		return protocolDao.findProtocol();
	}
	
	@Override
	public TNotice findNotice(){
		return noticeDao.findNotice();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TNotice> findAllNotice() {
		return (List<TNotice>) noticeDao.findAll(TNotice.class);
	}
	
	@Override
	public TNotice findNoticeById(String id) {
		return (TNotice) noticeDao.findById(TNotice.class, id);
	}
	
	@Override
	public void updateNotice(TNotice report) {
		noticeDao.update(report);
	}
}
