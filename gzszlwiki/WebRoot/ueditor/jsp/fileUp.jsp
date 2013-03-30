<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ueditor.Uploader" %>


<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    Uploader up = new Uploader(request);
    up.setSavePath("saestor://fileupload"); //保存路径
    String[] fileType = {".rar" , ".doc" , ".docx" , ".zip" , ".pdf" , ".txt" , ".swf", ".wmv"};  //允许的文件类型
    up.setAllowFiles(fileType);
    up.setMaxSize(1000);        //允许的文件最大尺寸，单位KB
    up.upload();
    response.getWriter().print("{'url':'"+up.getUrl().substring(up.getUrl().indexOf("fileupload")+11)+"','fileType':'"+up.getType()+"','state':'"+up.getState()+"','original':'"+up.getOriginalName()+"'}");

%>
