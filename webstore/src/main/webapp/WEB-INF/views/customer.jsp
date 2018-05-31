<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<section class="container">
		

			<div class="col-md-5">
				<h3>${customer.name}</h3>
				<p>
					<strong>Customer Code : </strong><span class="label label-warning">${customer.customerId}</span>
				</p>
				<p><strong>Door Number</strong> : ${customer.billingAddress.doorNo}</p>
				<p>
					<strong>Street Name</strong> : ${customer.billingAddress.streetName}
				</p>
				<p>
					<strong>Area </strong> : 
				</p>
				<p>
					<strong>State</strong> : 
				</p>
				<p>
					<strong>Country</strong> : 
				</p>
				<p>
					<strong>Zip </strong> : 
				
				</p>
				<p>
					<a href="<spring:url value="/market/products" />"
						class="btn btn-default"> <span
						class="glyphicon-hand-left glyphicon"></span> back
					</a> 

				</p>
			</div>
		</div>
	</section>
