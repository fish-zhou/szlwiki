package com.action.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import util.ImageCut;

@Component("imgUpload")
public class ImgUpload extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
//	public static final String IMGROOT = "saestor://fileupload/avatar/";//sae涓婄殑鍌ㄥ瓨璺緞
	public static final String IMGROOT = "/avatar/";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userWebAppPath = getWebAppPath();
//		String userWebAppPath = IMGROOT;
		/**锟斤拷锟斤拷欠锟斤拷锟酵计拷洗锟斤拷募锟斤拷锟�/
		checkImageDir(userWebAppPath);
		
		/**图片锟较达拷锟斤拷锟斤拷锟铰凤拷锟*/
		String imgUploadPath = null;
		String imgWebAppPath = null;
		/**图片锟斤拷缀*/
		String imgFileExt = null;
		
		/**图片锟斤拷锟�锟皆碉拷前锟斤拷锟斤拷*/
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		String imgFileId = formatter.format(new Date());
		
		//图片锟斤拷始锟斤拷锟竭讹拷锟斤拷锟斤拷
		String width = null;
		String height = null;
		
		int imgWidth = 0;
		int imgHeight = 0;

		try {
			
			com.jspsmart.upload.SmartUpload smartUpload = new com.jspsmart.upload.SmartUpload();
			smartUpload.initialize(getServletConfig(), request, response);
			smartUpload.upload();
			com.jspsmart.upload.Request rqest = smartUpload.getRequest();
			
			//指锟斤拷图片锟斤拷群透叨锟�
			width = rqest.getParameter("width");
			if(null == width){
				width = "700";
			}
			height= rqest.getParameter("height");	
			if(null == height){
				height = "600";
			}
			imgWidth = Integer.parseInt(width);
			imgHeight = Integer.parseInt(height);
			//锟侥硷拷锟斤拷锟斤拷
			int fileCounts =  smartUpload.getFiles().getCount();	
		
			for (int i = 0; i <fileCounts; i++) {
				com.jspsmart.upload.File myFile = smartUpload.getFiles().getFile(i);
				
				if (!myFile.isMissing()) {
					
					imgFileExt = myFile.getFileExt();
					//锟斤拷装图片锟斤拷实锟斤拷锟�
					imgFileId += i + System.currentTimeMillis() + "." + imgFileExt;
					//图片锟斤拷锟铰凤拷锟�
					imgWebAppPath = userWebAppPath + imgFileId;
					//锟斤拷锟酵计拷募锟�
					myFile.saveAs(imgWebAppPath);
					//图片锟斤拷锟斤拷锟铰凤拷锟�
					imgUploadPath = IMGROOT + imgFileId;
					//锟斤拷锟酵计拷锟叫�
					BufferedImage src = ImageIO.read(new File(imgWebAppPath)); // 锟斤拷锟斤拷锟侥硷拷						 
					int imgSrcWidth = src.getWidth(); // 锟矫碉拷源图锟斤拷							 
					//锟斤拷锟斤拷指锟斤拷锟斤拷小
					imgWidth = imgSrcWidth > imgWidth ? imgWidth : imgSrcWidth;
					
					int imgSrcHeight = src.getHeight(); // 锟矫碉拷源图锟斤拷
					imgHeight = imgSrcHeight > imgHeight ? imgHeight : imgSrcHeight;
				
					//锟斤拷锟斤拷图片锟斤拷锟斤拷锟矫达拷小锟斤拷锟�
					ImageCut.scale(imgWebAppPath, imgWebAppPath,imgWidth,imgHeight);
					 File f = new File(imgWebAppPath);								
//					 if(f.exists()){						
//						 System.out.println("锟斤拷锟斤拷"+imgWidth+"*"+imgHeight+"图片锟缴癸拷");
//					 }					
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		String path = "imgcrop.jsp?tag=0&oldImgPath="+imgUploadPath+"&imgFileExt="+imgFileExt+"&imgRoot="+IMGROOT+"&width="+imgWidth+"&height="+imgHeight;
//		System.out.println("path: "+path);
		request.getRequestDispatcher(path).forward(request,response);
		
	}
	
	private String getWebAppPath(){
		String webAppPath = this.getServletContext().getRealPath("/");		
		String userWebAppPath = webAppPath+IMGROOT;
		return userWebAppPath;
	}

	private void checkImageDir(String userWebAppPath) {		
		 File file = new File(userWebAppPath);
		 if(!file.exists()){
			 file.mkdir();
		 }
	}

}
