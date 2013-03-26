<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>【月次】</title>
<link href="<c:url value="/static/resources/css/style.css"/>" rel="stylesheet" type="text/css">
<script src="http://use.edgefonts.net/alegreya-sc.js"></script>
</head>
<body>
  <jsp:include page="/WEB-INF/views/menu.jsp" />
  <div id="center">
    <p>
      <strong>【月次】...【分類】、【通貨】でまとめた支出を表示します</strong>
    </p>
    <form:form commandName="regWalletRecord">
      <form:select path="date" onchange="submit();">
        <form:options items="${listWlDate}" />
      </form:select>
      <form:select path="currency.id" onchange="submit();">
        <form:option value="-1" label="-- Select Currency --" />
        <form:options items="${listWlcurrency}" itemValue="id" itemLabel="currency" />
      </form:select>
    </form:form>
    <display:table id="data" name="listWallet" class="displaytag" requestURI="/summary" sort="list" decorator="org.displaytag.decorator.TotalTableDecorator" pagesize="17">
      <display:column title="分類" property="category.category" sortable="true" />
      <display:column title="通貨" property="currency.currency" sortable="true" />
      <display:column title="金額" property="amount" sortable="true" format="{0,Number,#,###.00}" total="${isTotal}" style="text-align: right;" />
    </display:table>
  </div>
</body>
</html>
<div class="#bottom" id="bottom">Copyright (C) Toshifumi Masuda</div>
</body>
</html>
