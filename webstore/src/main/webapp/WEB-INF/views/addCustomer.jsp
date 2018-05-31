<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


	<section class="container">
		<form:form method="POST" modelAttribute="newCustomer"
			class="form-horizontal">
			<form:errors path="*" cssClass="alert alert-danger" element="div"/>
			<fieldset>
				<legend><spring:message
							code="addCustomer.form.addNewCustomer.label" /></legend>
				
					
					<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="name"><spring:message code="addCustomer.form.name.label"/></label>
					<div class="col-lg-10">
						<form:input id="name" path="name" type="text"
							class="form:input-large" />
					</div>
					</div>
					
					<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="phoneNumber"><spring:message code="addCustomer.form.phoneNumber.label"/></label>
					<div class="col-lg-10">
						<form:input id="phoneNumber" path="phoneNumber" type="text"
							class="form:input-large" />
					</div>
					</div>
					<div class="form-group">
               <label class="control-label col-lg-2" for="doorNo">Door No</label>
               <div class="col-lg-10">
                  <form:input id="doorNo" path="billingAddress.doorNo" type="text"
                     class="form:input-large" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="streetName">Street Name</label>
               <div class="col-lg-10">
                  <form:input id="streetName" path="billingAddress.streetName" type="text"
                     class="form:input-large" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="billingAddress.areaName">Area Name</label>
               <div class="col-lg-10">
                  <form:input id="areaName" path="billingAddress.areaName" type="text"
                     class="form:input-large" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="state">State</label>
               <div class="col-lg-10">
                  <form:input id="state" path="billingAddress.state" type="text"
                     class="form:input-large" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="country">country</label>
               <div class="col-lg-10">
                  <form:input id="country" path="billingAddress.country" type="text"
                     class="form:input-large" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="zipCode">Zip Code</label>
               <div class="col-lg-10">
                  <form:input id="zipCode" path="billingAddress.zipCode" type="text"
                     class="form:input-large" />
               </div>
            </div>
            <div class="form-group">
              
              
            </div>
            
					
					
				

				
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value="Add" />
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>

