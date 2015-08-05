package com.mvc.footprints.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.ICommentDao;
import com.mvc.footprints.entity.TComment;
import com.mvc.footprints.param.CommentParam;
import com.mvc.footprints.param.PagerParam;

@SuppressWarnings("unchecked")
@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoImpl implements ICommentDao {

	
	public List<TComment> findCommentByShopId(Integer id){
		return getHibernateTemplate().find("from TComment where shopId = " + id);
	}
	
	public List<TComment> findCommentByUserId(Integer id){
		return getHibernateTemplate().find("from TComment where userId = " + id);
	}
	
	@Override
	public List<?> findAll(@SuppressWarnings("rawtypes") Class clazz, PagerParam param) {
		CommentParam commentParam = (CommentParam)param;
		int first = (commentParam.getPage() - 1) * commentParam.getRows();
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		if(StringUtils.isNotBlank(commentParam.getCommentContent())){
			criteria.add(Restrictions.like("commentContent", "%" + commentParam.getCommentContent() + "%"));
		}
		criteria.addOrder(Order.asc("createTime"));
		return getHibernateTemplate().findByCriteria(criteria, first, commentParam.getRows());
	}
	
	@Override
	public List<TComment> findByShopId(Integer shopId) {
		return getHibernateTemplate().find("from TComment where shopId = " + shopId + " order by id desc");
	}
	
	@Override
	public List<TComment> findByParam(CommentParam param) {
		int first = (param.getPage() - 1) * param.getRows();
		DetachedCriteria criteria = DetachedCriteria.forClass(TComment.class);
		criteria.add(Restrictions.eq("shopId", param.getShopId()));
		criteria.addOrder(Order.desc("id"));
		return getHibernateTemplate().findByCriteria(criteria, first, param.getRows());
	}
}
