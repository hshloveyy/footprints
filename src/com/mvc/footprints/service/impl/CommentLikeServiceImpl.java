package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.ICommentLikeDao;
import com.mvc.footprints.entity.TCommentLike;
import com.mvc.footprints.entity.TProvince;
import com.mvc.footprints.entity.TShopImage;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.ICommentLikeService;
@Transactional
@Service("commentLikeService")
public class CommentLikeServiceImpl implements ICommentLikeService {

	@Autowired
	private ICommentLikeDao commentLikeDao;
	
	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return commentLikeDao.findAll(clazz, param);
	}

	@Override
	public void save(Object object) {
		commentLikeDao.save(object);
	}

	@Override
	public void delete(String id) {
		commentLikeDao.delete(commentLikeDao.findById(TProvince.class, id));
	}

	@Override
	public void update(Object object) {
		commentLikeDao.update(object);
	}

	@Override
	public Object findById(String id) {
		return commentLikeDao.findById(TShopImage.class, id);
	}

	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return commentLikeDao.findAllCount(clazz, param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TCommentLike> findAll(Class<?> clazz) {
		return (List<TCommentLike>) commentLikeDao.findAll(clazz);
	}
}
