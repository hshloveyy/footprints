package com.mvc.footprints.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
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
	
	@Override
	public int findUnreadMessageCountByUserId(final String userId) {
		return getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				return ((BigInteger) arg0
						.createSQLQuery(
								"SELECT count(id) FROM t_comment_user t where t.comment_id in "
								+ "(select id from t_comment a where a.user_id = ?) and t.read_flag = 0")
						.setString(0, userId).uniqueResult()).intValue();
			}
		});
	}
	
	@Override
	public List<TCommentUser> findUnreadMessageByUserId(final String userId) {
		return getHibernateTemplate().execute(new HibernateCallback<List<TCommentUser>>() {
			@Override
			public List<TCommentUser> doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				List<Object[]> list = arg0
						.createSQLQuery("SELECT * FROM t_comment_user t where t.comment_id in "
								+ "(select id from t_comment a where a.user_id = ?) and t.read_flag = 0")
						.setString(0, userId).list();
				List<TCommentUser> resultList = new ArrayList<TCommentUser>();
				for (Object[] objArr : list) {
					TCommentUser commentUser = new TCommentUser();
					commentUser.setId(objArr[0] != null ? Integer.valueOf(objArr[0].toString()) : 0);
					commentUser.setCommentId(objArr[1] != null ? Integer.valueOf(objArr[1].toString()) : 0);
					commentUser.setContent(objArr[2] != null ? objArr[2].toString() : "");
					commentUser.setFromUser(objArr[3] != null ? objArr[3].toString() : "");
					commentUser.setCreateTime(objArr[4] != null ? objArr[4].toString() : "");
					commentUser.setMillisecond(objArr[5] != null ? objArr[5].toString() : "");
					commentUser.setReadFlag(objArr[6] != null ? Integer.valueOf(objArr[6].toString()) : 0);
					resultList.add(commentUser);
				}
				
				return resultList;
			}
		});
	}
	
	@Override
	public void removeMessage(String commentUserId) {
		TCommentUser commentUser = getHibernateTemplate().load(TCommentUser.class, Integer.valueOf(commentUserId));
		commentUser.setReadFlag(2);
		getHibernateTemplate().saveOrUpdate(commentUser);
	}
}
