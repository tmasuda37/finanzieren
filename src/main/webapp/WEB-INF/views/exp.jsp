<%@ page language="java" contentType="text/html; charset=utf-8"
  pageEncoding="utf-8"%>
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
    <form:form commandName="regWalletRecord">
      <table width="346" border="1">
        <tr>
          <td width="165"><label for="wlamount">金額<br>
          </label> <input type="text" name="wlamount" id="wlamount"></td>
          <td width="165"><label for="wlcurrency">通貨<br>
          </label> <select name="wlcurrency" id="wlcurrency">
          </select></td>
        </tr>
        <tr>
          <td><label for="wlcategory">分類<br>
          </label> <select name="wlcategory" id="wlcategory">
          </select></td>
          <td><label for="wldate">日付<br></label> <select
            name="wldate" id="wldate">
          </select></td>
        </tr>
        <tr>
          <td colspan="2"><label for="wlnote">備考<br>
          </label> <textarea name="wlnote" id="wlnote" cols="45" rows="5"></textarea></td>
        </tr>
        <tr>
          <td><input type="submit" name="submit" id="submit"
            value="支出登録"></td>
          <td><input type="reset" name="reset" id="reset"
            value="クリア"></td>
        </tr>
      </table>
    </form:form>
  </div>
  <div class="#bottom" id="bottom">Copyright (C) Toshifumi Masuda</div>
</body>
</html>
