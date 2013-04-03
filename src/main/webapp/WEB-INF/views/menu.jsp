<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>FINANZIEREN</title>
<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-37342953-3' ]);
	_gaq.push([ '_trackPageview' ]);

	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
</script>
<div id="header">
  <a href="<c:url value="/"/>">FINANZIEREN</a>
  <div id="menu">
    <ul>
      <li><a href="<c:url value="/list/edit"/>">編集</a></li>
      <li><a href="<c:url value="/exp"/>">支出</a></li>
      <li><a href="<c:url value="/inc"/>">収入</a></li>
      <li><a href="<c:url value="/tro"/>">転出</a></li>
      <li><a href="<c:url value="/tri"/>">転入</a></li>
      <li><a href="<c:url value="/setting"/>">設定</a></li>
    </ul>
  </div>
</div>