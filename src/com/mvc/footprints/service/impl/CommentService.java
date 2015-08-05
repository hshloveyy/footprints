package com.mvc.footprints.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.dao.ICommentDao;
import com.mvc.footprints.dao.ICommentImageDao;
import com.mvc.footprints.dao.ICommentLikeDao;
import com.mvc.footprints.dao.ICommentUserDao;
import com.mvc.footprints.dao.IPreUcenterMemberDao;
import com.mvc.footprints.dao.ISignDao;
import com.mvc.footprints.entity.PreUcenterMembers;
import com.mvc.footprints.entity.TComment;
import com.mvc.footprints.entity.TCommentImage;
import com.mvc.footprints.entity.TCommentLike;
import com.mvc.footprints.entity.TCommentUser;
import com.mvc.footprints.entity.TSign;
import com.mvc.footprints.param.CommentParam;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.ICommentService;
@Transactional
@Service("commentService")
public class CommentService implements ICommentService {

	@Autowired
	private ICommentDao commentDao;
	
	@Autowired
	private ISignDao signDao;
	
	@Autowired
	private ICommentImageDao commentImageDao;
	
	@Autowired
	private ICommentUserDao commentUserDao;
	
	@Autowired
	private ICommentLikeDao commentLikeDao;
	
	@Autowired
	private IPreUcenterMemberDao preUcenterMemberDao;
	
	@Override
	public Object findById(String id) {
		return commentDao.findById(TComment.class, id);
	}

	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return commentDao.findAll(TComment.class, param);
	}

	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return commentDao.findAllCount(TComment.class, param);
	}

	@Override
	public void save(Object object) {
		commentDao.save(object);
	}

	@Override
	public void delete(String id) {
		commentDao.delete(id);
	}

	@Override
	public void update(Object object) {
		commentDao.update(object);
	}

	@Override
	public List<TComment> findCommentByShopId(Integer id) {
		return commentDao.findCommentByShopId(id);
	}

	@Override
	public TSign getUserSignInfo(CommentParam param) {
		return signDao.findSignByUserIdAndShopId(param.getUserId(), param.getShopId());
	}

	@Override
	public void addSign(Integer userId, Integer shopId) {
		TSign sign = signDao.findSignByUserIdAndShopId(userId, shopId);
		if(sign == null){
			sign = new TSign();
			sign.setUserId(userId);
			sign.setShopId(shopId);
			
			Date now = new Date();
			sign.setCreateTime(DateFormatUtils.format(now, Constant.DATETIME));
			sign.setLastTime(DateFormatUtils.format(now, Constant.DATETIME));
			sign.setSignCount(1);
			signDao.save(sign);
		}else{
			Integer count = sign.getSignCount();
			sign.setSignCount((count == null?0:count) + 1);
			sign.setLastTime(DateFormatUtils.format(new Date(), Constant.DATETIME));
			signDao.update(sign);
		}
	}

	@Override
	public void saveImage(TCommentImage commentImage) {
		commentImageDao.save(commentImage);
	}
	
	@Override
	public List<TComment> findCommentByParam(CommentParam param) {
		List<TComment> list = commentDao.findByParam(param);
		
		for (TComment tComment : list) {
			List<TCommentImage> images = commentImageDao.findImageByCommentId(tComment.getId());
			tComment.setImages(images);
			
			List<TCommentUser> commentUserList = commentUserDao.findCommentUserByCommentId(tComment.getId());
			if(commentUserList != null){
				tComment.setCommentCount(commentUserList.size());
			}
			
			PreUcenterMembers member = preUcenterMemberDao.findByUserId(tComment.getUserId());
			if(member != null){
				tComment.setUserName(member.getUsername());
			
				String imageId = member.getPhotoImgId();
				tComment.setUserImageId(imageId);
				
				String sex = member.getSex();
				tComment.setUserSex(sex);
			}
		}
		return list;
	}
	
	@Override
	public List<TComment> findCommentByUserId(Integer userId) {
		List<TComment> list = commentDao.findCommentByUserId(userId);
		
		for (TComment tComment : list) {
			List<TCommentImage> images = commentImageDao.findImageByCommentId(tComment.getId());
			tComment.setImages(images);
			
			int likeCount = commentLikeDao.findCommentLikeCountByCommentId(tComment.getId());
			tComment.setLikeCount(likeCount);
			
			int commentCount = commentUserDao.findCommentLikeCountByCommentId(tComment.getId());
			tComment.setCommentCount(commentCount);
			
			PreUcenterMembers member = preUcenterMemberDao.findByUserId(tComment.getUserId());
			if(member != null){
				tComment.setUserName(member.getUsername());
			}
		}
		return list;
	}
	
	@Override
	public TCommentLike findLikeByParam(CommentParam param) {
		return commentLikeDao.findCommentLikeByCommentIdAndUserId(param.getCommentId(), param.getUserId());
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TComment> findAll(Class<?> clazz) {
		return (List<TComment>) commentDao.findAll(clazz);
	}
	
	@Override
	public long findLikeCountByCommentId(CommentParam param) {
		return commentLikeDao.findLikeCountByCommentId(param.getCommentId());
	}
}
