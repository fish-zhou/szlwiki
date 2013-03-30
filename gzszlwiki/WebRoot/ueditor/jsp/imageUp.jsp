    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
    <%@ page import="ueditor.Uploader" %>

    <%
    request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
    Uploader up = new Uploader(request);
    up.setSavePath("saestor://fileupload");
    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
    up.setAllowFiles(fileType);
    up.setMaxSize(1024*10); //单位KB
    up.upload();
    response.getWriter().print("{'original':'"+up.getOriginalName()+"','url':'"+up.getUrl().substring(up.getUrl().indexOf("fileupload")+11)+"','title':'"+up.getTitle()+"','state':'"+up.getState()+"'}");
    %>
