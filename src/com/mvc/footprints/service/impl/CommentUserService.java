package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.ICommentUserDao;
import com.mvc.footprints.dao.IPreUcenterMemberDao;
import com.mvc.footprints.entity.PreUcenterMembers;
import com.mvc.footprints.entity.TComment;
import com.mvc.footprints.entity.TCommentUser;
import com.mvc.footprints.param.CommentUserParam;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.ICommentUserService;
@Transactional
@Service("commentUserService")
public class CommentUserService implements ICommentUserService {

	@Autowired
	private ICommentUserDao commentUserDao;
	
	@Autowired
	private IPreUcenterMemberDao preUcenterMemberDao;
	
	@Override
	public Object findById(String id) {
		return commentUserDao.findById(TComment.class, id);
	}

	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return commentUserDao.findAll(TComment.class, param);
	}

	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return commentUserDao.findAllCount(TComment.class, param);
	}

	@Override
	public void save(Object object) {
		commentUserDao.save(object);
	}

	@Override
	public void delete(String id) {
		commentUserDao.delete(id);
	}

	@Override
	public void update(Object object) {
		commentUserDao.update(object);
	}
	
	@Override
	public List<TCommentUser> findCommentUserByParam(CommentUserParam param) {
		List<TCommentUser> commentUsers = commentUserDao.findCommentUserByCommentId(param.getCommentId());
		for (TCommentUser tCommentUser : commentUsers) {
			PreUcenterMembers member = preUcenterMemberDao.findByUserId(Integer.valueOf(tCommentUser.getFromUser()));
			if(member != null){
				String imageId = member.getPhotoImgId();
				tCommentUser.setUserImageId(imageId);
				
				String fromUserName = member.getUsername();
				tCommentUser.setFromUserName(fromUserName);
			}
		}
		return commentUsers;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TCommentUser> findAll(Class<?> clazz) {
		return (List<TCommentUser>) commentUserDao.findAll(clazz);
	}
}
