package com.action.image;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import util.ImageCut;

import com.service.UserService;

public class ImgCrop extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService userservice;
	/**
    * 在Servlet中注入对象的步骤:
    * 1.取得ServletContext
    * 2.利用Spring的工具类WebApplicationContextUtils得到WebApplicationContext
    * 3.WebApplicationContext就是一个BeanFactory,其中就有一个getBean方法
    */ 
	public void init() throws ServletException{
		super.init();  
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		userservice = (UserService)ctx.getBean("userservice");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		System.out.println("x: " + request.getParameter("x") + "," + request.getParameter("y") + "," + request.getParameter("w") + "," + request.getParameter("h"));

		// �û�����������ͼƬ�Ĵ�С
		Integer x = Integer.parseInt(request.getParameter("x"));
		Integer y = Integer.parseInt(request.getParameter("y"));
		Integer w = Integer.parseInt(request.getParameter("w"));
		Integer h = Integer.parseInt(request.getParameter("h"));
		
		//��ȡԭ��ʾͼƬ·��
		String oldImgPath = request.getParameter("oldImgPath");
		//ͼƬ��׺
		String imgFileExt = request.getParameter("imgFileExt");
		String imgRoot =  request.getParameter("imgRoot");
		
		Integer width = Integer.parseInt(request.getParameter("width"));
		Integer height = Integer.parseInt(request.getParameter("height"));
		
		//WEBӦ�ó����·��
		String webAppPath = getServletContext().getRealPath("/");
		
		/**ͼƬ���:�Ե�ǰ����*/
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		String imgFileId = formatter.format(new Date());
		String imgName =  imgRoot + imgFileId + System.currentTimeMillis() + "." + imgFileExt;			
		//��װͼƬ��ʵ���
		String createImgPath = webAppPath + imgName;
		
		//֮ǰ�ϴ���ͼƬ·��
		webAppPath += oldImgPath;
		
//		System.out.println("ԭͼƬ·��: " + webAppPath + ",��ͼƬ·��: " + createImgPath);
		
		//���м���ͼƬ����
		ImageCut.abscut(webAppPath, createImgPath, x,y,w, h);
		
		 File f = new File(createImgPath);								
//		 if(f.exists()){						
//			 System.out.println("����ͼƬ��С: "+w+"*"+h+"ͼƬ�ɹ�!");
//		 }	
		 
		String path = "/login/imgcrop.jsp?tag=1&oldImgPath="+oldImgPath+"&imgFileExt="+imgFileExt+"&imgRoot="+imgRoot + "&imgName="+imgName+"&height=" + height + "&width=" + width;
		
		//获取session里的用户邮箱,用户名
		String email = (String)request.getSession().getAttribute("email");
		String username = (String)request.getSession().getAttribute("username");
		
		/**使用Thumbnailator生成指定大小的图像
		 * 大图：120*120
		 * 中图：36*36
		 * 小图：28*28
		 */
//		String avatar_big = imgRoot + username+ "_120x120.jpg";
//		String avatar_mid = imgRoot + username+ "_36x36.jpg";
//		String avatar_sml = imgRoot + username+ "_12x12.jpg";
		String avatar_big = getServletContext().getRealPath("/")+imgRoot + username+ "_120x120.jpg";
		String avatar_mid = getServletContext().getRealPath("/")+imgRoot + username+ "_36x36.jpg";
		String avatar_sml = getServletContext().getRealPath("/")+imgRoot + username+ "_28x28.jpg";
		//大图
		Thumbnails.of(createImgPath)
			.size(120, 120)  
			.keepAspectRatio(false) 
			.outputFormat("jpg")  
			.toFile(avatar_big);
		//中图
		Thumbnails.of(createImgPath)
				.size(36, 36)
				.keepAspectRatio(false)
				.outputFormat("jpg")
				.toFile(avatar_mid);
		//小图
		Thumbnails.of(createImgPath)
				.size(28, 28)
				.keepAspectRatio(false)
				.outputFormat("jpg")
				.toFile(avatar_sml);
		
		//数据库跟新用户头像
		userservice.updateHeadImg(email, avatar_big, avatar_mid, avatar_sml);
		
		request.getRequestDispatcher(path).forward(request,response);
	}

}
