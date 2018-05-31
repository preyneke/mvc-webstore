<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="utf-8">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>New Customer Confirmed </title>
</head>
<body>
   <section>
      <div class="jumbotron">
         <div class="container">
          <form:form modelAttribute="newCustomer" class="form-horizontal">
            <h1 class="alert alert-danger"> Thank you</h1>
            <p>Thank you ${newCustomer.name} for registering with the Webstore! </p>
            
            <p>Your Customer Number is ${newCustomer.customerId}</p>
         </div>
      </div>
      </form:form>
   </section>
   <section>
      <div class="container">
         <p>
            <a href="<spring:url value="/market/products" />" class="btn btn-primary">
               <span class="glyphicon-hand-left glyphicon"></span> products
            </a>
         </p>
      </div>
      
   </section>
</body>
</html>