<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;
charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title><spring:message code="Product.title.products.label" /></title>
</head>
<body>
	<section>
		<div class="pull-right" style="padding-right: 50px">
			<a href="?language=en">English</a>|<a href="?language=nl">Dutch</a>|
			<a href="<c:url value="/logout" />">Logout</a>
		</div>
	</section>

	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>
					<spring:message code="Product.title.products.label" />
				</h1>
				<p>
					<spring:message code="Product.desc.productsDesc.label" />
				</p>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<c:forEach items="${products}" var="product">
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<img src="<c:url value="/img/${product.productId}.png">
</c:url>"
							alt="image" style="width: 100%" />
						<div class="caption">
							<h3>${product.name}</h3>
							<p>${product.description}</p>
							<p>R${product.unitPrice}</p>
							<p>Available ${product.unitsInStock} units in stock</p>
							<p>
								<a
									href="<spring:url value="/market/product?
id=${product.productId}" /> "
									class="btn btn-primary"> <span
									class="glyphicon-info-sign glyphicon" /></span> Details
								</a>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>
</html>