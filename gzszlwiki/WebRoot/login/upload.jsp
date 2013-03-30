
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <link href="image/favicon.ico" rel="shortcut icon" type="image/ico"/>
  <link href="image/favicon.ico" rel="bookmark" type="image/ico"/>
<meta http-equiv="x-ua-compatible" content="ie=7" />
</head>
<body>
<script type="text/javascript">
function checkFile() 
{ 
 var file=document.getElementById("file"); 
 var i=file.value.lastIndexOf("."); 
    var ext=file.value.substring(i); 
    var  ext1=ext.toLowerCase();

    if(ext1!=".gif" && ext1!=".jpg" && ext1!=".jpeg"&&ext1!=".png") 
    {  document.getElementById("file").outerHTML = document.getElementById("file").outerHTML; 
        alert("您的上传图片的格式必须为jpg，jpeg，gif，png!");    
     } 
 }

</script>

<form method="post" action="uploadImage" method="post" ENCTYPE="multipart/form-data">
 <input type="hidden" name="type" value="tinymce"/>
 &nbsp;<span style="font-size:12px">上传图片</span>&nbsp;&nbsp;&nbsp;
 <input type="file" name="file" id="file" style="width:210px" onchange="checkFile()" />
 <input type="submit" name="reset" value="上传到服务器" />

</form>
</body>
</html>