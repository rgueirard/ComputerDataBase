<%@ tag body-content="empty" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="wrapper" required="true" type="com.excilys.rgueirard.wrapper.PageWrapper" %>
<table>
	<tr>
		<c:if test="${wrapper.currentPage != 1}">
			<td><a
				href="/computer-database/computer/show?page=${wrapper.currentPage - 1}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"
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
							<td><a
								href="/computer-database/computer/show?page=${i}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">${i}</a></td>
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
					<td><a
						href="/computer-database/computer/show?page=4&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">4</a></td>
					<td>5</td>
					<td>...</td>
				</c:if>
				<c:if
					test="${wrapper.currentPage gt 5 and wrapper.currentPage lt wrapper.nbPages-4}">
					<td>...</td>
					<c:forEach begin="${wrapper.currentPage-1}"
						end="${wrapper.currentPage+1}" var="i">
						<c:choose>
							<c:when test="${wrapper.currentPage eq i}">
								<td>${i}</td>
							</c:when>
							<c:otherwise>
								<td><a
									href="/computer-database/computer/show?page=${i}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">${i}</a></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<td>...</td>
				</c:if>
				<c:if test="${wrapper.currentPage eq wrapper.nbPages-4}">
					<td>...</td>
					<td>${wrapper.nbPages-4}</td>
					<td><a
						href="/computer-database/computer/show?page=${wrapper.nbPages-3}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">${wrapper.nbPages-3}</a></td>
				</c:if>
				<c:if test="${wrapper.currentPage eq wrapper.nbPages-3}">
					<td>...</td>
					<td>${wrapper.nbPages-3}</td>
				</c:if>
				<c:if test="${wrapper.currentPage gt wrapper.nbPages-3}">
					<td>...</td>
				</c:if>
				<c:forEach begin="${wrapper.nbPages-2}" end="${wrapper.nbPages}"
					var="i">
					<c:choose>
						<c:when test="${wrapper.currentPage eq i}">
							<td>${i}</td>
						</c:when>
						<c:otherwise>
							<td><a
								href="/computer-database/computer/show?page=${i}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">${i}</a></td>
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
							<td><a
								href="/computer-database/computer/show?page=${i}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}">${i}</a></td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<c:if test="${wrapper.currentPage lt wrapper.nbPages}">
			<td><a
				href="/computer-database/computer/show?page=${wrapper.currentPage + 1}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"
				class="btn primary">Next</a></td>
		</c:if>
	</tr>
</table>