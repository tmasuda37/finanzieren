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
      <strong>【設定】...新分類名を追加することができます</strong>
    </p>
    <form:form commandName="regCategoryRecord">
      <table width="346" border="1">
        <tr>
          <td width="165">
            <label for="category">
              分類名
              <br>
            </label>
            <form:input path="category" />
            <form:errors class="invalid" path="category" />
          </td>
          <td width="165">
            <label for="currency">
              対象処理
              <br>
            </label>
            <form:select path="kind.id" size="${listKind.size()}">
              <form:options items="${listKind}" itemValue="id" itemLabel="kind" />
            </form:select>
          </td>
        </tr>
        <tr>
          <td>
            <input type="submit" name="submit" id="submit" value="分類登録" />
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
