<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section id="main">
	<script src="http://code.jquery.com/jquery-2.1.0.js"
		type="text/javascript"></script>
	<script
		src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.10.0/jquery.validate.min.js"
		type="text/javascript"></script>
	<script
		src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.10.0/additional-methods.js"
		type="text/javascript"></script>


	<h1>Add Computer</h1>


	<form id="addComputer" action="addcomputer"
		onsubmit="return validateForm()" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input type="text" name="name" id="nameInput"
						value="<c:out value="${ computer.name }"/>" />
					<c:if
						test='${(error.computerName != null)&&(error.computerName != "")}'>
						<img
							src="/ComputerDBMaven/src/main/resources/images/unchecked.gif"
							border=0 width=14 height=14>
						<c:out value="${error.computerName}" />
						<span id="dName" style="display: none"> <img
							src="/ComputerDBMaven/src/main/resources/images/unchecked.gif"
							border=0 width=14 height=14> <c:out
								value="Please enter a computer name. ou PAS" />
						</span>
					</c:if>
					<c:if
						test='${(error.invalidChar != null)&&(error.invalidChar != "")}'>
						<img
							src="/ComputerDBMaven/src/main/resources/images/unchecked.gif"
							border=0 width=14 height=14>
						<c:out value="${error.invalidChar}" />
						<span id="dInv" style="display: none"> <img
							src="/ComputerDBMaven/src/main/resources/images/unchecked.gif"
							border=0 width=14 height=14> <c:out
								value="Invalid character \'&lt\', \'&gt\' or \'\"\' !" />
						</span>
					</c:if>
					<span id="nameSpan" class="help-inline">Required</span>
				</div>
			</div>

			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" name="introducedDate" id="introducedInput"
						value="<c:out value="${ computer.introduced.toString() }"/>" />
					<c:if
						test='${(error.validIntroducedDate != null)&&(error.validIntroducedDate != "")}'>
						<img
							src="/ComputerDBMaven/src/main/resources/images/unchecked.gif"
							border=0 width=14 height=14>
						<c:out value="${error.validIntroducedDate}" />
						<span id="dIntroduced" style="display: none"> <img
							src="/ComputerDBMaven/src/main/resources/images/unchecked.gif"
							border=0 width=14 height=14> <c:out
								value="Please enter a valid introduced date." />
						</span>
					</c:if>
					<span id="introducedSpan" class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" name="discontinuedDate" id="discontinuedInput"
						value="<c:out value="${ computer.discontinued.toString() }"/>" />
					<c:if
						test='${(error.validDiscontinuedDate != null)&&(error.validDiscontinuedDate != "")}'>
						<img
							src="/ComputerDBMaven/src/main/resources/images/unchecked.gif"
							border=0 width=14 height=14>
						<c:out value="${error.validDiscontinuedDate}" />
						<span id="dDiscontinued" style="display: none"> <img
							src="/ComputerDBMaven/src/main/resources/images/unchecked.gif"
							border=0 width=14 height=14> <c:out
								value="Please enter a valid discontinued date." />
						</span>
					</c:if>
					<c:if
						test='${(error.lesserThan != null)&&(error.lesserThan != "")}'>
						<img
							src="/ComputerDBMaven/src/main/resources/images/unchecked.gif"
							border=0 width=14 height=14>
						<c:out value="${error.lesserThan}" />
						<span id="dLesserThan" style="display: none"> <img
							src="/ComputerDBMaven/src/main/resources/images/unchecked.gif"
							border=0 width=14 height=14> <c:out
								value="Discontinued date is earlier than introduced date !" />
						</span>
					</c:if>
					<span id="discontinuedSpan" class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company">
						<option value="0">--</option>
						<c:forEach items="${ companies }" var="company">
							<c:choose>
								<c:when test="${ company.id == computer.companyId }">
									<option value="<c:out value="${ company.id }" />"
										selected="selected">
										<c:out value="${ company.name }" />
									</option>
								</c:when>
								<c:otherwise>
									<option value="<c:out value="${ company.id }" />">
										<c:out value="${ company.name }" />
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="hidden" name="nbDisplay" value="${wrapper.nbDisplay}">
			<input type="hidden" name="page" value="${wrapper.currentPage}">
			<input type="hidden" name="orderBy" value="${wrapper.orderBy}" /> <input
				type="hidden" name="searchType" value="${wrapper.searchType}" /> <input
				type="hidden" name="searchMotif" value="${wrapper.searchMotif}" />
			<input type="submit" id="validInput" value="Add" class="btn primary">
			or <a
				href="dashboard?page=${wrapper.currentPage}&nbDisplay=${wrapper.nbDisplay}&orderBy=${wrapper.orderBy}&searchType=${wrapper.searchType}&searchMotif=${wrapper.searchMotif}"
				class="btn">Cancel</a>
		</div>
	</form>

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