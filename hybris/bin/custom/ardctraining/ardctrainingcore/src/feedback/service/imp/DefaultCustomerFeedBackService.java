package feedback.service.imp;

import feedback.dao.imp.CustomerFeedbackDao;

import com.ardctraining.core.model.CustomerFeedbackModel;
import feedback.service.imp.CustomerFeedbackService;//good one
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

public class DefaultCustomerFeedBackService implements  CustomerFeedbackService{
     CustomerFeedbackDao customerFeedbackDao;


    @Override
    public List<CustomerFeedbackModel> findByCustomer(CustomerModel customer) {
        return getCustomerFeedbackDao().findByCustomer(customer);
    }

    @Override
    public List<CustomerFeedbackModel> save(CustomerModel customer) {
        return save( customer);
    }

    public CustomerFeedbackDao getCustomerFeedbackDao() {
        return customerFeedbackDao;
    }

    public void setCustomerFeedbackDao(CustomerFeedbackDao customerFeedbackDao) {
        this.customerFeedbackDao = customerFeedbackDao;
    }
}
