package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.entity.TAbout;
import com.mvc.footprints.entity.TDict;
import com.mvc.footprints.entity.TNotice;
import com.mvc.footprints.entity.TProtocol;



public interface ICommonService{

	void saveAbout(Object object);

	void saveNotice(Object object);

	void saveProtocol(Object object);

	TAbout findAbout();

	TProtocol findProtocol();

	TNotice findNotice();

	List<TNotice> findAllNotice();

	TNotice findNoticeById(String id);

	void updateNotice(TNotice report);

	TDict findByKey(String key);
}
