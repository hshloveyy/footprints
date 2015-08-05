package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TComment;
import com.mvc.footprints.param.CommentParam;


public interface ICommentDao extends BaseDao{

	List<TComment> findCommentByShopId(Integer id);
	
	List<TComment> findCommentByUserId(Integer id);

	List<TComment> findByShopId(Integer shopId);
	
	List<TComment> findByParam(CommentParam param);

}
