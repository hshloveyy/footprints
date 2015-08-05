package com.mvc.footprints.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mvc.footprints.entity.TFileInfo;
import com.mvc.footprints.service.impl.FileInfoService;
import com.mvc.footprints.utils.FileDownloadUtil;
import com.mvc.footprints.utils.ScaleImage;
@SuppressWarnings("serial")
public class FileDownloadServlet extends HttpServlet {
	@Autowired    
	private FileInfoService fileInfoService;
    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String fileId=req.getParameter("fileId");
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getSession().getServletContext());
		fileInfoService= (FileInfoService) context.getBean("fileInfoService");
		FileDownloadUtil fileDownloadUtil=new FileDownloadUtil();
		
		TFileInfo sFileInfoBean=fileInfoService.getSFileInfoByisMd5(fileId);
		if(sFileInfoBean != null){//存在
			resp=fileDownloadUtil.download(sFileInfoBean.getSavePath(),fileId, resp,sFileInfoBean.getFileName()+sFileInfoBean.getFileExt());
		}else{//不存在，判断是不是查询缩略图
			if(fileId.indexOf("_1") != -1){//查询缩略图
				//查询是否有原图
				TFileInfo fileInfo = fileInfoService.getSFileInfoByisMd5(fileId.substring(0, fileId.indexOf("_1")));
				if(fileInfo != null){
					//生成缩略图
					try {
						ScaleImage scaleImage = new ScaleImage();
						scaleImage
						.saveImageAsJpg(
								fileInfo.getSavePath() + "/"
										+ fileInfo.getEncryption() + ".temp",
										fileInfo.getSavePath() + "/"
										+ fileInfo.getEncryption() + "_1.temp",
								120, 120);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					//返回缩略图
					resp = fileDownloadUtil.download(fileInfo.getSavePath(),
							fileId, resp,
							fileInfo.getFileName() + fileInfo.getFileExt());
				}else{
					resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}
			}else{//返回错误
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
//			PrintWriter out = resp.getWriter();
//			out.print("<string><downloadfile result='0' ></downloadfile></string>");
//			out.flush();
//			out.close();
		}
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	doPost(req, resp);
    }
}
