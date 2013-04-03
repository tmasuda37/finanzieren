<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/static/resources/css/style.css"/>" rel="stylesheet" type="text/css">
<script src="https://use.edgefonts.net/alegreya-sc.js"></script>
<script src="<c:url value="/static/resources/js/input.js"/>" type="text/javascript"></script>

</head>
<body>
  <jsp:include page="/WEB-INF/views/menu.jsp" />
  <div id="center">
    <p>
      <strong>【転入】...転入のトランザクションを入力します</strong>
    </p>
    <form:form commandName="regWalletRecord">
      <form:hidden path="kind.id" value="4" />
      <table width="346" border="1">
        <tr>
          <td width="165">
            <label for="amount">
              金額
              <br>
            </label>
            <form:input path="amount" />
            <form:errors class="invalid" path="amount" />
          </td>
          <td width="165">
            <label for="currency">
              通貨
              <br>
            </label>
            <form:select path="currency.id" size="${listWlcurrency.size()}">
              <form:options items="${listWlcurrency}" itemValue="id" itemLabel="currency" />
            </form:select>
          </td>
        </tr>
        <tr>
          <td>
            <label for="category">
              分類
              <br>
            </label>
            <form:select path="category.id" size="${listWlcategory.size()}">
              <form:options items="${listWlcategory}" itemValue="id" itemLabel="category" />
            </form:select>
          </td>
          <td>
            <label for="date">
              日付
              <br>
            </label>
            <form:select path="date" size="${listWlDate.size()}">
              <form:options items="${listWlDate}" />
            </form:select>
            <form:errors class="invalid" path="date" />
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <label for="note">
              備考
              <br>
            </label>
            <span id="noteinfo"></span>
            <form:textarea path="note" id="note" cols="45" rows="5" onkeyup="showLength(this);" />
          </td>
        </tr>
        <tr>
          <td>
            <input type="submit" name="submit" id="submit" value="転入登録" />
          </td>
          <td>
            <input type="reset" name="reset" id="reset" value="クリア" />
          </td>
        </tr>
      </table>
    </form:form>
  </div>
  <div class="#bottom" id="bottom">Copyright (C) Toshifumi Masuda</div>
</body>
</html>
