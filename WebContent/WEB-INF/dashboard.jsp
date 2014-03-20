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
						<option value="0" selected="selected">by name</option>
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
				<!--<c:choose>
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
				</c:choose>-->
			</select>
			<input type="hidden" name="page" value="${1}" />
			<input type="hidden" name="nbDisplay" value="${wrapper.nbDisplay}"/>
			<input type="hidden" name="orderBy" value="${wrapper.orderBy}"/>
			<input type="hidden" name="ascendant" value="${wrapper.ascendant}"/>
			<input type="hidden" name="searchType" value="${wrapper.searchType}"/>
			<input type="hidden" name="searchMotif" value="${wrapper.searchMotif}"/>
			<input type="submit" id="searchsubmit" value="Filter" class="btn primary"> 
		</form>
		<a class="btn success" id="add"
			href="addcomputer?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">Add
			Computer</a>
	</div>


	<div id="tab-1" style="display: block;">
		<table>
			<thead>
				<tr>
					<th>Computer Id
						<c:choose>	
							<c:when test="${wrapper.orderBy == 1}">	
								<c:choose>
									<c:when test="${wrapper.ascendant}">
										<a class="" href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="/ComputerDataBase/resources/images/upArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
									</c:when>
									<c:otherwise>
										<a class="" href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=true&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="/ComputerDataBase/resources/images/downArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<a class="" href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=1&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="/ComputerDataBase/resources/images/downArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
							</c:otherwise>
						</c:choose>
					</th>
					<th>Computer Name 
						<c:choose>	
							<c:when test="${wrapper.orderBy == 2}">	
								<c:choose>
									<c:when test="${wrapper.ascendant}">
										<a class="arrow" href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="/ComputerDataBase/resources/images/upArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
									</c:when>
									<c:otherwise>
										<a class="arrow" href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=true&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="/ComputerDataBase/resources/images/downArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<a class="arrow" href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=2&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="/ComputerDataBase/resources/images/downArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
							</c:otherwise>
						</c:choose>
					</th>
					<th>Introduced Date 
						<c:choose>	
							<c:when test="${wrapper.orderBy == 3}">	
								<c:choose>
									<c:when test="${wrapper.ascendant}">
										<a class="arrow" href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="/ComputerDataBase/resources/images/upArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
									</c:when>
									<c:otherwise>
										<a class="arrow" href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=true&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="/ComputerDataBase/resources/images/downArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<a class="arrow" href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=3&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="/ComputerDataBase/resources/images/downArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
							</c:otherwise>
						</c:choose>
					</th>
					<th>Discontinued Date 
						<c:choose>	
							<c:when test="${wrapper.orderBy == 4}">	
								<c:choose>
									<c:when test="${wrapper.ascendant}">
										<a class="arrow" href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="/ComputerDataBase/resources/images/upArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
									</c:when>
									<c:otherwise>
										<a class="arrow" href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=true&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="/ComputerDataBase/resources/images/downArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<a class="arrow" href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=4&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="/ComputerDataBase/resources/images/downArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
							</c:otherwise>
						</c:choose>				
					</th>
					<th>Company 
						<c:choose>	
							<c:when test="${wrapper.orderBy == 5}">	
								<c:choose>
									<c:when test="${wrapper.ascendant}">
										<a class="arrow" href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="/ComputerDataBase/resources/images/upArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
									</c:when>
									<c:otherwise>
										<a class="arrow" href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=true&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="/ComputerDataBase/resources/images/downArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<a class="arrow" href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=5&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="/ComputerDataBase/resources/images/downArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
							</c:otherwise>
						</c:choose>
					</th>
					<th>Edition </th>
					<th>Removal </th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${wrapper.size != 0}">
					<c:forEach items="${ wrapper.pages }" var="computer">
						<tr>
							<td><c:out value="${ computer.id }"/></td>
							<td><c:out value="${ computer.name }" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${ computer.introduced }" /></td>
							<td><c:out value="${ computer.discontinued }" /></td>
							<td><c:out value="${ computer.company.name }" /></td>
							<td>
								<form action="editcomputer" method="GET">
									<input type="hidden" name="id" value="${computer.id}" />
									<input type="hidden" name="page" value="${wrapper.currentPage}" />
									<input type="hidden" name="nbDisplay" value="${wrapper.nbDisplay}"/>
									<input type="hidden" name="orderBy" value="${wrapper.orderBy}"/>
									<input type="hidden" name="ascendant" value="${wrapper.ascendant}"/>
									<input type="hidden" name="searchType" value="${wrapper.searchType}"/>
									<input type="hidden" name="searchMotif" value="${wrapper.searchMotif}"/>
									<input type="submit" value="Edit" class="btn success" id="editcomputer">
								</form>
							</td>
							<td>
								<form action="delcomputer" method="GET">
									<input type="hidden" name="id" value="${computer.id}" />
									<input type="hidden" name="page" value="${wrapper.currentPage}" />
									<input type="hidden" name="nbDisplay" value="${wrapper.nbDisplay}"/>
									<input type="hidden" name="orderBy" value="${wrapper.orderBy}"/>
									<input type="hidden" name="ascendant" value="${wrapper.ascendant}"/>
									<input type="hidden" name="searchType" value="${wrapper.searchType}"/>
									<input type="hidden" name="searchMotif" value="${wrapper.searchMotif}"/>
									<input type="submit" value="Delete" class="btn danger" id="delcomputer">
								</form>
							</td>
						</tr>
					</c:forEach>
				</c:if>				
			</tbody>
		</table>



		<table>
			<tr>
				<c:if test="${wrapper.currentPage != 1}">
					<td><a href="dashboard?page=${wrapper.currentPage - 1}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"
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
									<td><a href="dashboard?page=${i}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">${i}</a></td>
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
							<td><a href="dashboard?page=4&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">4</a></td>
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
										<td><a href="dashboard?page=${i}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">${i}</a></td>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<td>...</td>
						</c:if>
						<c:if test="${wrapper.currentPage eq wrapper.nbPages-4}">
							<td>...</td>
							<td>${wrapper.nbPages-4}</td>
							<td><a href="dashboard?page=${wrapper.nbPages-3}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">${wrapper.nbPages-3}</a></td>
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
									<td><a href="dashboard?page=${i}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">${i}</a></td>
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
									<td><a href="dashboard?page=${i}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">${i}</a></td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<c:if test="${wrapper.currentPage lt wrapper.nbPages}">
					<td><a href="dashboard?page=${wrapper.currentPage + 1}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"
						class="btn primary">Next</a></td>
				</c:if>
			</tr>
		</table>
		<form action="dashboard" id="nbcptform" method="POST">
			<span>Computers by pages : </span> 
			<select name="nbDisplay"	id="nbbpage" class="small">
				<c:choose>
					<c:when test="${ wrapper.nbDisplay == 25 }">
						<option id="opt0" value="25" selected="selected">25</option>
					</c:when>
					<c:otherwise>
						<option id="opt0" value="25">25</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ wrapper.nbDisplay == 50 }">
						<option id="opt1" value="50" selected="selected">50</option>
					</c:when>
					<c:otherwise>
						<option id="opt1" value="50">50</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ wrapper.nbDisplay == 75 }">
						<option id="opt2" value="75" selected="selected">75</option> 
					</c:when>
					<c:otherwise>
						<option id="opt2" value="75">75</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ wrapper.nbDisplay == 100 }">
						<option id="opt3" value="100" selected="selected">100</option>
					</c:when>
					<c:otherwise>
						<option id="opt3" value="100">100</option>
					</c:otherwise>
				</c:choose>
			</select> 
			<input type="hidden" name="page" value="${1}" />
			<input type="hidden" name="nbDisplay" value="${wrapper.nbDisplay}"/>
			<input type="hidden" name="orderBy" value="${wrapper.orderBy}"/>
			<input type="hidden" name="ascendant" value="${wrapper.ascendant}"/>
			<input type="hidden" name="searchType" value="${wrapper.searchType}"/>
			<input type="hidden" name="searchMotif" value="${wrapper.searchMotif}"/>
			<input type="submit" value="Filter" class="btn primary">
		</form>
	</div>
</section>

<jsp:include page="include/footer.jsp" />
