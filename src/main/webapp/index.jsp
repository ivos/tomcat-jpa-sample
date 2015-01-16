<%@page import="java.util.*,javax.inject.Inject,javax.naming.*"%>
<%@page import="java.sql.*,javax.sql.DataSource"%>
<%@page import="javax.persistence.*,com.github.ivos.tcjpa.*"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String dbStatusLabelClass = "label-danger";
	String dbTitle = "";
	try {
		InitialContext ctx = new InitialContext();
		DataSource dataSource = (DataSource) ctx
				.lookup("java:/comp/env/jdbc/sample");
		Connection connection = dataSource.getConnection();
		connection.close();
		dbStatusLabelClass = "label-success";
	} catch (Exception e) {
		dbTitle = e.getMessage();
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>Tomcat JPA Sample</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<div class="container">

		<div class="page-header">
			<h1>Tomcat JPA Sample</h1>
		</div>

		<h2>Status</h2>
		<p class="lead">
			<span class="label <%=dbStatusLabelClass%>" title="<%=dbTitle%>">DB</span>
		</p>

		<div class="panel-group" id="emf" role="tablist" aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingEmf1">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#emf" href="#emf1"
							aria-expanded="true" aria-controls="collapseOne">EMF:
							${sampleService.emf}</a>
					</h4>
				</div>
				<div id="emf1" class="panel-collapse collapse" role="tabpanel"
					aria-labelledby="headingEmf1">
					<div class="panel-body">
						<pre>${sampleService.emf.properties}</pre>
					</div>
				</div>
			</div>
		</div>

		${sampleService.createSample()}

		<table class="table table-bordered table-hover table-condensed">
			<tr>
				<th>Id</th>
				<th>Value</th>
			</tr>
			<c:forEach var="sample" items='${sampleService.samples}'>
				<tr>
					<td>${sample.id}</td>
					<td>${sample.value}</td>
				</tr>
			</c:forEach>
		</table>

		<hr />

	</div>

	<script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>
