<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <form:form commandName="regWalletRecord">
      <form:select path="date" onchange="submit();">
        <form:options items="${listWlDate}" />
      </form:select>
      <form:select path="currency.id" onchange="submit();">
        <form:options items="${listWlcurrency}" itemValue="id" itemLabel="currency" />
      </form:select>
    </form:form>
    <table>
      <tr>
        <td valign="top">
          <display:table id="data3" name="listDailyAmount" class="displaytag">
            <display:column title="日付" property="date" format="{0,date,yyyy-MM-dd}" />
            <display:column title="金額" style="text-align: right;">
              <c:set var="formedDate">
                <fmt:formatDate value="${data3.date}" pattern="yyyy-MM-dd" />
              </c:set>
              <a href="<c:url value="/list/${formedDate}/${data3.currency.id}"/>">${data3.sum}</a>
            </display:column>
          </display:table>
        </td>
        <td valign="top">
          <display:table id="data" name="listSummary" class="displaytag2">
            <display:column title="分類" property="category.category" />
            <display:column title="金額" style="text-align: right;">
              <c:set var="formedDate2">
                <fmt:formatDate value="${regWalletRecord.date}" pattern="yyyy-MM-dd" />
              </c:set>
              <a href="<c:url value="/list/${formedDate2}/${data.category.id}/${data.currency.id}"/>">${data.amount}</a>
            </display:column>
          </display:table>
        </td>
        <td valign="top">
          <display:table id="data2" name="listBalance" class="displaytag">
            <display:column title="通貨" property="currency.currency" />
            <display:column title="現在所持金" property="sum" style="text-align: right" />
          </display:table>
          <a href="<c:url value="/refresh"/>">Refresh</a>
        </td>
        <td valign="top">
          <display:table id="data" name="listWallet" class="displaytag2">
            <display:column title="日付" property="date" format="{0,date,yyyy-MM-dd}" />
            <display:column title="項目" property="kind.kind" />
            <display:column title="分類" property="category.category" />
            <display:column title="通貨" property="currency.currency" />
            <display:column title="金額" property="amount" style="text-align: right" />
            <display:column title="備考" property="note" />
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
