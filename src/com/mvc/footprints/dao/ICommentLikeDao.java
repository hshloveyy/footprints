package com.mvc.footprints.dao;

import com.mvc.footprints.entity.TCommentLike;


public interface ICommentLikeDao extends BaseDao{

	TCommentLike findCommentLikeByCommentIdAndUserId(Integer commentId,
			Integer userId);

	int findCommentLikeCountByCommentId(Integer id);

	long findLikeCountByCommentId(Integer commentId);

}