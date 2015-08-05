package com.mvc.footprints.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.ICommentLikeDao;
import com.mvc.footprints.entity.TCommentLike;

@Repository("commentLikeDao")
public class CommentLikeDaoImpl extends BaseDaoImpl implements ICommentLikeDao {

	@Override
	public TCommentLike findCommentLikeByCommentIdAndUserId(final Integer commentId,
			final Integer userId) {
		return getHibernateTemplate().execute(
				new HibernateCallback<TCommentLike>() {
					@Override
					public TCommentLike doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						return (TCommentLike) arg0.createQuery(
								"from TCommentLike where userId = " + userId
										+ " and commentId = " + commentId)
								.uniqueResult();
					}
				});
	}
	
	@Override
	public int findCommentLikeCountByCommentId(Integer id) {
		return getHibernateTemplate().find("from TCommentLike where commentId = " + id).size();
	}
	
	/**
	 * 根据足迹id获取足迹点赞数
	 */
	@Override
	public long findLikeCountByCommentId(final Integer commentId) {
		return getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				Object obj = arg0.createQuery("select sum(likeCount) from TCommentLike where commentId = " + commentId).uniqueResult();
				if(obj != null){
					return (Long)obj;
				}else{
					return 0l;
				}
			}
		});
	}
}
