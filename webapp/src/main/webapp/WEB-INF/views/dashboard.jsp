<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="pagination" prefix="page"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
		<spring:message code="dash.lab.cpt" text="com.err.text" />
	</h1>
	<div id="actions">
		<form action="/computer-database/computer/show" id="searchform" method="GET">
			<input type="search" id="searchbox" name="searchMotif" value="${wrapper.searchMotif}" placeholder="<spring:message code="dash.search.default" text="com.err.text" />"> 
			<select name="searchType" id="searchtype" class="small">
				<c:choose>
					<c:when test="${ wrapper.searchType == 0 }">
						<option value="0" selected="selected"><spring:message code="dash.search.cpt" text="com.err.text" /></option>
					</c:when>
					<c:otherwise>
						<option value="0"><spring:message code="dash.search.cpt" text="com.err.text" /></option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ wrapper.searchType == 1 }">
						<option value="1"	selected="selected"><spring:message code="dash.search.cpny" text="com.err.text" /></option>
					</c:when>
					<c:otherwise>
						<option value="1"><spring:message code="dash.search.cpny" text="com.err.text" /></option>
					</c:otherwise>
				</c:choose>
			</select>
			<input type="hidden" name="page" value="${1}" />
			<input type="hidden" name="nbDisplay" value="${wrapper.nbDisplay}"/>
			<input type="hidden" name="orderBy" value="${wrapper.orderBy}"/>
			<input type="hidden" name="ascendant" value="${wrapper.ascendant}"/>
			<input type="submit" id="searchsubmit" value="<spring:message code="dash.search.button" text="com.err.text" />" class="btn primary"> 
		</form>
		<a class="btn success" id="add"
			href="/computer-database/computer/add?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><spring:message code="com.title.add" text="com.err.text" /></a>
	</div>


	<div id="tab-1" style="display: block;">
		<table class="computers zebra-striped">
			<thead>
				<tr>
					<th><spring:message code="dash.lab.id" text="com.err.text" />
						<c:choose>	
							<c:when test="${wrapper.orderBy == 1}">	
								<c:choose>
									<c:when test="${wrapper.ascendant}">
										<a href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="../img/upArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
									</c:when>
									<c:otherwise>
										<a href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=true&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="../img/downArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<a href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=1&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="../img/downArrow.png" border=0 width=16 height=16 alt="Computer Id"></a>
							</c:otherwise>
						</c:choose>
					</th>
					<th><spring:message code="dash.lab.name" text="com.err.text" />
						<c:choose>	
							<c:when test="${wrapper.orderBy == 2}">	
								<c:choose>
									<c:when test="${wrapper.ascendant}">
										<a href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="../img/upArrow.png" border=0 width=16 height=16 alt="Computer Name"></a>
									</c:when>
									<c:otherwise>
										<a href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=true&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="../img/downArrow.png" border=0 width=16 height=16 alt="Computer Name"></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<a href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=2&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="../img/downArrow.png" border=0 width=16 height=16 alt="Computer Name"></a>
							</c:otherwise>
						</c:choose>
					</th>
					<th><spring:message code="dash.lab.int" text="com.err.text" />
						<c:choose>	
							<c:when test="${wrapper.orderBy == 3}">	
								<c:choose>
									<c:when test="${wrapper.ascendant}">
										<a href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="../img/upArrow.png" border=0 width=16 height=16 alt="Introduced Date"></a>
									</c:when>
									<c:otherwise>
										<a href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=true&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="../img/downArrow.png" border=0 width=16 height=16 alt="Introduced Date"></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<a href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=3&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="../img/downArrow.png" border=0 width=16 height=16 alt="Introduced Date"></a>
							</c:otherwise>
						</c:choose>
					</th>
					<th><spring:message code="dash.lab.disc" text="com.err.text" />
						<c:choose>	
							<c:when test="${wrapper.orderBy == 4}">	
								<c:choose>
									<c:when test="${wrapper.ascendant}">
										<a href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="../img/upArrow.png" border=0 width=16 height=16 alt="Discontinued Date"></a>
									</c:when>
									<c:otherwise>
										<a href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=true&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="../img/downArrow.png" border=0 width=16 height=16 alt="Discontinued Date"></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<a href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=4&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="../img/downArrow.png" border=0 width=16 height=16 alt="Discontinued Date"></a>
							</c:otherwise>
						</c:choose>				
					</th>
					<th><spring:message code="dash.lab.cpny" text="com.err.text" />
						<c:choose>	
							<c:when test="${wrapper.orderBy == 5}">	
								<c:choose>
									<c:when test="${wrapper.ascendant}">
										<a href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="../img/upArrow.png" border=0 width=16 height=16 alt="Company"></a>
									</c:when>
									<c:otherwise>
										<a href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=true&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="../img/downArrow.png" border=0 width=16 height=16 alt="Company"></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<a href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=5&ascendant=false&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"><img src="../img/downArrow.png" border=0 width=16 height=16 alt="Company"></a>
							</c:otherwise>
						</c:choose>
					</th>
					<th><spring:message code="dash.lab.edit" text="com.err.text" /></th>
					<th><spring:message code="dash.lab.del" text="com.err.text" /></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${wrapper.size != 0}">
					<c:forEach items="${ wrapper.pages }" var="computer">
						<tr>
							<td><c:out value="${ computer.id }"/></td>
							<td><c:out value="${ computer.name }" /></td>
							<td><c:out value="${ computer.introduced }"/></td>
							<td><c:out value="${ computer.discontinued }" /></td>
							<td><c:out value="${ computer.companyName }" /></td>
							<td>
								<form action="/computer-database/computer/add" method="GET">
									<input type="hidden" name="id" value="${computer.id}" />
									<input type="hidden" name="page" value="${wrapper.currentPage}" />
									<input type="hidden" name="nbDisplay" value="${wrapper.nbDisplay}"/>
									<input type="hidden" name="orderBy" value="${wrapper.orderBy}"/>
									<input type="hidden" name="ascendant" value="${wrapper.ascendant}"/>
									<input type="hidden" name="searchType" value="${wrapper.searchType}"/>
									<input type="hidden" name="searchMotif" value="${wrapper.searchMotif}"/>
									<input type="hidden" name="edit" value="true"/>
									<input type="submit" value="<spring:message code="dash.button.edit" text="com.err.text" />" class="btn success" id="editcomputer">
								</form>
							</td>
							<td>
								<form action="/computer-database/computer/del" method="GET">
									<input type="hidden" name="id" value="${computer.id}" />
									<input type="hidden" name="page" value="${wrapper.currentPage}" />
									<input type="hidden" name="nbDisplay" value="${wrapper.nbDisplay}"/>
									<input type="hidden" name="orderBy" value="${wrapper.orderBy}"/>
									<input type="hidden" name="ascendant" value="${wrapper.ascendant}"/>
									<input type="hidden" name="searchType" value="${wrapper.searchType}"/>
									<input type="hidden" name="searchMotif" value="${wrapper.searchMotif}"/>
									<input type="submit" value="<spring:message code="dash.button.del" text="com.err.text" />" class="btn danger" id="delcomputer">
								</form>
							</td>
						</tr>
					</c:forEach>
				</c:if>				
			</tbody>
		</table>

		<page:pagination wrapper="${wrapper}"/>

		<form action="/computer-database/computer/show" id="nbcptform" method="GET">
			<span><spring:message code="dash.lab.nbpage" text="com.err.text" /></span> 
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
			<input type="submit" value="<spring:message code="dash.search.button" text="com.err.text" />" class="btn primary">
		</form>
	</div>
</section>

<jsp:include page="include/footer.jsp" />
