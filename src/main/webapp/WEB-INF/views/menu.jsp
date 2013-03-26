<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="header">
  <a href="<c:url value="/"/>">finanzieren</a>
  <div id="menu">
    <ul>
      <li><a href="<c:url value="/list"/>">一覧</a></li>
      <li><a href="<c:url value="/summary"/>">月次</a></li>
      <li><a href="<c:url value="/exp"/>">支出</a></li>
      <li><a href="<c:url value="/inc"/>">収入</a></li>
      <li><a href="<c:url value="/tro"/>">転出</a></li>
      <li><a href="<c:url value="/tri"/>">転入</a></li>
    </ul>
  </div>
</div>