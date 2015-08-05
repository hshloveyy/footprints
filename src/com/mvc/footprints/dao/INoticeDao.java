package com.mvc.footprints.dao;

import com.mvc.footprints.entity.TNotice;


public interface INoticeDao extends BaseDao{

	TNotice findNotice();

}
