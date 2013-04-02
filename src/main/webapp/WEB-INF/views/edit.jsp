<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/static/resources/css/style.css"/>" rel="stylesheet" type="text/css">
<script src="https://use.edgefonts.net/alegreya-sc.js"></script>
</head>
<body>
  <jsp:include page="/WEB-INF/views/menu.jsp" />
  <div id="center">
    <p>
      <strong>【一覧】...すべてのトランザクションを日付降順で表示します</strong>
    </p>
    <table>
      <tr>
        <td valign="top">
          <display:table id="data" name="listWallet" class="displaytag2" requestURI="/edit" sort="list" pagesize="20" export="true">
            <display:setProperty name="export.excel" value="false" />
            <display:setProperty name="export.csv" value="false" />
            <display:setProperty name="export.xml" value="true" />
            <display:setProperty name="export.xml.filename" value="finanzieren.xml" />
            <display:setProperty name="export.xml.include_header" value="false" />
            <display:column title="編集・削除" sortable="true" media="html">
              <a href="<c:url value="/list/${data.id}/edit/"/>"><img src="<c:url value="/static/resources/gfx/edit.png"/>" style="width: 12px;" /></a>・
              <a href="<c:url value="/list/${data.id}/delete/"/>"><img src="<c:url value="/static/resources/gfx/delete.png"/>" style="width: 12px;" /></a>
            </display:column>
            <display:column title="日付" property="date" sortable="true" format="{0,date,yyyy-MM-dd}" />
            <display:column title="項目" property="kind.kind" sortable="true" />
            <display:column title="分類" property="category.category" sortable="true" />
            <display:column title="通貨" property="currency.currency" sortable="true" />
            <display:column title="金額" property="amount" sortable="true" style="text-align: right" />
            <display:column title="備考" property="note" sortable="true" />
          </display:table>
        </td>
      </tr>
    </table>
    <br />
  </div>
</body>
</html>
<div class="#bottom" id="bottom">Copyright (C) Toshifumi Masuda</div>
</body>
</html>
