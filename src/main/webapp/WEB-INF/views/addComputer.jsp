<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	<h1>
		<c:choose>
			<c:when test="${edit=='true'}">
				<c:out value="Edit Computer"/>
			</c:when>
			<c:otherwise>
				<c:out value="Add Computer"/>
			</c:otherwise>		
		</c:choose>
	</h1>


	<form:form id="addComputer" action="/computer-database/computer/submit" onsubmit="return validateForm()" method="GET" modelAttribute="cptDTO">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<form:input id="nameInput" type="text" path="name" value="${ computer.name }"/>
					<span id="nameSpan" class="help-inline">Required</span>
					<form:errors path="name" cssClass="errorMessage"/>
				</div>
			</div>
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<form:input id="introducedInput" type="date" path="introduced" value="${computer.introduced}"/>
					<span id="introducedSpan" class="help-inline">YYYY-MM-DD</span>
					<form:errors path="introduced" cssClass="errorMessage"/>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<form:input id="discontinuedInput" path="discontinued" value="${computer.discontinued}"/>
					<span id="discontinuedSpan" class="help-inline">YYYY-MM-DD</span>
					<form:errors path="discontinued" cssClass="errorMessage"/>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<form:select path="companyId">
						<form:option value="0">--</form:option>
						<c:forEach items="${ companies }" var="company">
							<c:choose>
								<c:when test="${ company.id == computer.companyId }">
									<form:option value="${ company.id }" selected="selected">
										<c:out value="${ company.name }" />
									</form:option>
								</c:when>
								<c:otherwise>
									<form:option value="${ company.id }">
										<c:out value="${ company.name }" />
									</form:option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="hidden" name="nbDisplay" value="${wrapper.nbDisplay}">
			<input type="hidden" name="page" value="${wrapper.currentPage}">
			<input type="hidden" name="orderBy" value="${wrapper.orderBy}" />
			<input type="hidden" name="ascendant" value="${wrapper.ascendant}" />
			<input type="hidden" name="searchType" value="${wrapper.searchType}" /> 
			<input type="hidden" name="searchMotif" value="${wrapper.searchMotif}" />
			
			<c:choose>
				<c:when test="${edit=='true'}">
					<input type="hidden" name="edit" value="true"/>
					<input type="submit" id="validInput" value="Edit" class="btn primary">
				</c:when>
				<c:otherwise>
					<input type="hidden" name="edit" value="false"/>
					<input type="submit" id="validInput" value="Add" class="btn primary">
				</c:otherwise>		
			</c:choose>
				
			or <a
				href="/computer-database/computer/show?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&ascendant=${wrapper.ascendant}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"
				class="btn">Cancel</a>
		</div>
	</form:form>

	<!-- script de validation -->
	<script>
		/*function isValidDate(date) {
			var valid = false;
			var dateISO = new RegExp(
					"[0-9]{4}[-](0?[1-9]|1[012])[-](0?[1-9]|[12][0-9]|3[01])");

			if (date.match(dateISO)) {
				date = date.replace('/-/g', '');
				var year = parseInt(date.substring(0, 4), 10);
				var month = parseInt(date.substring(4, 6), 10);
				var day = parseInt(date.substring(6, 8), 10);

				if ((year > 999) && (year < 10000)) {
					if ((month > 0) && (month < 13)) {
						if (day > 0) {
							if ((month == 12) && (day <= 31)) {
								valid = true;
							} else if ((month == 11) && (day <= 30)) {
								valid = true;
							} else if ((month == 10) && (day <= 31)) {
								valid = true;
							} else if ((month == 9) && (day <= 30)) {
								valid = true;
							} else if ((month == 8) && (day <= 31)) {
								valid = true;
							} else if ((month == 7) && (day <= 31)) {
								valid = true;
							} else if ((month == 6) && (day <= 30)) {
								valid = true;
							} else if ((month == 5) && (day <= 31)) {
								valid = true;
							} else if ((month == 4) && (day <= 30)) {
								valid = true;
							} else if ((month == 3) && (day <= 31)) {
								valid = true;
							} else if ((month == 2)
									&& ((((year % 4) == 0) && (day <= 29)) || (((year % 4) != 0) && (day <= 28)))) {
								valid = true;
							} else if ((month == 1) && (day <= 31)) {
								valid = true;
							}
						}
					}
				}
			}
			return valid;
		}

		function discLTInt(intDate, discDate) {
			var lesserThan = false;

			intDate = intDate.replace('/-/g', '');

			var intYear = parseInt(intDate.substring(0, 4), 10);
			var intMonth = parseInt(intDate.substring(4, 6), 10);
			var intDay = parseInt(intDate.substring(6, 8), 10);
			var discYear = parseInt(discDate.substring(0, 4), 10);
			var discMonth = parseInt(discDate.substring(4, 6), 10);
			var discDay = parseInt(discDate.substring(6, 8), 10);

			if (discYear < intYear) {
				lesserThan = true;
			} else if (discYear == intYear) {
				if (discMonth < intMonth) {
					lesserThan = true;
				} else if (discMonth == intMonth) {
					if (discDay < intDay) {
						lesserThan = true;
					}
				}
			}
			return lesserThan;
		}

		function validateForm() {
			var nameExc = new RegExp(".*[<|>|\"]+.*");
			var image = document.createElement("img");
			var error = true;
			var dateError = false;
			image.setAttribute("src",
					"/ComputerDataBase/resources/images/unchecked.gif");
			image.setAttribute("border", "0");
			image.setAttribute("width", "14");
			image.setAttribute("height", "14");

			var cptName = document.forms["addComputer"]["nameInput"].value;
			var intDate = document.forms["addComputer"]["introducedInput"].value;
			var discDate = document.forms["addComputer"]["discontinuedInput"].value;
			var errName = document.getElementById("dName");
			var errInv = document.getElementById("dInv");
			var errInt = document.getElementById("dIntroduced");
			var errDisc = document.getElementById("dDsic");
			var errLT = document.getElementById("dLesserThan");

			if (cptName == null || cptName == "") {
				errName.style.display = "";
				error = false;
			} else {
				errName.style.display = "none";
				if (cptName.match(nameExc)) {
					alert("test0");

					errInv.style.display = "";
					error = false;
				} else {
					errInv.style.display = "none";
				}
			}
			alert("test1");
			if (((intDate != null) && (intDate != ""))
					&& (!isValidDate(intDate))) {
				errInt.style.display = "";
				error = false;
				dateError = true;
			} else {
				errInt.style.display = "none";
			}
			alert("test2");
			if (((discDate != null) && (discDate != ""))
					&& (!isValidDate(discDate))) {
				errDisc.style.display = "";
				error = false;
				dateError = true;
			} else {
				errDisc.style.display = "none";
			}
			alert("test3");
			if ((!dateError) && (isValidDate(discDate))
					&& (isValidDate(intDate))) {
				if (discLTInt(intDate, discDate)) {
					errLT.style.display = "";
					error = false;
				}
			} else {
				errLT.style.display = "none";
			}
			return error;
		}*/
	</script>
</section>
<jsp:include page="include/footer.jsp" />