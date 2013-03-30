
$(document)
		.ready(
				function() {

					var count = 0;
					if (count === 0) {
						$('#addPara').click(function() {
							addPara();
						});
					}

					// 澧炲姞娈佃惤妗�
					function addPara() {
						var addPara = document.getElementById("addPara");
						count += 1
						var delDivid = "delDiv" + count;
						var delBtnId = 'del' + count;
						var submitId = 'submit' + count;
						// 涓嬮潰涓よ鐢熸垚浜嗙櫨搴︾紪杈戝櫒
						var editor = new baidu.editor.ui.Editor();
						editor.render('addParaDiv');

						$(
								'<div id='
										+ delDivid
										+ '>'
										+ '<input  type="button"  class="BTN" value="增加段落" id='
										+ delBtnId
										+ '>'
										+ ' &nbsp;&nbsp;&nbsp;&nbsp;'
										+ ' <input type="submit" class="BTN" value="提交" '
										+ ' <br /><br />'
										+ '娈佃惤鏍囬 &nbsp;&nbsp;<input  type="text" name="ptitle">'
										+ '<br /><br />' + '</div>').appendTo(
								$("#addParaDiv"));

						$('#' + delBtnId).click({
							divId : 'addParaDiv'
						}, delPara);

						addPara.style.display = "none";
					}

					// 鍒犻櫎娈佃惤妗�
					function delPara(event) {
						$('#' + event.data.divId).remove();
					}
				});