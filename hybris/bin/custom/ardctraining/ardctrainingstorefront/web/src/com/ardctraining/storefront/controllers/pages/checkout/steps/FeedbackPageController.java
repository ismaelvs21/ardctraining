package com.ardctraining.storefront.controllers.pages.checkout.steps;

import com.ardctraining.core.jalo.CustomerFeedback;
import com.ardctraining.core.model.CustomerFeedbackModel;
import com.ardctraining.storefront.controllers.ControllerConstants;
import com.ardctraining.storefront.form.FeedbackForm;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractLoginPageController;
import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import feedback.service.imp.CustomerFeedbackService;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping(value = "/feedback")
public class FeedbackPageController extends AbstractLoginPageController {
    private HttpSessionRequestCache httpSessionRequestCache;
private static String REDIRECT="redirect/feedback";

    private static String FEEDBACK_FORM="feedbackForm";
    private static String FEEDBACK_PAGE="feedback";

    @Resource(name="customerFeedbackService")
    private CustomerFeedbackService customerFeedbackService;

    @Override
    protected String getView()
    {
        return ControllerConstants.Views.Pages.Account.AccountLoginPage;
    }

    @Override
    protected String getSuccessRedirect(final HttpServletRequest request, final HttpServletResponse response)
    {
        if (httpSessionRequestCache.getRequest(request, response) != null)
        {
            return httpSessionRequestCache.getRequest(request, response).getRedirectUrl();
        }
        return "/";
    }

    @Override
    protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException
    {
        return getContentPageForLabelOrId("login");
    }


    @Resource(name = "httpSessionRequestCache")
    public void setHttpSessionRequestCache(final HttpSessionRequestCache accHttpSessionRequestCache)
    {
        this.httpSessionRequestCache = accHttpSessionRequestCache;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String doLogin(@RequestHeader(value = "referer", required = false) final String referer,
                          @RequestParam(value = "error", defaultValue = "false") final boolean loginError, final Model model,
                          final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
            throws CMSItemNotFoundException
    {
        if (!loginError)
        {
            storeReferer(referer, request, response);
        }
        return getDefaultLoginPage(loginError, session, model);
    }

    protected void storeReferer(final String referer, final HttpServletRequest request, final HttpServletResponse response)
    {
        if (StringUtils.isNotBlank(referer) && !StringUtils.endsWith(referer, "/login")
                && StringUtils.contains(referer, request.getServerName()))
        {
            httpSessionRequestCache.saveRequest(request, response);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(@RequestHeader(value = "referer", required = false) final String referer, final RegisterForm form,
                             final BindingResult bindingResult, final Model model, final HttpServletRequest request,
                             final HttpServletResponse response, final RedirectAttributes redirectModel) throws CMSItemNotFoundException
    {
        getRegistrationValidator().validate(form, bindingResult);
        return processRegisterUserRequest(referer, form, bindingResult, model, request, response, redirectModel);
    }


    //jj
    @PostMapping("/save")
    public String SubmitFeedback(HttpSession session, @ModelAttribute FeedbackForm form, ModelMap model){
        CustomerFeedbackModel customerFeedbackModel=new CustomerFeedbackModel();
        customerFeedbackModel.setSubject(form.getSubject());
        customerFeedbackModel.setMessage(form.getMessage());
        customerFeedbackModel.setRead(false);
        customerFeedbackModel.setSubmittedDate(new Date());
     //  customerFeedbackService.save(model);
        return REDIRECT;
    }/*
    @GetMapping
    public String getFeedbackView(Model model)throws CMSItemNotFoundException{
        ContentPageModel contentPageModel=getContentPageForLabelOrId(FEEDBACK_PAGE);
        storeCmsPageInModel(model,contentPageModel);
        setUpMetaDataForContentPage(model,contentPageModel);
        model.addAttribute(FEEDBACK_FORM);

        return  ControllerConstants.Views.Pages.Feedback.FeedbackPage;
    }*/
}
