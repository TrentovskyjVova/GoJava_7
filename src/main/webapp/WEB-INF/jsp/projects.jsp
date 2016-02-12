<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />
<h3>Projects:</h3>

<table border=1 solid black cellspacing=0>

	<tr>
		<td>project</td>
		<td>funded</td>
		<td>days to go</td>
		<td>pledged</td>
	</tr>

	<c:forEach var="project" items="${projects}">
		<tr>
			<td><a href="project.html?projectId=${project.getId()}">${project.getName()}</a></td>
			<td>${project.getFunded()}</td>
			<td>${project.getDaysToGo()}</td>
			<td>${project.getAmountPledge()}</td>
		</tr>
	</c:forEach>

</table>
<jsp:include page="footer.jsp" />