<%@page import="java.util.List"%>
<%@page import="javax.naming.*,java.sql.*,javax.sql.DataSource"%>
<%@page import="javax.persistence.*,com.github.ivos.tcjpa.*"%>
<%@page contentType="text/html;charset=UTF-8"%>
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

		<%
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("sample");
		%>
		<div class="panel-group" id="emf" role="tablist" aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingEmf1">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#emf" href="#emf1"
							aria-expanded="true" aria-controls="collapseOne">EMF: <%=emf%></a>
					</h4>
				</div>
				<div id="emf1" class="panel-collapse collapse" role="tabpanel"
					aria-labelledby="headingEmf1">
					<div class="panel-body">
						<pre><%=emf.getProperties()%></pre>
					</div>
				</div>
			</div>
		</div>

		<%
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Sample newSample = new Sample();
			em.persist(newSample);
			em.getTransaction().commit();

			List<Sample> samples = em.createQuery("select s from Sample s",
					Sample.class).getResultList();
		%>

		<table class="table table-bordered table-hover table-condensed">
			<tr>
				<th>Id</th>
				<th>Value</th>
			</tr>
			<%
				for (Sample sample : samples) {
			%>
			<tr>
				<td><%=sample.getId()%></td>
				<td><%=sample.getValue()%></td>
			</tr>
			<%
				}
			%>
		</table>

		<%
			em.close();
		%>

		<hr />

	</div>

	<script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>
