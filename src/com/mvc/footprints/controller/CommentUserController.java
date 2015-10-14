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
import com.mvc.footprints.entity.TCommentUser;
import com.mvc.footprints.param.CityParam;
import com.mvc.footprints.param.CommentUserParam;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.ICommentUserService;
import com.mvc.footprints.service.IUserService;

@Controller
@RequestMapping("commentuser")
public class CommentUserController {
	
	@Autowired
	private ICommentUserService commentUserService;
	
	@Autowired
	private IUserService userService;
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/list")
	public String index(CityParam param){
		PagerResult result = new PagerResult();
		List<TCommentUser> list = (List<TCommentUser>) commentUserService.findAll(TCommentUser.class, param);
		result.setRows(list);
		
		int count = commentUserService.findAllCount(TCommentUser.class, param);
		result.setTotal(count);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(){

		return "/comment/commentAdd";
	}
	
	/**
	 * 发布用户评论
	 * @param commentUser
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/save")
	public String save(TCommentUser commentUser){
		JsonResult jsonResult = new JsonResult();
		try {
			Date now = new Date();
			if(StringUtils.isNotBlank(commentUser.getContent())){
				commentUser.setContent(new String(commentUser.getContent().getBytes("iso-8859-1"),"utf-8"));
			}
			commentUser.setCreateTime(DateFormatUtils.format(now, Constant.DATETIME));
			commentUser.setMillisecond(now.getTime()+"");
			commentUserService.save(commentUser);
			jsonResult.setCode(Constant.SUCCESS);
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
		TCommentUser comment = (TCommentUser) commentUserService.findById(id);
		return JSONObject.fromObject(comment).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TCommentUser comment){
		JsonResult jsonResult = new JsonResult();
		try {
			commentUserService.update(comment);
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
			commentUserService.delete(id);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 根据用户id获取评论列表
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("comments")
	public String getComment(CommentUserParam param){
		JsonResult jsonResult = new JsonResult();
		try {
			List<TCommentUser> commentUsers = commentUserService.findCommentUserByParam(param);
			
			for (TCommentUser tCommentUser : commentUsers) {
				PreUcenterMembers member = userService.findUserById(Integer.valueOf(tCommentUser.getFromUser()));
				if(member != null){
					String imageId = member.getPhotoImgId();
					tCommentUser.setUserImageId(imageId);
				}
			}
			jsonResult.setObj(commentUsers);
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
	 * 根据用户id获取评论列表条数
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("messagetome")
	public String messageToMe(String userId){
		JsonResult jsonResult = new JsonResult();
		try {
			int count = commentUserService.findUnreadMessageCountByUserId(userId);
			
			jsonResult.setObj(count);
			jsonResult.setMsg("有" + count + "条未读消息");
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
	 * 修改未读状态为已读
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updatereadflag")
	public String updateCommentUserFlag(String commentUserId){
		JsonResult jsonResult = new JsonResult();
		try {
			commentUserService.updateMessageByCommentUserId(commentUserId);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setMsg("状态修改为已读");
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
	 * 未读列表
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("unreadmessage")
	public String unreadmessage(String userId) {
		JsonResult jsonResult = new JsonResult();
		try {
			List<TCommentUser> commentUsers = commentUserService.findUnreadMessageByUserId(userId);
			for (TCommentUser tCommentUser : commentUsers) {
				PreUcenterMembers member = userService.findUserById(Integer.valueOf(tCommentUser.getFromUser()));
				if(member != null){
					String imageId = member.getPhotoImgId();
					tCommentUser.setUserImageId(imageId);
					tCommentUser.setFromUser(member.getUid().toString());
					tCommentUser.setFromUserName(member.getUsername());
				}
			}
			
			jsonResult.setObj(commentUsers);
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
	 * 消息标识为删除
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("removemessage")
	public String removeMessage(String commentUserId) {
		JsonResult jsonResult = new JsonResult();
		try {
			commentUserService.removeMessage(commentUserId);
			
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setMsg("删除成功");
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg(e.getMessage());
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		
		return JSONObject.fromObject(jsonResult).toString();
	}
}
