<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../basic/base.jsp"%>

<!DOCTYPE html>

<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
</head>
<body>
	<form method="post" action="${ctx}/anno/list.action" id="listform">
		<div class="panel admin-panel">
			<div class="padding border-bottom">
				<ul class="search" style="padding-left:10px;">
					<li>
					<c:if test="${sessionScope.type=='root' or sessionScope.type=='manager'}">
						<a class="button border-main icon-plus-square-o"
						href="${ctx}/anno/tocreate.action"> 添 加 公 告</a>
					</c:if>
						</li>
					  <if condition="$iscid eq 1">
					<!--<li>
						<select name="date" class="input" id="arg"
							style="margin-left:30px;width:250px; line-height:17px;">
								<option value="">按日期分类</option>
								<option value="3" onclick="selectdate(this.value)">三天内</option>
								<option value="7" onclick="selectdate(this.value)">一周内</option>
								<option value="30" onclick="selectdate(this.value)">一个月内</option>
						</select>
						<script type="text/javascript">
							$("select[name='date']").val("${date}");
						</script>
					</li>-->
					</if>
					<li><input type="text" placeholder="请 输 入 查 询 信 息 的 关 键 字" id="key" value="${key}"
						class="input"	style="width:250px; margin-left:20px;line-height:17px;display:inline-block" /> 
						<a href="#" class="button border-main icon-search"
						onclick="Sear()">查 询</a></li>
				</ul>
			</div>
			<table class="table table-hover text-center">
			
				<tr>
					<th width="5%"></th>
					<th width="10%">时间</th>
					<th width="15%">标题</th>
					<th width="20%">内容</th>
					<th width="25%">操作</th>
				</tr>
			
								<%
