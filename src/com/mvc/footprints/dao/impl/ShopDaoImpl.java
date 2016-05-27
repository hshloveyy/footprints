package com.mvc.footprints.dao.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
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
import com.mvc.footprints.dao.IShopDao;
import com.mvc.footprints.entity.TShopInfo;
import com.mvc.footprints.entity.TSubCategory;
import com.mvc.footprints.param.ShopParam;
			
@Repository("shopDao")
@SuppressWarnings("unchecked")
public class ShopDaoImpl extends BaseDaoImpl implements IShopDao {

	@Override
	public List<TShopInfo> findShopByParams(final ShopParam param) {
		return hibernateTemplate.execute(new HibernateCallback<List<TShopInfo>>() {
			@Override
			public List<TShopInfo> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(TShopInfo.class);
				if(StringUtils.isNotBlank(param.getName())){
					criteria.add(Restrictions.like("name", "%"+param.getName()+"%"));
				}
//				criteria.setMaxResults(param.getRows());             // 最大显示记录数  
//				criteria.setFirstResult((param.getPage() - 1) * param.getRows()); // 从第几条开始  
				criteria.addOrder(Order.asc("sort"));
				criteria.addOrder(Order.asc("id"));
				return criteria.list();
			}
		});
	}
	
	@Override
	public List<TShopInfo> isbbsShop(final ShopParam param) {
		return hibernateTemplate.execute(new HibernateCallback<List<TShopInfo>>() {
			@Override
			public List<TShopInfo> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(TShopInfo.class);
				if(StringUtils.isNotBlank(param.getType()) && "1".equals(param.getType())){
					criteria.add(Restrictions.eq("isDiscount", Integer.valueOf(param.getType())));
				}
				if(null != param.getCity() && !"0".equals(param.getCity())){
					criteria.add(Restrictions.eq("city", param.getCity().toString()));
				}
				if(StringUtils.isNotBlank(param.getSubClass())){
					TSubCategory subCategory = (TSubCategory)session.createQuery("from TSubCategory where id = " + param.getSubClass()).uniqueResult();
					if(subCategory != null){
						if(1 == subCategory.getStatus()){
							criteria.add(Restrictions.eq("subclass", param.getSubClass()));
						}else{
							criteria.add(Restrictions.eq("class_", subCategory.getCategoryId() + ""));
						}
					}
				}
				if(StringUtils.isNotBlank(param.getName())){
					try {
						criteria.add(Restrictions.like("name", "%" + new String(param.getName().getBytes("iso-8859-1"),"utf-8") + "%"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				criteria.addOrder(Order.asc("sort"));
				criteria.addOrder(Order.asc("id"));
//				System.out.println(param.getPage() + "-" + param.getRows());
				criteria.setMaxResults(param.getRows());             // 最大显示记录数  
				criteria.setFirstResult((param.getPage() - 1) * param.getRows()); // 从第几条开始  
				return criteria.list();
			}
		});
	}
	
	@Override
	public List<TShopInfo> findAll(final ShopParam param) {
		return hibernateTemplate.execute(new HibernateCallback<List<TShopInfo>>() {
			@Override
			public List<TShopInfo> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(TShopInfo.class);
//				System.err.println(param);
				if(StringUtils.isNotBlank(param.getName())){
					criteria.add(Restrictions.like("name", "%"+param.getName()+"%"));
				}
				if(null != param.getCity() && param.getCity() != 0){
					criteria.add(Restrictions.eq("city", param.getCity().toString()));
				}
				if(null != param.getProvince() && param.getProvince() != 0){
					criteria.add(Restrictions.eq("province", param.getProvince().toString()));
				}
				criteria.setMaxResults(param.getRows());             // 最大显示记录数  
				criteria.setFirstResult((param.getPage() - 1) * param.getRows()); // 从第几条开始  
				criteria.addOrder(Order.asc("sort"));
				criteria.addOrder(Order.asc("id"));
				return criteria.list();
			}
		});
	}
	
	@Override
	public int findAllCount(final ShopParam param) {
		return hibernateTemplate.execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				return ((BigInteger)arg0.createSQLQuery("select count(1) from t_shop_info").uniqueResult()).intValue();
			}
		});
//		return hibernateTemplate.execute(new HibernateCallback<List<TShopInfo>>() {
//			@Override
//			public List<TShopInfo> doInHibernate(Session session)
//					throws HibernateException, SQLException {
//				Criteria criteria = session.createCriteria(TShopInfo.class);
//				System.err.println(param);
//				if(StringUtils.isNotBlank(param.getName())){
//					criteria.add(Restrictions.like("name", "%"+param.getName()+"%"));
//				}
//				if(null != param.getCity() && param.getCity() != 0){
//					criteria.add(Restrictions.eq("city", param.getCity().toString()));
//				}
//				if(null != param.getProvince() && param.getProvince() != 0){
//					criteria.add(Restrictions.eq("province", param.getProvince().toString()));
//				}
//				criteria.setMaxResults(param.getRows());             // 最大显示记录数  
//				criteria.setFirstResult((param.getPage() - 1) * param.getRows()); // 从第几条开始  
//				criteria.addOrder(Order.asc("sort"));
//				criteria.addOrder(Order.asc("id"));
//				return criteria.list();
//			}
//		}).size();
	}
	
	@Override
	public void deleteByEncryption(final String id) {
		TShopInfo shopInfo = getHibernateTemplate().execute(new HibernateCallback<TShopInfo>() {
			@Override
			public TShopInfo doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(TShopInfo.class);
				criteria.add(Restrictions.eq("uploadPics", id));
				return (TShopInfo)criteria.uniqueResult();
			}
		});
		
		shopInfo.setUploadPics("");
		getHibernateTemplate().update(shopInfo);
	}
}
