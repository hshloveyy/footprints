package com.mvc.footprints.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.impl.FileInfoDaoImpl;
import com.mvc.footprints.entity.TFileInfo;
//Spring Service Bean�ı�ʶ.
@Component
@Transactional
public class FileInfoService {
	private static Logger logger = LoggerFactory.getLogger(FileInfoService.class);
	@Autowired
	private FileInfoDaoImpl fileInfoDao;
	/**
	 * �����ļ���Ϣ
	 * @param sFileInfoBean
	 */
	public void saveFileInfo(TFileInfo ipFileInfo){
		fileInfoDao.save(ipFileInfo);
	}
	/**
	 * ɾ���ļ���Ϣ
	 */
	public void deleteFileInfo(TFileInfo ipFileInfo){
		fileInfoDao.delete(ipFileInfo);
	}
	/**
	 * 
	 */
	public TFileInfo getSFileInfoByisMd5(String md5){
		return fileInfoDao.findByMd5(md5);
	}
}
