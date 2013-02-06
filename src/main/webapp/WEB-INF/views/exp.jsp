<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>支出登録</title>
<link href="<c:url value="/static/resources/css/style.css"/>"
  rel="stylesheet" type="text/css">
<script src="http://use.edgefonts.net/alegreya-sc.js"></script>
</head>
<body>
  <div id="header">finanzieren</div>
  <div id="center">
    <p>
      <strong>支出登録 － あなたの財布から支出した記録を残しましょう</strong>
    </p>
    <form:form commandName="regWalletRecord" id="reg">
      <table width="346" border="1">
        <tr>
          <td width="165">
            <label for="wlAmount">
              金額
              <br>
            </label>
            <form:input path="wlAmount" />
          </td>
          <td width="165">
            <label for="wlCurrency">
              通貨
              <br>
            </label>
<%--             <form:select path="wlCurrency">
              <form:options items="listWlcurrency" />
            </form:select>
 --%>          </td>
        </tr>
        <tr>
          <td>
            <label for="wlCategory">
              分類
              <br>
            </label>
            <form:select path="wlCategory">
              <form:options items="${listWlcategory}" itemValue="id" itemLabel="category"/>
            </form:select>
          </td>
          <td>
            <label for="wlDate">
              日付
              <br>
            </label>
<%--             <form:select path="wlDate">
               <form:options items="listWldate" />
             </form:select>
 --%>          </td>
        </tr>
        <tr>
          <td colspan="2">
            <label for="wlNote">
              備考
              <br>
            </label>
            <form:textarea path="wlNote" id="wlNote" cols="45" rows="5" />
          </td>
        </tr>
        <tr>
          <td>
            <input type="submit" name="submit" id="submit" value="支出登録" />
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
