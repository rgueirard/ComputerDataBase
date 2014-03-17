<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<section id="main">

	<script src="http://code.jquery.com/jquery-2.1.0.js"
		type="text/javascript"></script>
	<script
		src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.10.0/jquery.validate.min.js"
		type="text/javascript"></script>
	<script
		src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.10.0/additional-methods.js"
		type="text/javascript"></script>

	<script src="../resources/jquery/scriptSearch.js"></script>
	<h1 id="homeTitle">
		<c:out value="${ size }" />
		Computers found
	</h1>
	<div id="actions">
		<form action="dashboard" id="searchform" method="POST">
			<input type="search" id="searchbox" name="search" value=""
				placeholder="Search"> <select name="searchtype"
				id="searchtype" class="small">
				<option value="0" selected="selected">by id</option>
				<option value="1">by name</option>
				<option value="2">by introduced date</option>
				<option value="3">by discontinued date</option>
				<option value="4">by company</option>
			</select> <input type="submit" id="searchsubmit" value="Filter"
				class="btn primary"> <span>Order By : </span> <select
				name="orderby" id="orderby" class="small">
				<option value="1" selected="selected">id</option>
				<option value="2">name</option>
				<option value="3">introduced date</option>
				<option value="4">discontinued date</option>
				<option value="5">company</option>
			</select>

		</form>
		<a class="btn success" id="add" href="addcomputer?page=${currentPage}">Add
			Computer</a>
	</div>


	<div id="tab-1" style="display: block;">
		<table id="sortable1" class="computers zebra-striped">
			<thead>
				<tr>
					<th>Computer Name</th>
					<th>Introduced Date</th>
					<th>Discontinued Date</th>
					<th>Company</th>
					<th>Edition</th>
					<th>Removal</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ computers }" var="computer">
					<tr>
						<td><c:out value="${ computer.name }" /></td>
						<td><fmt:formatDate pattern="yyyy-MM-dd"
								value="${ computer.introduced }" /></td>
						<td><c:out value="${ computer.discontinued }" /></td>
						<td><c:out value="${ computer.company.name }" /></td>
						<td>
							<form action="editcomputer?page=${currentPage}" method="GET">
								<input type="hidden" name="id" value="${computer.id}" /> <input
									type="submit" value="Edit" class="btn success"
									id="editcomputer">
							</form>
						</td>
						<td>
							<form action="delcomputer" method="GET">
								<input type="hidden" name="id" value="${computer.id}" />
								<input type="hidden" name="page" value="${currentPage}" /> 
								<input type="submit" value="Delete" class="btn danger" id="delcomputer">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>



		<table>
			<tr>
				<c:if test="${currentPage != 1}">
					<td><a href="dashboard?page=${currentPage - 1}"
						class="btn primary">Prev</a></td>
				</c:if>

				<c:forEach begin="1" end="${nbPages}" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							<td>${i}</td>
						</c:when>
						<c:otherwise>
							<td><a href="dashboard?page=${i}">${i}</a></td>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${currentPage lt nbPages}">
					<td><a href="dashboard?page=${currentPage + 1}"
						class="btn primary">Next</a></td>
				</c:if>
			</tr>
		</table>
	</div>


</section>

<jsp:include page="include/footer.jsp" />
