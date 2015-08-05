package com.mvc.footprints.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IAdvertDao;
import com.mvc.footprints.entity.TAdvert;
import com.mvc.footprints.param.AdvertParam;
			
@Repository("advertDao")
public class AdvertDaoImpl extends BaseDaoImpl implements IAdvertDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TAdvert> findByParam(final AdvertParam param) {
		return hibernateTemplate.execute(new HibernateCallback<List<TAdvert>>() {
			@Override
			public List<TAdvert> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(TAdvert.class);
				if(null != param.getCity() && !"0".equals(param.getCity())){
					criteria.add(Restrictions.eq("cityId", Integer.valueOf(param.getCity().toString())));
				}
				criteria.addOrder(Order.asc("sort"));
				
				if(param.getRows() < 1){
					param.setRows(Constant.SHOP_LIST_ROWS);
				}
				criteria.setMaxResults(param.getRows());             // 最大显示记录数  
				criteria.setFirstResult((param.getPage() - 1) * param.getRows()); // 从第几条开始  
				return criteria.list();
			}
		});
	}
}
