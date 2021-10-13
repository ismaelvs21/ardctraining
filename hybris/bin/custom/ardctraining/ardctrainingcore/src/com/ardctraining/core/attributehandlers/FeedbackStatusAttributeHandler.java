package com.ardctraining.core.attributehandlers;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import com.ardctraining.core.enums.FeedbackStatusEnum;
import com.ardctraining.core.model.CustomerFeedbackModel;
import de.hybris.platform.servicelayer.time.TimeService;
import java.util.Date;
import java.util.concurrent.TimeUnit;


//DynamicAttributeHandler<FeedbackStatusEnum ,CustomerFeedbackModel >
public class FeedbackStatusAttributeHandler implements DynamicAttributeHandler<FeedbackStatusEnum,CustomerFeedbackModel> {
    private ConfigurationService configurationService;
    private TimeService timeService;


    @Override
    public FeedbackStatusEnum get(CustomerFeedbackModel model) {
        Date now=getTimeService().getCurrentTime();
        Date userDate=model.getSubmittedDate();
        long timeLeft=now.getTime()-userDate.getTime();

        TimeUnit CurrentTime=TimeUnit.DAYS;
        long difference=CurrentTime.convert(timeLeft,CurrentTime.MILLISECONDS);
        long mx_days=getConfigurationService().getConfiguration().getLong("feedback.status.feedback_days");

        if (model.getRead()) {
            if (difference > mx_days) {
                return FeedbackStatusEnum.READ_PASTDUE;
            } else {
                return FeedbackStatusEnum.READ;
            }
        }  else{
            if (difference>mx_days){
                return FeedbackStatusEnum.PASTDUE;
            }else {
                return FeedbackStatusEnum.NOT_READ;
            }
        }
    }

    @Override
    public void set(CustomerFeedbackModel model, FeedbackStatusEnum feedbackStatusEnum) {
    //prepared.
        //model.setSubject(feedbackStatusEnum);
    }
    public TimeService getTimeService(){return  timeService;}

    public void setTimeService(TimeService timeService){this.timeService=timeService;}

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }


}
