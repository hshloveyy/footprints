package com.mvc.footprints.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.ICommentImageDao;
import com.mvc.footprints.entity.TCommentImage;

@SuppressWarnings("unchecked")
@Repository("commentImageDao")
public class CommentImageDaoImpl extends BaseDaoImpl implements ICommentImageDao {

	
	public List<TCommentImage> findImageByCommentId(Integer id){
		return getHibernateTemplate().find("from TCommentImage where commentId = " + id);
	}
	
}
