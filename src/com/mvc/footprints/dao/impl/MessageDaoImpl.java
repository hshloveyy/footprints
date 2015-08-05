package com.mvc.footprints.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IMessageDao;
import com.mvc.footprints.entity.TMessage;
import com.mvc.footprints.param.CityParam;
			
@Repository("messageDao")
public class MessageDaoImpl extends BaseDaoImpl implements IMessageDao {
	
}
