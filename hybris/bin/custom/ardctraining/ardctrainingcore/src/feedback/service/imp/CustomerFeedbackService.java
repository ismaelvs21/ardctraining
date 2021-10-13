package feedback.service.imp;
import com.ardctraining.core.model.CustomerFeedbackModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

public interface CustomerFeedbackService {

    List<CustomerFeedbackModel>findByCustomer(CustomerModel customer);

    List<CustomerFeedbackModel>save(CustomerModel customer);


}
