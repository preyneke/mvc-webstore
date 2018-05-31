<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>

	<section class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title"><spring:message code="login.h3.pleaseSignIn.label"/></h3>
					</div>
					<div class="panel-body">
						<c:url var="loginUrl" value="/login" />
						<form action="${loginUrl}" method="post" class="form-horizontal">
							<c:if test="${param.error != null}">
								<div class="alert alert-danger">
									<p><spring:message code="login.p.invalidUserName.label"></spring:message></p>
								</div>
							</c:if>
							<c:if test="${param.logout != null}">
								<div class="alert alert-success">
									<p><spring:message code="login.p.successfullLogout.label"/></p>
								</div>
							</c:if>
							<c:if test="${param.accessDenied != null}">
								<div class="alert alert-danger">
									<p><spring:message code="login.p.accessDenied.label"/></p>
								</div>
							</c:if>
							<div class="input-group input-sm">
								<label class="input-group-addon" for="username"><i
									class="fa fa-user"></i></label> <input type="text" class="form-control"
									id="userId" name="userId" placeholder="<spring:message code="login.input.usernamePlaceHolder.label"/>" required>
							</div>
							<div class="input-group input-sm">
								<label class="input-group-addon" for="password"><i
									class="fa fa-lock"></i></label> <input type="password"
									class="form-control" id="password" name="password"
									placeholder="<spring:message code="login.input.passwordPlaceHolder.label"/>" required>
							</div>
							<div class="form-actions">
								<input type="submit"
									class="btn btn-block btn-primary btn-default" value="Login">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>


