package com.ardctraining.storefront.form;

import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;

public class FeedbackForm extends RegisterForm {

    private String subject;
    private String message;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
