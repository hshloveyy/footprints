package com.mvc.footprints.controller;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.entity.PreUcenterMembers;
import com.mvc.footprints.entity.TComment;
import com.mvc.footprints.entity.TCommentImage;
import com.mvc.footprints.entity.TCommentLike;
import com.mvc.footprints.entity.TShopInfo;
import com.mvc.footprints.entity.TSign;
import com.mvc.footprints.param.CityParam;
import com.mvc.footprints.param.CommentParam;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.ICommentLikeService;
import com.mvc.footprints.service.ICommentService;
import com.mvc.footprints.service.IShopService;
import com.mvc.footprints.service.IUserService;
import com.mvc.footprints.utils.DateUtils;

@Controller
@RequestMapping("comment")
public class CommentController {

	private static final int CLICKED = 1;
	private static final int UNCLICK = 0;
	
	@Autowired
	private ICommentService commentService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IShopService shopService;
	
	@Autowired
	private ICommentLikeService commentLikeService;

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/list")
	public String index(CommentParam param){
		PagerResult result = new PagerResult();
		List<TComment> list = (List<TComment>) commentService.findAll(TComment.class, param);
		for (TComment tComment : list) {
			PreUcenterMembers members = userService.findUserById(tComment.getUserId());
			if(members != null){
				tComment.setUserName(members.getUsername());
			}
			TShopInfo shop = (TShopInfo)shopService.findById(tComment.getShopId().toString());
			if(shop != null){
				tComment.setShopName(shop.getName());
			}
		}
		result.setRows(list);
		
		int count = commentService.findAllCount(TComment.class, param);
		result.setTotal(count);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(){

		return "/comment/commentAdd";
	}
	
	/**
	 * 发布足迹
	 * @param comment
	 * @param sign
	 * @param pics
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/save")
	public String save(TComment comment, String sign, String pics){
		JsonResult jsonResult = new JsonResult();
		try {
			Date now = new Date();
			if(StringUtils.isNotBlank(comment.getCommentContent())){
				comment.setCommentContent(new String(comment.getCommentContent().getBytes("iso-8859-1"),"utf-8"));
			}
			comment.setCreateTime(DateFormatUtils.format(now, "yyyy-MM-dd HH:mm:ss"));
			comment.setCreateMillsecond(now.getTime()+"");
			commentService.save(comment);
			jsonResult.setCode(Constant.SUCCESS);
			
			if(Constant.COMMENT_SIGN_YES.equals(sign)){
				commentService.addSign(comment.getUserId(), comment.getShopId());
				//成功签到
				jsonResult.setMsg(Constant.COMMENT_SIGN_YES);
			}
			
			if(pics != null){
				String[] images = pics.split(",");
				for (String imageId : images) {
					if(StringUtils.isNotBlank(imageId)){
						TCommentImage commentImage = new TCommentImage();
						commentImage.setCommentId(comment.getId());
						commentImage.setImageId(imageId);
						commentImage.setType("1");
						
						commentService.saveImage(commentImage);
					}
				}
			}
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toForm")
	public String toForm(){
		return "/comment/commentEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/toEdit")
	public String toEdit(String id){
		TComment comment = (TComment) commentService.findById(id);
		return JSONObject.fromObject(comment).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TComment comment){
		JsonResult jsonResult = new JsonResult();
		try {
			commentService.update(comment);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public String delete(String id){
		JsonResult jsonResult = new JsonResult();
		try {
			commentService.delete(id);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 获取签到信息
	 * @param param 
	 * 			userId
	 * 			shopId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("signinfo")
	public String getUserSignInfo(CommentParam param){
		JsonResult jsonResult = new JsonResult();
		try {
			TSign sign = commentService.getUserSignInfo(param);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setObj(sign);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 根据商铺id获取足迹列表
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("comments")
	public String getComment(CommentParam param){
		JsonResult jsonResult = new JsonResult();
		try {
			param.setRows(Constant.SHOP_LIST_ROWS);
			List<TComment> comments = commentService.findCommentByParam(param);
			
			for (TComment tComment : comments) {
				TShopInfo shopInfo = (TShopInfo) shopService.findById(tComment.getShopId() + "");
				
				if(shopInfo != null){
					tComment.setShopName(shopInfo.getName());
				}
				
				PreUcenterMembers member = userService.findUserById(tComment.getUserId());
				if(member != null){
					tComment.setUserImageId(member.getPhotoImgId());
				}
				
				Long count = commentService.findLikeCountByCommentId(new CommentParam(tComment.getId()));
				tComment.setLikeCount(count.intValue());
			}
			jsonResult.setObj(comments);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg(e.getMessage());
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	
	/**
	 * 根据用户id跟足迹id获取点赞情况信息
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("commentlike")
	public String commentLike(CommentParam param){
		JsonResult jsonResult = new JsonResult();
		try {
			TCommentLike commentLike = commentService.findLikeByParam(param);
			if(commentLike != null){
				long sumLike = commentService.findLikeCountByCommentId(param);
				commentLike.setSumLike((int)sumLike);
				commentLike.setLikeCount(Long.valueOf(sumLike).intValue());
				jsonResult.setObj(commentLike);
			}
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setMsg(e.getMessage());
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 用户对商铺点赞
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("like")
	public String likeComment(CommentParam param, int like){
		JsonResult jsonResult = new JsonResult();
		try {
			//查询用户之前有没有对该店铺点赞的记录
			TCommentLike commentLike = commentService.findLikeByParam(param);
			Date now = new Date();
			
			if(commentLike == null){//用户第一次对该店铺点赞
				commentLike = new TCommentLike();
				commentLike.setUserId(param.getUserId());
				commentLike.setCommentId(param.getCommentId());
				commentLike.setCreateTime(DateFormatUtils.format(now, Constant.DATETIME));
				commentLike.setLastTime(DateFormatUtils.format(now, Constant.DATETIME));
				commentLike.setMillsecond(now.getTime()+"");
				commentLike.setIsClick(CLICKED);
			}else{//用户有对该店铺点过赞
				if(like == -1 || !DateUtils.isSameMonth(now, new Date(Long.valueOf(commentLike.getMillsecond()))) || commentLike.getIsClick() == 1){
					commentLike.setLastTime(DateFormatUtils.format(now, Constant.DATETIME));
					commentLike.setMillsecond(now.getTime()+"");
					if(like == -1){
						commentLike.setIsClick(CLICKED);
					}else{
						commentLike.setIsClick(UNCLICK);
					}
				}else{
					commentLike.setIsClick(UNCLICK);
					jsonResult.setCode(Constant.LIKED);
					jsonResult.setResult(false);
					return JSONObject.fromObject(jsonResult).toString();
				}
			}
			Integer sourceLike = commentLike.getLikeCount();
			if(sourceLike == null){
				sourceLike = 0;
			}
			if(sourceLike + like < 0){
				commentLike.setLikeCount(0);
			}else{
				commentLike.setLikeCount(sourceLike + like);
			}
			
			commentLikeService.save(commentLike);
			jsonResult.setObj(commentLike);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 获取用户所有足迹列表
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("usercomments")
	public String getUserComment(CommentParam param){
		JsonResult jsonResult = new JsonResult();
		try {
			param.setRows(Constant.SHOP_LIST_ROWS);
			List<TComment> comments = commentService.findCommentByUserId(param.getUserId());
			for (TComment tComment : comments) {
				TShopInfo shopInfo = (TShopInfo) shopService.findById(tComment.getShopId() + "");
				
				if(shopInfo != null){
					tComment.setShopName(shopInfo.getName());
				}
				
				PreUcenterMembers member = userService.findUserById(tComment.getUserId());
				if(member != null){
					tComment.setUserImageId(member.getPhotoImgId());
				}
				
				Long count = commentService.findLikeCountByCommentId(new CommentParam(tComment.getId()));
				tComment.setLikeCount(count.intValue());
			}
			jsonResult.setObj(comments);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		
		return JSONObject.fromObject(jsonResult).toString();
	}
}
