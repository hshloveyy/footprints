package com.mvc.footprints.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IShopLikeDao;
import com.mvc.footprints.entity.TShopLike;
import com.mvc.footprints.entity.TSubCategory;
import com.mvc.footprints.param.ShopParam;

@Repository("shopLikeDao")
public class ShopLikeDaoImpl extends BaseDaoImpl implements IShopLikeDao {

	@Override
	public TShopLike findShopLikeByUserIdAndShopId(final Integer userId,
			final Integer shopId) {
		return getHibernateTemplate().execute(
				new HibernateCallback<TShopLike>() {
					@Override
					public TShopLike doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						return (TShopLike) arg0.createQuery(
								"from TShopLike where userId = " + userId
										+ " and shopId = " + shopId)
								.uniqueResult();
					}
				});
	}

	@Override
//	public List<Map<String, Object>> ranking(final ShopParam param) {
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		Map<String, List<Object[]>> l = getHibernateTemplate().execute(
//				new HibernateCallback<Map<String, List<Object[]>>>() {
//					@SuppressWarnings("unchecked")
//					@Override
//					public Map<String, List<Object[]>> doInHibernate(Session arg0)
//							throws HibernateException, SQLException {
//						boolean flag = true;
//						
//						//是否选择全部
//						TSubCategory subCategory = (TSubCategory)arg0.createQuery("from TSubCategory where id = " + param.getSubClass()).uniqueResult();
//						if(subCategory != null){
//							if(0 == subCategory.getStatus()){
//								flag = false;
//							}
//						}else{
//							flag = false;
//						}
//						
//						List<Object[]> allList = arg0
//									.createSQLQuery(
//											"select "
//													+ getColumn()
//													+ ",sum(like_count) like_count "
//													+ "from t_shop_like,t_shop_info where t_shop_like.shop_id = t_shop_info.id "
//													+ "and year(t_shop_like.last_time) = year(now()) "
//													+ "and shop_id in (select id from t_shop_info where 1=1 "
//													+ getCityParam(param)
//													+ getSubClassParam(param, flag)
//													+ ") "
//													+ "group by shop_id "
//													+ "order by like_count desc")
//									.setMaxResults(Constant.RANKING_ROWS)
//									.list();
//					
//						String sql = "select "
//									+ getColumn()
//									+ ",sum(like_count) like_count "
//									+ "from t_shop_like,t_shop_info where t_shop_like.shop_id = t_shop_info.id "
//									+ "and month(t_shop_like.last_time) = month(now()) "
//									+ "and shop_id in (select id from t_shop_info where 1=1 "
//									+ getCityParam(param)
//									+ getSubClassParam(param, flag)
//									+ ") "
//									+ "group by shop_id "
//									+ "order by like_count desc";
//						List<Object[]> monthList = arg0
//									.createSQLQuery(sql)
//									.setMaxResults(Constant.RANKING_ROWS)
//									.list();
//						
//						Map<String, List<Object[]>> listMap = new HashMap<String, List<Object[]>>();
//						listMap.put("all", allList);
//						listMap.put("month", monthList);
//						
//						return listMap;
//					}
//			});
//		
//		
//		List<Object[]> all = l.get("all");
//		List<Object[]> month = l.get("month");
//		
//		for (Object[] objects : l) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("shopId", objects[0]);
//			map.put("likeCount", objects[1]);
//			list.add(map);
//		}
//		return list;
//	}
//	
	public List<Map<String, Object>> ranking(final ShopParam param) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Object[]> l = getHibernateTemplate().execute(
				new HibernateCallback<List<Object[]>>() {
					@SuppressWarnings("unchecked")
					@Override
					public List<Object[]> doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						boolean flag = true;
						
						//是否选择全部
						TSubCategory subCategory = (TSubCategory)arg0.createQuery("from TSubCategory where id = " + param.getSubClass()).uniqueResult();
						if(subCategory != null){
							if(0 == subCategory.getStatus()){
								flag = false;
								param.setCategory(subCategory.getCategoryId() + "");
							}
						}else{
							flag = false;
						}
						
						if (Constant.RANKING_TYPE_YEAR.equals(param.getType())) {
							return arg0
									.createSQLQuery(
											"select "
													+ getColumn()
													+ ",sum(like_count) like_count "
													+ "from t_shop_like,t_shop_info where t_shop_like.shop_id = t_shop_info.id "
//													+ "and year(t_shop_like.last_time) = year(now()) "
													+ "and shop_id in (select id from t_shop_info where 1=1 "
													+ getCityParam(param)
													+ getSubClassParam(param, flag)
													+ ") "
													+ "group by shop_id "
													+ "order by like_count desc")
									.setMaxResults(Constant.RANKING_ROWS)
									.list();
						} else {
							String sql = "select "
									+ getColumn()
									+ ",sum(like_count) like_count "
									+ "from t_shop_like,t_shop_info where t_shop_like.shop_id = t_shop_info.id "
									+ "and month(t_shop_like.last_time) = month(now()) "
									+ "and shop_id in (select id from t_shop_info where 1=1 "
									+ getCityParam(param)
									+ getSubClassParam(param, flag)
									+ ") "
									+ "group by shop_id "
									+ "order by like_count desc";
							return arg0
									.createSQLQuery(sql)
									.setMaxResults(Constant.RANKING_ROWS)
									.list();
						}
					}
				});
		
		for (Object[] objects : l) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("shopId", objects[0]);
			map.put("likeCount", objects[1]);
			list.add(map);
		}
		return list;
	}

	private String getCityParam(ShopParam param) {
		if (param.getCity() != null) {
			return " and city = " + param.getCity();
		} else {
			return "";
		}
	}

	private String getSubClassParam(ShopParam param, boolean flag) {
		if(flag){
			if (param.getSubClass() != null) {
				return " and subClass = " + param.getSubClass();
			} else {
				return "";
			}
		}else{
			if(StringUtils.isNotBlank(param.getCategory())){
				return " and class = " + param.getCategory();
			}
			return "";
		}
	}

	private String getColumn() {
//		return "t_shop_like.id,t_shop_like.shop_id," +
//				"t_shop_like.shop_name,t_shop_like.user_id," +
//				"t_shop_like.create_time,t_shop_like.last_time," +
//				"t_shop_like.millsecond,t_shop_like.is_click";
		return "min(t_shop_info.id) shop_id";
	}
	
	@Override
	public Long findLikeCountByShopId(final Integer shopId) {
		return getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				return (Long) arg0.createQuery("select sum(likeCount) from TShopLike where shopId = " + shopId).uniqueResult();
			}
		});
	}
}
