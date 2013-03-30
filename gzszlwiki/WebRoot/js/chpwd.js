
$(document).ready(function(){
	var count = 0;
	$('#chpwd').click(function(){
		if(count===0){
			addChpwd();
			count++;
		}
	});
		
	//增加修改密码模块	
	 function addChpwd(){
		 var delDivid = "delDiv";        
	     var delBtnId = 'del';
		 $( '<div id='+delDivid+'>'+  		
			      	'<div id="oldPassword">原密码： &nbsp;<input  type="text" name="oldPassword"></div>'+
			      	'<div id="newPassword">新密码：&nbsp;<input  type="password" name="newPassword"></div>'+
			      	'<div id="rePassword">新密码：&nbsp;<input  type="password" name="rePassword"></div>'+
			      	'<br /><br />'+
			      	'<input  type="button"  class="BTN" value="取消修改" id='+delBtnId+'>'+ 
			      	' &nbsp;&nbsp;&nbsp;&nbsp;'+
			      	' <input type="submit" class="BTN" value="提交修改" '+
			      	'</div>'
			     ).appendTo($("#chpwdDiv"));
		 
		 $('#' + delBtnId).click({
	            divId: delDivid
	        }, delChpwd);
	 }
	 
	 //删除增加的模块
	 function delChpwd(event){
	        $('#' + event.data.divId).remove();
	        count--;
	    }
});