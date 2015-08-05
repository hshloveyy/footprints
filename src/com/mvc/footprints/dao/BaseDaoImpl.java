package com.mvc.footprints.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.spi.LoggerFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.mvc.footprints.entity.TProvince;
import com.mvc.footprints.param.PagerParam;

@Repository("baseDao")
public class BaseDaoImpl implements BaseDao{
	
	Logger log = Logger.getLogger(this.getClass().getSimpleName());
	protected HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	public List<Object> createQuery(final String queryString, final Object[] objects) {
		return (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Query query = session.createQuery(queryString);
						if(objects != null){
							for (int i = 0; i < objects.length; i++) {
								query.setParameter(i, objects[i]);
							}
						}
						List<Object> rows = query.list();
						return rows;
					}
				});
	}
	
	public Object save(final Object model) {
		return  getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						session.save(model);
						return null;
					}
				});
	}
	public void update(final Object model) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				session.update(model);
				return null;
			}
		});
	}
	public void delete(final Object model) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				session.delete(model);
				return null;
			}
		});
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<?> findAll(final Class clazz, final PagerParam param) {
//		String queryString = "from " + clazz.getSimpleName() + " limte " + (param.getPage() - 1) + "," + param.getRows();
		@SuppressWarnings("unchecked")
		List<TProvince> list = getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					int first = (param.getPage() - 1) * param.getRows();
					int rows = param.getRows();
					if(rows == 0){
						rows = 10;
					}
					Query query = session.createQuery("from " + clazz.getSimpleName() + " order by sort").setFirstResult(first).setMaxResults(rows);
				return query.list();
			}
		});
		return list;
	}
	
	@Override
	public List<?> findAll(final Class clazz, final PagerParam param, final String sort) {
//		String queryString = "from " + clazz.getSimpleName() + " limte " + (param.getPage() - 1) + "," + param.getRows();
		@SuppressWarnings("unchecked")
		List<TProvince> list = getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					int first = (param.getPage() - 1) * param.getRows();
					int rows = param.getRows();
					if(rows == 0){
						rows = 10;
					}
					String sortString = "";
					if(StringUtils.isNotBlank(sort)){
						sortString = " order by " + sort;
					}
					Query query = session.createQuery("from " + clazz.getSimpleName() + sortString).setFirstResult(first).setMaxResults(rows);
				return query.list();
			}
		});
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> findAll(final Class<?> clazz, final PagerParam param, final boolean isSort) {
		List<TProvince> list = getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					int first = (param.getPage() - 1) * param.getRows();
					Query query = session.createQuery("from " + clazz.getSimpleName()).setFirstResult(first).setMaxResults(10);
				return query.list();
			}
		});
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object findById(Class clazz, String id) {
		log.info(clazz.getSimpleName() + " - " + id);
		if(StringUtils.isNotBlank(id)){
			return getHibernateTemplate().get(clazz, Integer.valueOf(id));//("from " + clazz.getSimpleName() + " where id = " + id);
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findAllCount(final Class<?> clazz, final PagerParam param) {
		List<TProvince> list = getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					Query query = session.createQuery("from " + clazz.getSimpleName());
				return query.list();
			}
		});
		return list.size();
	}

	@Override
	public List<?> findAll(Class<?> clazz) {
		return getHibernateTemplate().find("from " + clazz.getSimpleName());
	}
}
