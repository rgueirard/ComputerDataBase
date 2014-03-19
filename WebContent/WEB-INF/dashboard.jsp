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
		<c:out value="${ wrapper.size }" />
		Computers found
	</h1>
	<div id="actions">
		<form action="dashboard" id="searchform" method="POST">
			<input type="search" id="searchbox" name="searchMotif" value="${wrapper.searchMotif}" placeholder="Search"> 
			<select name="searchType" id="searchtype" class="small">
				<c:choose>
					<c:when test="${ wrapper.searchType == 0 }">
						<option value="0"	selected="selected">by name</option>
					</c:when>
					<c:otherwise>
						<option value="0">by name</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ wrapper.searchType == 1 }">
						<option value="1"	selected="selected">by company</option>
					</c:when>
					<c:otherwise>
						<option value="1">by company</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ wrapper.searchType == 2 }">
						<option value="2"	selected="selected">by id</option>
					</c:when>
					<c:otherwise>
						<option value="2">by id</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ wrapper.searchType == 3 }">
						<option value="3"	selected="selected">by introduced date</option>
					</c:when>
					<c:otherwise>
						<option value="3">by introduced date</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ wrapper.searchType == 4 }">
						<option value="4"	selected="selected">by discontinued date</option>
					</c:when>
					<c:otherwise>
						<option value="4">by discontinued date</option>
					</c:otherwise>
				</c:choose>
			</select>
			<input type="hidden" name="page" value="${wrapper.currentPage}" />
			<input type="hidden" name="nbByPage" value="${wrapper.nbDisplay}"/>
			<input type="hidden" name="orderBy" value="${wrapper.orderBy}"/>
			<input type="hidden" name="searchType" value="${wrapper.searchType}"/>
			<input type="hidden" name="searchMotif" value="${wrapper.searchMotif}"/>
			<input type="submit" id="searchsubmit" value="Filter" class="btn primary"> 
			<span>Order By : </span> 
			<select	name="orderBy" id="orderby" class="small">
				<c:choose>
					<c:when test="${ wrapper.orderBy == 1 }">
						<option value="1"	selected="selected">id</option>
					</c:when>
					<c:otherwise>
						<option value="1">id</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ wrapper.orderBy == 2 }">
						<option value="2"	selected="selected">name</option>
					</c:when>
					<c:otherwise>
						<option value="2">name</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ wrapper.orderBy == 3 }">
						<option value="3"	selected="selected">introduced</option>
					</c:when>
					<c:otherwise>
						<option value="3">introduced</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ wrapper.orderBy == 4 }">
						<option value="4" selected="selected">discontinued</option>
					</c:when>
					<c:otherwise>
						<option value="4">discontinued</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ wrapper.orderBy == 5 }">
						<option value="5"	selected="selected">company</option>
					</c:when>
					<c:otherwise>
						<option value="5">company</option>
					</c:otherwise>
				</c:choose>
			</select>

		</form>
		<a class="btn success" id="add"
			href="addcomputer?page=${wrapper.currentPage}&nbByPage=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">Add
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
				<c:forEach items="${ wrapper.computers }" var="computer">
					<tr>
						<td><c:out value="${ computer.name }" /></td>
						<td><fmt:formatDate pattern="yyyy-MM-dd"
								value="${ computer.introduced }" /></td>
						<td><c:out value="${ computer.discontinued }" /></td>
						<td><c:out value="${ computer.company.name }" /></td>
						<td>
							<form action="editcomputer" method="GET">
								<input type="hidden" name="id" value="${computer.id}" />
								<input type="hidden" name="page" value="${wrapper.currentPage}" />
								<input type="hidden" name="nbByPage" value="${wrapper.nbDisplay}"/>
								<input type="hidden" name="orderBy" value="${wrapper.orderBy}"/>
								<input type="hidden" name="searchType" value="${wrapper.searchType}"/>
								<input type="hidden" name="searchMotif" value="${wrapper.searchMotif}"/>
								<input type="submit" value="Edit" class="btn success" id="editcomputer">
							</form>
						</td>
						<td>
							<form action="delcomputer" method="GET">
								<input type="hidden" name="id" value="${computer.id}" />
								<input type="hidden" name="page" value="${wrapper.currentPage}" />
								<input type="hidden" name="nbByPage" value="${wrapper.nbDisplay}"/>
								<input type="hidden" name="orderBy" value="${wrapper.orderBy}"/>
								<input type="hidden" name="searchType" value="${wrapper.searchType}"/>
								<input type="hidden" name="searchMotif" value="${wrapper.searchMotif}"/>
								<input type="submit" value="Delete" class="btn danger" id="delcomputer">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>



		<table>
			<tr>
				<c:if test="${wrapper.currentPage != 1}">
					<td><a href="dashboard?page=${wrapper.currentPage - 1}&nbByPage=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"
						class="btn primary">Prev</a></td>
				</c:if>
				<c:choose>
					<c:when test="${wrapper.nbPages gt 10}">
						<c:forEach begin="1" end="3" var="i">
							<c:choose>
								<c:when test="${wrapper.currentPage eq i}">
									<td>${i}</td>
								</c:when>
								<c:otherwise>
									<td><a href="dashboard?page=${i}&nbByPage=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">${i}</a></td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${wrapper.currentPage lt 4}">
							<td>...</td>
						</c:if>
						<c:if test="${wrapper.currentPage eq 4}">
							<td>4</td>
							<td>...</td>
						</c:if>
						<c:if test="${wrapper.currentPage eq 5}">
							<td><a href="dashboard?page=4&nbByPage=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">4</a></td>
							<td>5</td>
							<td>...</td>
						</c:if>
						<c:if test="${wrapper.currentPage gt 5 and wrapper.currentPage lt wrapper.nbPages-4}">
							<td>...</td>
							<c:forEach begin="${wrapper.currentPage-1}" end="${wrapper.currentPage+1}"
								var="i">
								<c:choose>
									<c:when test="${wrapper.currentPage eq i}">
										<td>${i}</td>
									</c:when>
									<c:otherwise>
										<td><a href="dashboard?page=${i}&nbByPage=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">${i}</a></td>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<td>...</td>
						</c:if>
						<c:if test="${wrapper.currentPage eq wrapper.nbPages-4}">
							<td>...</td>
							<td>${wrapper.nbPages-4}</td>
							<td><a href="dashboard?page=${wrapper.nbPages-3}&nbByPage=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">${wrapper.nbPages-3}</a></td>
						</c:if>
						<c:if test="${wrapper.currentPage eq wrapper.nbPages-3}">
							<td>...</td>
							<td>${wrapper.nbPages-3}</td>
						</c:if>
						<c:if test="${wrapper.currentPage gt wrapper.nbPages-3}">
							<td>...</td>
						</c:if>
						<c:forEach begin="${wrapper.nbPages-2}" end="${wrapper.nbPages}" var="i">
							<c:choose>
								<c:when test="${wrapper.currentPage eq i}">
									<td>${i}</td>
								</c:when>
								<c:otherwise>
									<td><a href="dashboard?page=${i}&nbByPage=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">${i}</a></td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach begin="1" end="${wrapper.nbPages}" var="i">
							<c:choose>
								<c:when test="${wrapper.currentPage eq i}">
									<td>${i}</td>
								</c:when>
								<c:otherwise>
									<td><a href="dashboard?page=${i}&nbByPage=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">${i}</a></td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<c:if test="${wrapper.currentPage lt wrapper.nbPages}">
					<td><a href="dashboard?page=${wrapper.currentPage + 1}&nbByPage=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"
						class="btn primary">Next</a></td>
				</c:if>
			</tr>
		</table>
		<form action="dashboard" id="nbcptform" method="GET">
			<span>Computers by pages : </span> 
			<select name="nbByPage"	id="nbbpage" class="small">
				<c:choose>
					<c:when test="${ wrapper.nbDisplay == 0 }">
						<option id="opt0" value="0"	selected="selected">25</option>
					</c:when>
					<c:otherwise>
						<option id="opt0" value="0">25</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ wrapper.nbDisplay == 1 }">
						<option id="opt1" value="1"	selected="selected">50</option>
					</c:when>
					<c:otherwise>
						<option id="opt1" value="1">50</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ wrapper.nbDisplay == 2 }">
						<option id="opt2" value="2"	selected="selected">75</option>
					</c:when>
					<c:otherwise>
						<option id="opt2" value="2">75</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ wrapper.nbDisplay == 3 }">
						<option id="opt3" value="3"	selected="selected">100</option>
					</c:when>
					<c:otherwise>
						<option id="opt3" value="3">100</option>
					</c:otherwise>
				</c:choose>
			</select> 
			<input type="hidden" name="page" value="${wrapper.currentPage}" />
			<input type="hidden" name="nbByPage" value="${wrapper.nbDisplay}"/>
			<input type="hidden" name="orderBy" value="${wrapper.orderBy}"/>
			<input type="hidden" name="searchType" value="${wrapper.searchType}"/>
			<input type="hidden" name="searchMotif" value="${wrapper.searchMotif}"/>
			<input type="submit" value="Filter" class="btn primary">
		</form>
	</div>
	<script>
		if(wrapper.nbDisplay==0){
			$("#opt0").attr("selected", "selected");
		} else {
			if (wrapper.nbDisplay==1) {
				$("#opt1").attr("selected", "selected");
			} else {
				if (wrapper.nbDisplay==2) {
					$("#opt2").attr("selected", "selected");
				} else {
					$("#opt3").attr("selected", "selected");
				}
			}
		} 
	</script>

</section>

<jsp:include page="include/footer.jsp" />
