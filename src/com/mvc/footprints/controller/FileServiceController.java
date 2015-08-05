package com.mvc.footprints.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.entity.TFileInfo;
import com.mvc.footprints.resultmap.ResultJson;
import com.mvc.footprints.service.impl.FileInfoService;
import com.mvc.footprints.utils.ScaleImage;



@Controller
public class FileServiceController {
	@Autowired
	private FileInfoService fileInfoService;
	static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	static BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	private static Logger logger = LoggerFactory.getLogger(FileServiceController.class);
	
	@ResponseBody
	@RequestMapping("fileService/test")
	public String test(){
	    
		return "abcdefghijklmnopqrstuvwxyz";		
	}
	
	@ResponseBody
	@RequestMapping("fileService/Upload")
	public String upload(MultipartFile file){
	    
		String id = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
		String fileName = "";
		// 返回参数
		ResultJson jsonResult = new ResultJson();
		
		TFileInfo sFileInfoBean = new TFileInfo();
		sFileInfoBean.setEncryption(id);
		
		InputStream fis = null;
		byte[] bytes1 = null;
		//文件大小
		long size = 0;
			try {
				fis = file.getInputStream();
				size = file.getSize();
				fileName = new String(file.getName().getBytes("iso-8859-1"), "gbk");

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

				File file1 = null;
				FileOutputStream fileOutputStream = null;
				// 进行文件夹的创建
				File f1 = new File(sFileInfoBean.getSavePath());
				f1.setWritable(true, false);
				if (!f1.exists()) {
					f1.mkdirs();
				}
				// 进行文件的创建并进行写入
				file1 = new File(sFileInfoBean.getSavePath() + "/"
						+ sFileInfoBean.getEncryption() + ".temp");
				file1.setWritable(true, false);
				//文件是否存在
//				boolean isExists = file.exists();
				file1.createNewFile();
				fileOutputStream = new FileOutputStream(file1);
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
					}
				}
				
			} catch(Exception e){
				jsonResult.setCode(0);
				jsonResult.setResult(false);
				e.printStackTrace();
			}
		return JSONObject.fromObject(jsonResult).toString();
	}

	/**
	 * 文件上传创建任务
	 * 
	 * @param id
	 * @param fileName
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/fileService/CreateTaskEx")
	public ModelAndView CreateTaskEx(@RequestParam("id") String id,
			@RequestParam("filename") String fileName,
			@RequestParam("size") Long size,
			@RequestParam("savetype") String saveType) {
		logger.info("进入CreateTaskEx==========="+fileName);
//		System.out.println("=======CreateTaskEx======="+fileName);
		
		// 返回参数
		String xml = "";
		try {
			if(saveType==null || saveType.equals("")){
				saveType="99";
			}
			// 上传文件的大小
			Long isuploadsize = (long) 0;
			// 根据传入的ID进行查询，看是否存在信息。
			TFileInfo sFileInfoBean = fileInfoService.getSFileInfoByisMd5(id);
			if (sFileInfoBean == null) {
				sFileInfoBean = new TFileInfo();
				// 文件的保存路径
				SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd");// 设置日期格式
				String subPath = d.format(new Date());
				String savePath = Constant.FILE_PATH +saveType+"/"+ subPath;
				// 解析文件名
				String extName = "";
				String showName = fileName;
				try {
					byte[] by = decoder.decodeBuffer(fileName);
					fileName = new String(by,"gbk");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} 
				// 扩展名格式：
				if (fileName.lastIndexOf(".") >= 0) {
					extName = fileName.substring(fileName.lastIndexOf("."));
				}
				// 显示文件名
//			if (fileName.lastIndexOf(".") >= 0) {
//				showName = fileName.substring(0, fileName.lastIndexOf("."));
//			}
				sFileInfoBean.setPoid(UUID.randomUUID().toString());
				sFileInfoBean.setFileName(showName);
				sFileInfoBean.setFileExt(extName);
				sFileInfoBean.setExt3("0");
				sFileInfoBean.setExt4(size);
				sFileInfoBean.setSaveType(saveType);
//			sFileInfoBean.se
				sFileInfoBean.setEncryption(id);
				// 未开始上传，只创建了任务
				sFileInfoBean.setStatus(0);
				sFileInfoBean.setSavePath(savePath);
				
//			System.out.println("=== CreateTaskEx----===savePathl========"+savePath);
				fileInfoService.saveFileInfo(sFileInfoBean);
				logger.info("进入fileInfoService.saveFileInfo===========");
				// 返回0代表创建任务成功
				xml = "<createtask state='0' size='0'></createtask>";
			} else {
				isuploadsize = Long.parseLong(sFileInfoBean.getExt3());
				// 返回1代表已经存在此任务
				xml = "<createtask state='1' size='" + isuploadsize
						+ "'></createtask>";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("================结束");
		return new ModelAndView("restfulXmlView",
				BindingResult.MODEL_KEY_PREFIX, xml);
	}
	/**
	 * 文件上传创建任务
	 * 
	 * @param id
	 * @param fileName
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/fileService/CreateTask")
	public ModelAndView CreateTask(@RequestParam("id") String id,
			@RequestParam("filename") String fileName,
			@RequestParam("size") Long size) {
//		System.out.println("=======CreateTask======="+fileName);
		// 返回参数
		String xml = "";
		// 上传文件的大小
		Long isuploadsize = (long) 0;
		// 根据传入的ID进行查询，看是否存在信息。
		TFileInfo sFileInfoBean = fileInfoService.getSFileInfoByisMd5(id);
		if (sFileInfoBean == null) {
			sFileInfoBean = new TFileInfo();
			// 文件的保存路径
			SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd");// 设置日期格式
			String subPath = d.format(new Date());
			String savePath = Constant.FILE_PATH +"999"+"/"+ subPath;
			
			String extName = "";
			String showName = fileName;
			try {
				fileName=URLDecoder.decode(fileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
			// 扩展名格式：
			if (fileName.lastIndexOf(".") >= 0) {
				extName = fileName.substring(fileName.lastIndexOf("."));
			}
			// 显示文件名
//			if (fileName.lastIndexOf(".") >= 0) {
//				showName = fileName.substring(0, fileName.lastIndexOf("."));
//			}
			sFileInfoBean.setPoid(UUID.randomUUID().toString());
			sFileInfoBean.setFileName(showName);
			sFileInfoBean.setFileExt(extName);
			sFileInfoBean.setExt3("0");
			sFileInfoBean.setExt4(size);
			sFileInfoBean.setSaveType("99");
//			sFileInfoBean.se
			sFileInfoBean.setEncryption(id);
			// 未开始上传，只创建了任务
			sFileInfoBean.setStatus(0);
//			System.out.println("=== CreateTask===savePathl========"+savePath);
			sFileInfoBean.setSavePath(savePath);
			fileInfoService.saveFileInfo(sFileInfoBean);
			// 返回0代表创建任务成功
			xml = "<createtask state='0' size='0'></createtask>";
		} else {
			isuploadsize = Long.parseLong(sFileInfoBean.getExt3());
			// 返回1代表已经存在此任务
			xml = "<createtask state='1' size='" + isuploadsize
					+ "'></createtask>";
		}
//		System.out.println("================结束");
		return new ModelAndView("restfulXmlView",
				BindingResult.MODEL_KEY_PREFIX, xml);
	}

	/**
	 * 文件上传接口
	 * 
	 * @param id
	 * @param size
	 * @param isLast
	 * @param content
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/fileService/UploadData")
	public ModelAndView uploadFile(@RequestParam String id,
			@RequestParam boolean isfirst, @RequestParam Long size,
			@RequestParam boolean islast, @RequestParam String data) {
		// 根据传入的ID进行查询，看是否存在信息。
		
		TFileInfo sFileInfoBean = fileInfoService.getSFileInfoByisMd5(id);
//		System.out.println("====进入uploadFile===uploadFile8888888888======="+data);
		byte[] bytes1=new byte[size.intValue()];
		try {
			bytes1 = decoder.decodeBuffer(data);
		} catch (IOException e1) {
			e1.printStackTrace();
		}  
		
		// 返回参数
		String xml = "";
		// 上传文件的大小
//		Long isuploadsize = (long) 0;
	
		File file = null;
		FileOutputStream fileOutputStream = null;
		if (isfirst) {
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
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					fileOutputStream = new FileOutputStream(file);
					fileOutputStream.write(bytes1);
					fileOutputStream.flush();
					fileOutputStream.close();
					// 返回1成功
					xml = "<uploadfile result='1' ></uploadfile>";
				} catch (FileNotFoundException e) {
					// 返回2失败
					xml = "<uploadfile result='2' ></uploadfile>";
					e.printStackTrace();
				} catch (IOException e) {
					xml = "<uploadfile result='2' ></uploadfile>";
					e.printStackTrace();
				}
			}
			sFileInfoBean.setExt3(size.toString());
			// 未完成状态
			sFileInfoBean.setStatus(2);
		} else {
			try {
				// 进行内容的追加
				fileOutputStream = new FileOutputStream(
						sFileInfoBean.getSavePath() + "/"
								+ sFileInfoBean.getEncryption() + ".temp", true);
				fileOutputStream.write(bytes1);
				fileOutputStream.flush();
				fileOutputStream.close();
				xml = "<uploadfile result='1' ></uploadfile>";
			} catch (FileNotFoundException e) {
				xml = "<uploadfile result='2' ></uploadfile>";
				e.printStackTrace();
			} catch (IOException e) {
				xml = "<uploadfile result='2' ></uploadfile>";
				e.printStackTrace();
			}
			Long c = Long.parseLong(sFileInfoBean.getExt3()) + size;
			sFileInfoBean.setExt3(c.toString());
			// 未完成状态
			sFileInfoBean.setStatus(2);
		}
		if (islast) {
			// 上传完成状态
			xml = "<uploadfile result='1' ></uploadfile>";
			sFileInfoBean.setStatus(3);
		}
		fileInfoService.saveFileInfo(sFileInfoBean);
		return new ModelAndView("restfulXmlView",
				BindingResult.MODEL_KEY_PREFIX, xml);
	}
	/**
	 * 文件下载接口
	 * @param id
	 * @param start
	 * @param size
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/fileService/downLoadFileDoGet")
	public ModelAndView downloadFileDoGet(@RequestParam String id,
														 @RequestParam int start,
														 @RequestParam int size) {
		        // 返回参数
				String xml = "";
				// 根据传入的ID进行查询，看是否存在信息。
				TFileInfo sFileInfoBean = fileInfoService.getSFileInfoByisMd5(id);
				File f1 = new File(sFileInfoBean.getSavePath()+"/"+sFileInfoBean.getEncryption()+".temp");
				String filecontent=getBinary(f1,start,size);
				xml=filecontent.trim();
				
		return new ModelAndView("restfulXmlView",
				BindingResult.MODEL_KEY_PREFIX, xml);
	}

	/**
	 * 文件下载接口
	 * @param id
	 * @param start
	 * @param size
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/fileService/downLoadFile")
	public ModelAndView downloadFile(@RequestParam String id,
														 @RequestParam int start,
														 @RequestParam int size) {
		        // 返回参数
				String xml = "";
				// 根据传入的ID进行查询，看是否存在信息。
				TFileInfo sFileInfoBean = fileInfoService.getSFileInfoByisMd5(id);
				File f1 = new File(sFileInfoBean.getSavePath()+"/"+sFileInfoBean.getEncryption()+".temp");
				String filecontent=getBinary(f1,start,size);
				xml=filecontent.trim();
				
		return new ModelAndView("restfulXmlView",
				BindingResult.MODEL_KEY_PREFIX, xml);
	}

	private static  byte[] getByte(File file,int e,int d) throws Exception 
    { 
		long len=file.length();
		RandomAccessFile raf=new RandomAccessFile(file,"r"); 
		raf.seek(e);
		
		
		
		
        byte[] buffer = null; 
        if(file!=null) 
        { 
        	if(len-e>d){
      		  buffer  =   new   byte [d];
                raf.read(buffer,0,d);
                raf.close();
      	}else{
      		  Long size=len-e;    
      		  buffer  =   new   byte [size.intValue()];
                raf.read(buffer,0,size.intValue());
                raf.close();
      	}
        } 
        return buffer; 
    } 
    
    private static String getBinary(File f,int s,int end){      
        try {      
    
            byte[] bytes =getByte(f,s,end);   
            return encoder.encodeBuffer(bytes).trim();      
        } catch (IOException e) {      
            e.printStackTrace();      
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
        return null;      
    }   
	/**
	 * 文件查询接口
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/fileService/QueryFile")
	public ModelAndView queryFile(@RequestParam String id) {
		// 返回参数
		String xml = "";
		// 根据传入的ID进行查询，看是否存在信息。
		TFileInfo sFileInfoBean = fileInfoService.getSFileInfoByisMd5(id);
		if (sFileInfoBean != null) {
			if(sFileInfoBean.getStatus()==2){
				//未传完
				xml = "<queryfile result='2' filename='' size='0' url='0'></queryfile>";
			}else if(sFileInfoBean.getStatus()==3){
				// 文件不存在
//				fileName=URLEncoder.encode(s, enc)
//				new String(str.getBytes("UTF-8"),"UTF-8"): 
				//转为base64的编码
				String fileName=sFileInfoBean.getFileName().trim();//+sFileInfoBean.getFileExt().trim();
				System.out.println("=======queryFile======="+fileName);
					xml = "<queryfile result='1' filename='"+fileName+"' size='"
							+ sFileInfoBean.getExt4()
							+ "' url=''></queryfile>";
			
			}
		
		} else {
			// 文件不存在
			xml = "<queryfile result='2' filename='' size='0' url='0'></queryfile>";
		}
		return new ModelAndView("restfulXmlView",
				BindingResult.MODEL_KEY_PREFIX, xml);
	}
	/**
	 * 文件查询接口
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/fileService/QueryFileDoGet")
	public ModelAndView queryFileDoGet(@RequestParam String id) {
		// 返回参数
		String xml = "";
		// 根据传入的ID进行查询，看是否存在信息。
		TFileInfo sFileInfoBean = fileInfoService.getSFileInfoByisMd5(id);
		if (sFileInfoBean != null) {
			if(sFileInfoBean.getStatus()==2){
				//未传完
				xml = "<queryfile result='2' filename='' size='0' url='0'></queryfile>";
			}else if(sFileInfoBean.getStatus()==3){
				// 文件不存在
//				fileName=URLEncoder.encode(s, enc)
//				new String(str.getBytes("UTF-8"),"UTF-8"): 
				//转为base64的编码
				String fileName=encoder.encodeBuffer((sFileInfoBean.getFileName().trim()+sFileInfoBean.getFileExt().trim()).getBytes()).trim(); 
				System.out.println("=======queryFileDoGet======="+fileName);
					xml = "<queryfile result='1' filename='"+fileName+"' size='"
							+ sFileInfoBean.getExt4()
							+ "' url=''></queryfile>";
			
			}
		
		} else {
			// 文件不存在
			xml = "<queryfile result='2' filename='' size='0' url='0'></queryfile>";
		}
		return new ModelAndView("restfulXmlView",
				BindingResult.MODEL_KEY_PREFIX, xml);
	}
	
	@RequestMapping("/fileService/UploadEx")
	public @ResponseBody String upload(HttpServletRequest request){
		DiskFileItemFactory factory = new DiskFileItemFactory();        //基于磁盘文件项目创建一个工厂对象
	    ServletFileUpload upload = new ServletFileUpload(factory);  //创建一个新的文件上传对象
	    
		String id = "";
		String saveType = "";
		String fileName = "";
		// 返回参数
		String xml = "<uploadfile result='2'></uploadfile>";
		TFileInfo sFileInfoBean = new TFileInfo();
		
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
					}else if("id".equals(fileItem.getFieldName())){
						id = new String(fileItem.getString().getBytes("iso-8859-1"), "gbk");
						logger.info(System.currentTimeMillis()+ " - " + id);
						sFileInfoBean.setEncryption(id);
					}else if("savetype".equals(fileItem.getFieldName())){
						saveType = new String(fileItem.getString().getBytes("iso-8859-1"),"gbk");
						sFileInfoBean.setSaveType(saveType);
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
				String savePath = Constant.FILE_PATH +saveType+"/"+ subPath;
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
				fis.close();
				// 返回1成功
				xml = "<uploadfile result='1'></uploadfile>";
				sFileInfoBean.setFileName(encoder.encode(fileName.getBytes()));
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
						logger.info("出现异常 - " + id, e);
					}
				}
			} catch (FileUploadException e) {
				xml = "<uploadfile result='2'></uploadfile>";
				e.printStackTrace();
			}catch(Exception e){
				xml = "<uploadfile result='2'></uploadfile>";
				e.printStackTrace();
			}
		return "<string>"+xml+"</string>";
	}
}
