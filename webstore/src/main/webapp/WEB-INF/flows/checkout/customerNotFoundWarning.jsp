<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="utf-8">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Invalid cart </title>
</head>
<body>
   <section>
      <div class="jumbotron">
         <div class="container">
            <h1 class="alert alert-danger"> CUSTOMER NOT FOUND</h1>
         </div>
      </div>
   </section>
    <section class="container">
      <form:form modelAttribute="newCustomer" class="form-horizontal">
         <fieldset>
            <legend>Customer Details</legend>
           
            <div class="form-group">
               <label class="control-label col-lg-2" for="customerId">Customer ID</label>
               <div class="col-lg-10">
                  <form:input id="customerId" path="customerId" type="number" class="form:input-large" value="${newCustomer.customerId}" />
               </div>
            </div>
           
            <input  type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
            
            <div class="form-group">
               <div class="col-lg-offset-2 col-lg-10">
                  <input type="submit" id="btnAdd" class="btn btn-primary"
                     value="Add" name="_eventId_loadCustomerInfoCollected" />
                     <input type="submit" id="btnAdd" class="btn btn-primary"
                     value="New Customer" name="_eventId_newCustomerInfo" />
                  <button id="btnCancel" class="btn btn-default" name="_eventId_cancel">Cancel</button>
               </div>
            </div>
         </fieldset>
      </form:form>
   </section>
</body>
</html>