package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TCommentUser;


public interface ICommentUserDao extends BaseDao{

	List<TCommentUser> findCommentUserByCommentId(Integer commentId);

	int findCommentLikeCountByCommentId(Integer id);

	int findUnreadMessageByUserId(String userId);

}
