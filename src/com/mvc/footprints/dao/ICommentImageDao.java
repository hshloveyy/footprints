package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TCommentImage;



public interface ICommentImageDao extends BaseDao{

	List<TCommentImage> findImageByCommentId(Integer id);

}
