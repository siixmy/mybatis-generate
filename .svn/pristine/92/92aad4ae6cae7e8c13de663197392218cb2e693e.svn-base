<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Java持久化生成工具</title>
    <%@ include file="inc.jsp"%>
    <script type="text/javascript" src="${ctx}/include/js/gen.js"></script>
    <link href="${ctx}/include/css/gen.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form id="genForm" action="${ctx}/generate.htm" url-download="${ctx}/download.htm">
    <table>
        <tr>
            <td><span class="required">*</span>数据库类型:</td>
            <td><select name="dbType">
                <c:forEach items="${dbTypes}" var="v">
                    <option>${v}</option>
                </c:forEach>
            </select></td>
        </tr>
        <tr>
            <td><span class="required">*</span>主机/端口:</td>
            <td><input type="text" name="dbHost" datatype="*" nullmsg="主机IP错误">/<input type="text" name="dbPort" datatype="*" nullmsg="端口错误"/></td>
            <td><div class="Validform_checktip"></div></td>
        </tr>
        <tr>
            <td><span class="required">*</span>用户名:</td>
            <td><input type="text" name="dbUsername"/></td>
        </tr>
        <tr>
            <td><span class="required">*</span>密码:</td>
            <td><input type="password" name="dbPwd"/></td>
        </tr>
        <tr>
            <td><span class="required">*</span>数据库</td>
            <td><input type="text" name="db"/></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: right">
                <input class="customBtn" type="button" id="testDb" value="测试连接" data-href="${ctx}/connect-db.htm"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <hr/>
            </td>
        </tr>
        <tr>
            <td>要生成的表(,分隔):</td>
            <td><textarea name="tableGen" style="width: 320px"></textarea></td>
        </tr>
        <tr>
            <td>截取表名开始字符作为Bean名称:</td>
            <td><input type="text" name="tableFirstRemove" value="0"></td>
        </tr>
        <tr>
            <td>是否使用老版:<div class="tips">即使用tcmc-common包的ISingleTableDao</div></td>
            <td><input type="checkbox" value="true" name="useOld"></td>
        </tr>
        <tr>
            <td>生成基类dao名:<div class="tips">默认值：IMybatisBaseDao</div></td>
            <td><input type="text" name="baseDaoClass"></td>
        </tr>
        <tr>
            <td>是否第一次生成:<div class="tips">第一次生成会覆盖原来自定义sql</div></td>
            <td><input type="checkbox" value="true" name="firstGen" checked></td>
        </tr>
        <tr>
            <td><span class="required">*</span>项目包:<div class="tips">如：com.tc.crm</div></td>
            <td><input type="text" name="packagePrefix"></td>
        </tr>
        <tr>
            <td>文件注释中的作者:</td>
            <td><input type="text" name="author"/></td>
        </tr>
        <tr>
            <td>文件注释中的version:</td>
            <td><input type="text" name="version"/></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: right">
                <input class="customBtn" type="button" id="genBtn" value="生成"/>
            </td>
        </tr>
    </table>
</form>
<p class="remark">
    说明：
<ul>
    <li>要生成的表：若不填，则生成所有的表</li>
    <li>是否第一次生成：true，生成时不会覆盖mybatis/ex里面的xml文件</li>
    <%--<li>是否生成基类dao：不生成则使用tcmc-common-1.0.7.jar中的com.tc.common.dao.ISingleTableDao作为基类</li>--%>
    <li>目标项目所需jar：guava-13.0.1.jar,mybatis-3.1.1.jar,spring-beans-xx.jar,spring-context-xx.jar,tcmc-common-1.0.7.jar</li>
</ul>
</p>
</body>
</html>
