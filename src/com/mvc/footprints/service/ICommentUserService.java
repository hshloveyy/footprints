package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.entity.TCommentUser;
import com.mvc.footprints.param.CommentUserParam;



public interface ICommentUserService extends BaseService{

	List<TCommentUser> findCommentUserByParam(CommentUserParam param);

	int findUnreadMessageCountByUserId(String userId);

	void updateMessageByCommentUserId(String commentUserId);

	List<TCommentUser> findUnreadMessageByUserId(String userId);
}
