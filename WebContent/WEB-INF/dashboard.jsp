<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<section id="main">
	<style>
		 #sortable1 li, #sortable2 li { margin: 0 5px 5px 5px; padding: 5px; font-size: 1.2em; width: 120px; }
	</style>
	
	<!-- <script>
	
		$( "#sortable1, #sortable2" ).sortable().disableSelection();
		$(function() {
			var $tabs = $( "#tabs" ).tabs();
			var $tabsItems = $( "ul:first li", $tabs ).droppable({
				accpet: ".computers zebra-striped thead tbody",
				hoverClass: "ui-state-hover",
				drop: function(event, ui) {
					var $item = $(this);
					var $list = $( $item.find("a").attr("href") ).find(".computers zebra-striped");
					ui.draggable.hide( "slow", function() {
						$tabs.tabs("option","active", $tabItems.index($item));
						$(this).appendTo($list).show("slow");
					});				
				}		
			});			
		});
	</script>
 -->
	<script src="../resources/jquery/scriptSearch.js"></script>
	<h1 id="homeTitle">
		<c:out value="${ size }" />
		Computers found
	</h1>
	<div id="actions">
		<form action="dashboard" method="POST">
			<input type="search" id="searchbox" name="search" value="" placeholder="Search"> 
			<select name="searchtype" id="searchtype" class="small">
				<option value="0" selected="selected">by id</option>
				<option value="1">by name</option>
				<option value="2">by introduced date</option>
				<option value="3">by discontinued date</option>
				<option value="4" onclick="">by company</option>
			</select>
			<input type="submit" id="searchsubmit" value="Filter" class="btn primary">
			<span>Order By : </span>
			<select name="orderby" id="orderby" class="small">
				<option value="1" selected="selected">id</option>
				<option value="2">name</option>
				<option value="3">introduced date</option>
				<option value="4">discontinued date</option>
				<option value="5">company</option>
			</select>
			
		</form>
		<a class="btn success" id="add" href="addcomputer">Add Computer</a>
	</div>

	<!--<div id="tabs">
		 <ul>
			<li><a href="#tabs-1">Computers</a></li>
			<li><a href="#tabs-2">Companies</a></li>
		</ul>-->
		<div id="tab-1" style="display: block;">
			<table id="sortable1" class="computers zebra-striped">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->
						<th>Computer Name</th>
						<th>Introduced Date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued Date</th>
						<!-- Table header for Company -->
						<th>Company</th>
						<th>Edition</th>
						<th>Removal</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ computers }" var="computer">
						<tr>
							<td>
								<!-- <a href="#" onclick=""></a> --> <c:out
									value="${ computer.name }" />
							</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${ computer.introduced }" /></td>
							<td><c:out value="${ computer.discontinued }" /></td>
							<td><c:out value="${ computer.company.name }" /></td>
							<td>
								<form action="editcomputer" method="GET">
									<input type="hidden" name="id" value="${computer.id}" /> <input
										type="submit" value="Edit" class="btn success" id="editcomputer">
								</form>
							</td>
							<td>
								<form action="delcomputer" method="GET">
									<input type="hidden" name="id" value="${computer.id}" /> <input
										type="submit" value="Delete" class="btn danger" id="delcomputer">
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- <div id="tab-2" style="display: none;">
			<table id="sortable2" class="computers zebra-striped">
				<thead>
					<tr>
						
						<th>Company</th>
						<th>Edition</th>
						<th>Removal</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ companies }" var="computer">
						<tr>
							<td><c:out value="${ company.name }" /></td>
							<td>
								<form action="editcomputer" method="GET">
									<input type="hidden" name="id" value="${computer.id}" /> <input
										type="submit" value="Edit" class="btn success" id="editcomputer">
								</form>
							</td>
							<td>
								<form action="delcomputer" method="GET">
									<input type="hidden" name="id" value="${computer.id}" /> <input
										type="submit" value="Delete" class="btn danger" id="delcomputer">
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div> 
	</div>-->
</section>

<jsp:include page="include/footer.jsp" />
