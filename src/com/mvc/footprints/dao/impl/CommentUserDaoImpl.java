package com.mvc.footprints.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.ICommentUserDao;
import com.mvc.footprints.entity.TCommentUser;

@SuppressWarnings("unchecked")
@Repository("commentUserDao")
public class CommentUserDaoImpl extends BaseDaoImpl implements ICommentUserDao {

	
	@Override
	public List<TCommentUser> findCommentUserByCommentId(Integer commentId) {
		return getHibernateTemplate().find("from TCommentUser where commentId = " + commentId + " order by id desc");
	}
	
	@Override
	public int findCommentLikeCountByCommentId(Integer id) {
		return getHibernateTemplate().find("from TCommentUser where commentId = " + id).size();
	}
}
