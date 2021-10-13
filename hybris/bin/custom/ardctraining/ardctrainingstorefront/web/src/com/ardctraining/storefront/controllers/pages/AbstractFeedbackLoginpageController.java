package com.ardctraining.storefront.controllers.pages;

import com.ardctraining.storefront.form.FeedbackForm;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractRegisterPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.GuestForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Collections;

public abstract  class AbstractFeedbackLoginpageController extends AbstractRegisterPageController {

    protected static final String SPRING_SECURITY_LAST_USERNAME = "SPRING_SECURITY_LAST_USERNAME";

    protected String getDefaultLoginPage(final boolean loginError, final HttpSession session, final Model model)
            throws CMSItemNotFoundException
    {
        final LoginForm loginForm = new LoginForm();
        model.addAttribute(loginForm);
        model.addAttribute("FeedbackRegisterForm",new FeedbackForm());//edited
        model.addAttribute(new GuestForm());

        final String username = (String) session.getAttribute(SPRING_SECURITY_LAST_USERNAME);
        if (username != null)
        {
            session.removeAttribute(SPRING_SECURITY_LAST_USERNAME);
        }

        loginForm.setJ_username(username);
        storeCmsPageInModel(model, getCmsPage());
        setUpMetaDataForContentPage(model, (ContentPageModel) getCmsPage());
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.INDEX_NOFOLLOW);

        addRegistrationConsentDataToModel(model);

        final Breadcrumb loginBreadcrumbEntry = new Breadcrumb("#",
                getMessageSource().getMessage("header.link.login", null, "header.link.login", getI18nService().getCurrentLocale()),
                null);
        model.addAttribute("breadcrumbs", Collections.singletonList(loginBreadcrumbEntry));

        if (loginError)
        {
            model.addAttribute("loginError", Boolean.valueOf(loginError));
            GlobalMessages.addErrorMessage(model, "login.error.account.not.found.title");
        }

        return getView();
    }


}
