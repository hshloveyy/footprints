package com.mvc.footprints.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IYellowPageInfoDao;
import com.mvc.footprints.entity.TShopInfo;
import com.mvc.footprints.entity.TSubKind;
import com.mvc.footprints.entity.TYellowPageInfo;
import com.mvc.footprints.param.YellowPageParam;
			
@Repository("yellowPageInfoDao")
public class YellowPageInfoDaoImpl extends BaseDaoImpl implements IYellowPageInfoDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TYellowPageInfo> findByParam(final YellowPageParam param) {
		return getHibernateTemplate().execute(new HibernateCallback<List<TYellowPageInfo>>() {
			@Override
			public List<TYellowPageInfo> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(TYellowPageInfo.class);
				if(null != param.getCity()){
					criteria.add(Restrictions.eq("city", Integer.valueOf(param.getCity())));
				}
				if(StringUtils.isNotBlank(param.getSubKindId())){
					TSubKind subKind = (TSubKind)session.createQuery("from TSubKind where id = " + param.getSubKindId()).uniqueResult();
					if(subKind != null){
						if(1 == subKind.getStatus()){
							criteria.add(Restrictions.eq("subKindId", Integer.valueOf(param.getSubKindId())));
						}else{
							criteria.add(Restrictions.eq("kindId", subKind.getParentId()));
						}
					}
				}
				criteria.addOrder(Order.asc("sort"));
				criteria.setMaxResults(param.getRows());             // 最大显示记录数  
				criteria.setFirstResult((param.getPage() - 1) * param.getRows()); // 从第几条开始  
				return criteria.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TYellowPageInfo> findByMark(String[] marks) {
		String hql = "from TYellowPageInfo where 1=1 ";
		for (String mark : marks) {
			hql += " or mark like '%" + mark + "%' ";
		}
		
		return getHibernateTemplate().find(hql);
	}
}
