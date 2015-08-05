package com.mvc.footprints.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.entity.TFileInfo;
import com.mvc.footprints.resultmap.ResultJson;
import com.mvc.footprints.service.impl.FileInfoService;
import com.mvc.footprints.utils.ScaleImage;

/**
 * Servlet implementation class FileUploadServlet
 */
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired    
	private FileInfoService fileInfoService;
	static BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		fileInfoService= (FileInfoService) context.getBean("fileInfoService");
		DiskFileItemFactory factory = new DiskFileItemFactory();        //基于磁盘文件项目创建一个工厂对象
	    ServletFileUpload upload = new ServletFileUpload(factory);  //创建一个新的文件上传对象
	    
		String id = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
		String fileName = "";
		// 返回参数
//		String xml = "<uploadfile result='2'></uploadfile>";
		ResultJson jsonResult = new ResultJson();
		
		TFileInfo sFileInfoBean = new TFileInfo();
		sFileInfoBean.setEncryption(id);
		
		InputStream fis = null;
		byte[] bytes1 = null;
		//文件大小
		long size = 0;
			try {
				List<FileItem> items = upload.parseRequest(request);// 解析上传请求
				for (FileItem fileItem : items) {
					if("file".equals(fileItem.getFieldName())){
						fis = fileItem.getInputStream();
						size = fileItem.getSize();
						fileName = new String(fileItem.getName().getBytes("iso-8859-1"), "gbk");
					}
				}
				TFileInfo fileInfo = fileInfoService.getSFileInfoByisMd5(id);
				if(fileInfo != null){
					sFileInfoBean.setPoid(fileInfo.getPoid());
				}else{
					sFileInfoBean.setPoid(UUID.randomUUID().toString());
				}
				// 文件的保存路径
				SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd");// 设置日期格式
				String subPath = d.format(new Date());
				String savePath = Constant.FILE_PATH + subPath;
				sFileInfoBean.setSavePath(savePath);
				
				bytes1 = new byte[(int)size];

				File file = null;
				FileOutputStream fileOutputStream = null;
				// 进行文件夹的创建
				File f1 = new File(sFileInfoBean.getSavePath());
				f1.setWritable(true, false);
				if (!f1.exists()) {
					f1.mkdirs();
				}
				// 进行文件的创建并进行写入
				file = new File(sFileInfoBean.getSavePath() + "/"
						+ sFileInfoBean.getEncryption() + ".temp");
				file.setWritable(true, false);
				//文件是否存在
//				boolean isExists = file.exists();
				file.createNewFile();
				fileOutputStream = new FileOutputStream(file);
				while(fis.read(bytes1) >= 0){
					fileOutputStream.write(bytes1);
				}
				fileOutputStream.flush();
				fileOutputStream.close();
				// 返回1成功
//				xml = "<uploadfile result='1'></uploadfile>";
				jsonResult.setCode(1);
				jsonResult.setData(id);
				jsonResult.setResult(true);
				sFileInfoBean.setFileName(encoder.encode(fileName.getBytes("UTF-8")));
				if (fileName.lastIndexOf(".") >= 0) {
					sFileInfoBean.setFileExt(fileName.substring(fileName.lastIndexOf(".")));
				}else{
					sFileInfoBean.setFileExt(fileName);
				}
				
				//设置文件大小与文件实际上传大小
				sFileInfoBean.setExt3(""+size);
				sFileInfoBean.setExt4(size);
				// 完成状态
				sFileInfoBean.setStatus(3);
				
//				if(!isExists){//不存在则保存一条新纪录
				fileInfoService.saveFileInfo(sFileInfoBean);
//				}
				fileName = fileName.toLowerCase();
				if(fileName.indexOf("jpg") > 0 || fileName.indexOf("png") > 0 || fileName.indexOf("gif") > 0){
					//创建缩略图
					ScaleImage scaleImage = new ScaleImage();
					try {
						scaleImage
								.saveImageAsJpg(
										sFileInfoBean.getSavePath() + "/"
												+ sFileInfoBean.getEncryption() + ".temp",
										sFileInfoBean.getSavePath() + "/"
												+ sFileInfoBean.getEncryption() + "_1.temp",
										120, 120);
						//查询缩略图是否存在
						TFileInfo tumbFileInfo = fileInfoService.getSFileInfoByisMd5(id+"_1");
						if(tumbFileInfo != null){
							tumbFileInfo.setEncryption(sFileInfoBean.getEncryption()+"_1");
							tumbFileInfo.setFileName(sFileInfoBean.getFileName());
							tumbFileInfo.setFileExt(sFileInfoBean.getFileExt());
							tumbFileInfo.setSavePath(sFileInfoBean.getSavePath());
							tumbFileInfo.setSaveType(sFileInfoBean.getSaveType());
							FileInputStream inputStream = null;
							inputStream = new FileInputStream(new File(sFileInfoBean.getSavePath() + "/"
									+ tumbFileInfo.getEncryption() + ".temp"));
							tumbFileInfo.setExt3(inputStream.available()+"");
							tumbFileInfo.setExt4((long) inputStream.available());
							inputStream.close();
							
							fileInfoService.saveFileInfo(tumbFileInfo);
						}else{
							sFileInfoBean.setPoid(UUID.randomUUID().toString());
							sFileInfoBean.setEncryption(sFileInfoBean.getEncryption()+"_1");
							FileInputStream inputStream = null;
							inputStream = new FileInputStream(new File(sFileInfoBean.getSavePath() + "/"
									+ sFileInfoBean.getEncryption() + ".temp"));
							sFileInfoBean.setExt3(inputStream.available()+"");
							sFileInfoBean.setExt4((long) inputStream.available());
							inputStream.close();
							fileInfoService.saveFileInfo(sFileInfoBean);
						}
					} catch (Exception e) {
						e.printStackTrace();
						log("出现异常 - " + id, e);
					}
				}
				
			} catch (FileUploadException e) {
//				xml = "<uploadfile result='2'></uploadfile>";
				jsonResult.setCode(0);
				jsonResult.setResult(false);
				e.printStackTrace();
			}catch(Exception e){
//				xml = "<uploadfile result='2'></uploadfile>";
				jsonResult.setCode(0);
				jsonResult.setResult(false);
				e.printStackTrace();
			}
		out.print(JSONObject.fromObject(jsonResult).toString());
		out.flush();
		out.close();
//		log(xml + "   --------    " + id);
	}

}
