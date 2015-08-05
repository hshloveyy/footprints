package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.entity.TComment;
import com.mvc.footprints.entity.TCommentImage;
import com.mvc.footprints.entity.TCommentLike;
import com.mvc.footprints.entity.TSign;
import com.mvc.footprints.param.CommentParam;


public interface ICommentService extends BaseService{

	List<TComment> findCommentByShopId(Integer id);
	
	List<TComment> findCommentByUserId(Integer userId);

	TSign getUserSignInfo(CommentParam param);

	void addSign(Integer userId, Integer shopId);

	void saveImage(TCommentImage commentImage);

//	List<TComment> findCommentByParam(ShopParam param);

	List<TComment> findCommentByParam(CommentParam param);

	TCommentLike findLikeByParam(CommentParam param);

	long findLikeCountByCommentId(CommentParam param);
}
