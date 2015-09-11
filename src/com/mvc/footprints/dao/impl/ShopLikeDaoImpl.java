package com.mvc.footprints.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IShopLikeDao;
import com.mvc.footprints.entity.TShopLike;
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
	public List<TShopLike> ranking(final ShopParam param) {
		List<TShopLike> list = new ArrayList<TShopLike>();
		List<Object[]> l = getHibernateTemplate().execute(
				new HibernateCallback<List<Object[]>>() {
					@SuppressWarnings("unchecked")
					@Override
					public List<Object[]> doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						if (Constant.RANKING_TYPE_YEAR.equals(param.getType())) {
							return arg0
									.createSQLQuery(
											"select "
													+ getColumn()
													+ ",sum(like_count) like_count "
													+ "from t_shop_like,t_shop_info where t_shop_like.shop_id = t_shop_info.id "
													+ "and year(t_shop_like.last_time) = year(now()) "
													+ "and shop_id in (select id from t_shop_info where 1=1 "
													+ getCityParam(param)
													+ getSubClassParam(param)
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
									+ getSubClassParam(param)
									+ ") "
									+ "group by shop_id "
									+ "order by like_count desc";
							System.out.println(sql);
							return arg0
									.createSQLQuery(sql)
									.addEntity(TShopLike.class)
									.setMaxResults(Constant.RANKING_ROWS)
									.list();
						}
					}
				});
		
		for (Object[] objects : l) {
			TShopLike shopLike = new TShopLike((Integer) objects[1],
					(Integer) objects[3], (Integer) objects[7],
					objects[4].toString(), objects[5].toString(),
					objects[6].toString());
			list.add(shopLike);
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

	private String getSubClassParam(ShopParam param) {
		if (param.getSubClass() != null) {
			return " and subClass = " + param.getSubClass();
		} else {
			return "";
		}
	}

	private String getColumn() {
		return "t_shop_like.id,t_shop_like.shop_id," +
				"t_shop_like.shop_name,t_shop_like.user_id," +
				"t_shop_like.create_time,t_shop_like.last_time," +
				"t_shop_like.millsecond,t_shop_like.is_click";
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
