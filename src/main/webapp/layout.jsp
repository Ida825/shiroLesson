<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <title>layout.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="themes/icon.css">
    <script type="text/javascript" src="jquery.min.js"></script>
    <script type="text/javascript" src="jquery.easyui.min.js"></script>
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<script type="text/javascript">
		function urlClick(myTitle,myUrl){
		//判断新增前是否已经存在该选项卡
			var ifExists=$("#myTabs").tabs("exists",myTitle);
			
			if(!ifExists){
				$("#myTabs").tabs("add",{
					title:myTitle,
					closable:true,  //可关闭
					content:'<iframe src="'+myUrl+'" frameborder=0 width="100%" scrolling="no" height="100%"></iframe>',
					
				});
			}		
			
			//选择个"菜品管理"这个选项卡面板
			$("#myTabs").tabs("select",myTitle);
		}
	</script>
  </head>
  
  <body style="margin:2px">
    <div class="easyui-layout" style="width:100%;height:100%;">
		<div data-options="region:'north'" style="height:15%;background-image: url(a.png)"></div>
		<div data-options="region:'west',split:true" title="导航菜单" style="width:18%;">
			<div class="easyui-accordion" style="width:500px;height:300px;">
				<div title="权限管理" style="overflow:auto;padding:10px;">	
					<c:forEach var="menu" items="${requestScope.menuList}">
						<a href="javascript:urlClick('${menu.menuName}','${pageContext.request.contextPath}${menu.menuUrl}')"><img src="themes/icons/search.png"/>${menu.menuName}</a><br/>
					</c:forEach>			
				</div>
				<div title="系统设置" style="padding:10px;">
					
				</div>		
			</div>
		</div>
		<div data-options="region:'center',iconCls:'icon-ok'">
			<div id="myTabs"  class="easyui-tabs" style="width:100%;height:100%">
				<div title="欢迎访问" style="padding:10px;background-image: url(2.jpg)"></div>		
			</div>
		</div>
	</div>
  </body>
</html>
