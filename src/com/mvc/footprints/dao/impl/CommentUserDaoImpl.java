package com.mvc.footprints.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
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
	public int findUnreadMessageByUserId(final String userId) {
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
}
