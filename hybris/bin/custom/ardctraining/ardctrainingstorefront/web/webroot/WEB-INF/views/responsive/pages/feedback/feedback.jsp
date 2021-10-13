<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="feedback" tagdir="/WEB-INF/tags/responsive/feedback" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>

<template:page pageTitle="Feedback">


<div class="page-feedback">
<div class="header">
<h3>Feedback</h3>
</div>

<div class="feedback-form">
<form:form id="feedbackForm" action="feedback/save" method="POST" modelAttribute="FeedbackForm">
<form:input cssClass="form-control" name="subject" id="subject" maxlenght="100" placeholder="Subject" path="subject"/>
<form:input cssClass="form-control" name="message" id="message" maxlenght="100" placeholder="Message" path="message"/>

<button type="submit" id="submit" class="btn btn-primary btn-sn">Save</button>
<a href="/feedback" role="button" id="cancel" class="btn btn-light btn-sn">Cancel</a>
</form:form>
</div>
<div class="customer-feedback">
<feedback:customerFeedback customerFeedbacks="${feedbacks}"/>
</div>
</div>
</template:page>