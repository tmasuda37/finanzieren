<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>finanzieren</title>
<link href="<c:url value="/static/resources/css/style.css"/>" rel="stylesheet" type="text/css">
<script src="http://use.edgefonts.net/alegreya-sc.js"></script>
</head>
<body>
  <jsp:include page="/WEB-INF/views/menu.jsp" />
  <div id="center">
    <p>
      <strong>現在状況 － あなたの財布、支出、収入の状況です</strong>
    </p>
    <table>
      <tr>
        <td valign="top">
          <display:table id="data" name="listWallet" class="displaytag" requestURI="/list" sort="list" pagesize="17">
            <display:column title="編集・削除" sortable="true">
              <a href="<c:url value="/list/${data.id}/edit/"/>"><img src="<c:url value="/static/resources/gfx/edit.png"/>" /></a>・
              <a href="<c:url value="/list/${data.id}/delete/"/>"><img src="<c:url value="/static/resources/gfx/delete.png"/>" /></a>
            </display:column>
            <display:column title="日付" property="date" sortable="true" format="{0,date,yyyy-MM-dd}" />
            <display:column title="項目" property="kind.kind" sortable="true" />
            <display:column title="分類" property="category.category" sortable="true" />
            <display:column title="金額" property="amount" sortable="true" />
            <display:column title="通貨" property="currency.currency" sortable="true" />
            <display:column title="備考" property="note" sortable="true" />
          </display:table>
        </td>
        <td valign="top">
          <display:table id="data2" name="listBalance" class="displaytag2" requestURI="/list" pagesize="10">
            <display:column title="通貨" property="currency.currency" />
            <display:column title="現在所持金" property="sum" />
          </display:table>
          <a href="/refresh">Refresh</a>
        </td>
      </tr>
    </table>
  </div>
</body>
</html>
<div class="#bottom" id="bottom">Copyright (C) Toshifumi Masuda</div>
</body>
</html>
