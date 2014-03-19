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


	<form id="addComputer" action="addcomputer" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input type="text" name="name" id="nameInput" /> <span
						id="nameSpan" class="help-inline">Required</span>
				</div>
			</div>

			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" name="introducedDate" id="introducedInput" /> <span
						id="introducedSpan" class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" name="discontinuedDate" id="discontinuedInput" />
					<span id="discontinuedSpan" class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company" id="companySelect">
						<option value="0" selected="selected">--</option>
						<c:forEach items="${ companies }" var="company">
							<option value="<c:out value="${ company.id }" />">
								<c:out value="${ company.name }" />
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="hidden" name="nbByPage" value="${nbDisplay}">
			<input type="hidden" name="page" value="${currentPage}">
			<input type="hidden" name="orderBy" value="${orderBy}"/>
			<input type="hidden" name="searchType" value="${searchType}"/>
			<input type="hidden" name="searchMotif" value="${searchMotif}"/>
			<input type="submit" id="validInput" value="Add" class="btn primary">
			or <a href="dashboard?page=${currentPage}&nbByPage=${nbDisplay}&orderBy=${orderBy}&searchType=${searchType}&searchMotif=${searchMotif}" class="btn">Cancel</a>
		</div>
	</form>
	<script>
		$("#addComputer").validate({
			rules : {
				name : {
					required : true
				},
				introducedDate : {
					dateISO : true
				},
				discontinuedDate : {
					dateISO : true
				}
			},
			messages : {
				name : "Please enter a computer name.",

				introducedDate : {
					dateISO : "Please enter a valid date.",
					date : "Please enter a valid date."
				},
				discontinuedDate : {
					dateISO : "Please enter a valid date.",
					date : "Please enter a valid date."
				}
			}
		});
		$("validInput").on('click', function() {
			$('#addComputer').valid();
		});
	</script>
</section>
<jsp:include page="include/footer.jsp" />