System.out.println("lllllll"+request.getAttribute("dataList") );
%>
				<c:forEach items="${dataList}" var="obj" varStatus="status">
			
				
					<tr class="leirong">
						<td style="text-align:left; padding-left:20px;"><input
							type="checkbox" name="announcementId"
							value="${obj.announcementId}" class="sb" /></td>
						<td>${obj.createTime}</td>
						<td width="30%">${obj.title}</td>
						<td>${obj.content}</td>
						 
						<td><div class="button-group" style="height:48px;">
							<c:if test="${sessionScope.type=='root' or sessionScope.type=='manager'}">
								<a class="button border-main"
									href="${ctx}/anno/toupdate.action?annoId=${obj.announcementId}"><span
									class="icon-edit"></span>修改</a> <a  id="deleteBtn" class="button border-red"
									href="${ctx}/anno/deletebyid.action?annoId=${obj.announcementId}&pageNo=${page.pageNo}&totalPage=${page.totalPage}"
									onclick="return del(1,1,1)"><span class="icon-trash-o"></span>
									删除</a>
							</c:if>
									
									
							</div></td>
					</tr>
				</c:forEach>
				
	
				<tr>
					<td style="text-align:left; padding:19px 0;padding-left:20px;">
					<input	type="checkbox" id="checkall" /></td>
					<td colspan="7" style="text-align:left;padding-left:20px;">
					<c:if test="${sessionScope.type=='root' or sessionScope.type=='manager'}">
						<a	href="" class="button border-red icon-trash-o"
						style="padding:5px 15px;" onclick="DelSelect()"> 多 选 删 除</a>
					</c:if>
				</tr>
				
				<tr>
				
					<td colspan="8">
					
						<div class="pagelist">
						
									<a  onclick="fanye('1')" href="#">上一页</a> 
									<a  onclick="fanye('2')" href="#">${page.pageNo}</a>
									<a  onclick="fanye('3')" href="#">${page.pageNo+1}</a>
									<a  onclick="fanye('4')" href="#">${page.pageNo+2}</a>......
									<a  onclick="fanye('5')" href="#">${page.totalPage}</a>
									<a  onclick="fanye('6')" href="#">下一页</a>
								
						</div>
					</td>
					
				</tr>
			</table>
		</div>
	</form>



	<script type="text/javascript">
	
		function fanye(id){
			var url;
			if(id=='1'){
				url="${ctx}/anno/list.action?totalPage=${page.totalPage}&pageNo=${page.pageNo-1}&date=${date}";
			}else if(id=='2'){
				url="${ctx}/anno/list.action?totalPage=${page.totalPage}&pageNo=${page.pageNo}&date=${date}";
			}else if(id=='3'){
				url="${ctx}/anno/list.action?totalPage=${page.totalPage}&pageNo=${page.pageNo+1}&date=${date}";
			}else if(id=='4'){
				url="${ctx}/anno/list.action?totalPage=${page.totalPage}&pageNo=${page.pageNo+2}&date=${date}";
			}else if(id=='5'){
				url="${ctx}/anno/list.action?totalPage=${page.totalPage}&pageNo=${page.totalPage}&date=${date}";
			}else if(id=='6'){
				url="${ctx}/anno/list.action?totalPage=${page.totalPage}&pageNo=${page.pageNo+1}&date=${date}";
			}
			var key=encodeURI(encodeURI("${key}"));
			url+=("&key="+key);
			window.location.href=url;
		}
		
	
	
		$(".pagelist a").each(function() {
			if ('${page.pageNo}'==this.text) {
				$(this).css({ 
					"color":"#FFF",
					"background-color":"#09F",
					"border-color": "#09F"
  				});
			}
			if('${page.totalPage}'<this.text) {
				 $(this).attr('href', '#');  
				$(this).css({ 
					display:"none"
  				});
			}
		});

		if('${page.pageNo}'=='${page.totalPage}') {
			$(".pagelist .xiaye").css({ 
				display:"none"
 			});
		}
		if('${page.pageNo}'==1) {
			$(".pagelist .shangye").css({ 
				display:"none"
 			});
		}

	
		function Shangye(totalPage,pageNo){
			window.location.href="${ctx}/anno/list.action?pageNo="+(pageNo-1)+"&totalPage="+totalPage;
			return false;
		}	
		function Xiaye(totalPage,pageNo){
			window.location.href="${ctx}/anno/list.action?pageNo="+(pageNo+1)+"&totalPage="+totalPage;
			return false;
		}	
		
	</script>
	
	<script type="text/javascript">
	
	/* $.ajax({
                cache: true,
                type: "POST",
                url:ajaxCallUrl,
                data:$('#yourformid').serialize(),// 你的formid
                async: false,
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
                    $("#commonLayout_appcreshi").parent().html(data);
                }
            }); */
	
		//下拉列表 分类
		function selectdate(arg){
			window.location.href='${ctx}/anno/list.action?date='+arg; 
			/* if(arg!=null){
				$.ajax({
					type : 'post',
					url : '${ctx}/anno/list.action', 
					data : {
						date:arg		
					},
					success : function(msg) {
					}
				});
			
			} */
		 		
		}									
		
	
		//搜索
		function Sear(){
			var key=$("#key").val();
			var arg1=$("#arg").val();
			window.location.href='${ctx}/anno/list.action?key='+encodeURI(encodeURI(key))+'&date='+arg1;
			
			return false;
		}

		//单个删除
		function del(id, mid, iscid) {
			if (confirm("您确定要删除吗?")) {
				var a=0;
				$(".leirong").each(function(){
					a++;
				});
				if(a==1){
					var id=$(".sb").val();
					window.location.href="${ctx}/anno/deletebyid.action?annoId="+id+"&pageNo=${page.pageNo-1}&totalPage=${page.totalPage-1}";
					return false;
				}
					
			}else {
				return false;
			}
		}

		//全选
		$("#checkall").click(function() {
			$("input[name='announcementId']").each(function() {
				if (this.checked) {
					this.checked = false;
				} else {
					this.checked = true;
				}
			});
		})

		//批量删除
		function DelSelect() {
			var Checkbox = false;
			var quanshan=true; 
			var ff = "";
			$("input[name='announcementId']").each(function(x, y) {
				if (this.checked == true) {
					Checkbox = true;
					ff += $(this).val() + ",";
				}
				else quanshan=false;
			});
			if (Checkbox) {
				var t = confirm("您确认要删除选中的内容吗？");
				if (t == false) {
					return false;
				}
				$.ajax({
					type : 'post',
					url : '${ctx}/anno/delete.action',
					dateTye:'text',
					data : {
						sb : ff,
						pageNo : '${page.pageNo-1}',
						totalPage : '${page.totalPage-1}'
					},
					success:function(msg){
					},error:function(){
							if(quanshan){
								if("${page.pageNo}"!='1') window.location.href="${ctx}/anno/list.action?pageNo=${page.pageNo-1}&totalPage=${page.totalPage-1}";
								else  window.location.href="${ctx}/anno/list.action?pageNo=${page.pageNo}&totalPage=${page.totalPage-1}";
							}
							else	
							window.location.href="${ctx}/anno/list.action?pageNo=${page.pageNo}&totalPage=${page.totalPage}";
					}
					
				});
				
			} else {
				alert("请选择您要删除的内容!");
				return false;
			}
		}

		//批量排序
		function sorts() {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");
				return false;
			}
		}

		//批量首页显示
		function changeishome(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量推荐
		function changeisvouch(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量置顶
		function changeistop(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量移动
		function changecate(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量复制  meibaobing 没看到穿进去的checkbox 没毛病吗你post请求的怎么看的到  只能在后台看 
		function changecopy(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {
				var i = 0;
				$("input[name='id[]']").each(function() {
					if (this.checked == true) {
						i++;
					}
				});
				if (i > 1) {
					alert("只能选择一条信息!");
					$(o).find("option:first").prop("selected", "selected");
				} else {

					$("#listform").submit();
				}
			} else {
				alert("请选择要复制的内容!");
				$(o).find("option:first").prop("selected", "selected");
				return false;
			}
		}
	</script>
</body>
</html>