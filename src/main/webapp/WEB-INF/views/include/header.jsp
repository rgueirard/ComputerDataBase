<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>EPF Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="../css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<c:set var="localeCode" value="${pageContext.response.locale}" />
	<header class="topbar">
		<h1 class="fill">
			<a href="/computer-database/computer/show?lang=fr"> Application - Computer Database </a>
			<c:choose>
			<c:when test="${localeCode=='en'}">
				<span class="language">En|<a href="?id=${cptDTO.id}&page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}&edit=${edit}&lang=fr">Fr</a></span>
			</c:when>
			<c:otherwise>
				<span class="language"><a href="?id=${cptDTO.id}&page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}&edit=${edit}&lang=en">En</a>|Fr</span>
			</c:otherwise>
		</c:choose>
		</h1>
	</header>