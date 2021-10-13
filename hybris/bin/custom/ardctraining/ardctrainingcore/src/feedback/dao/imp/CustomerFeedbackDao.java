package feedback.dao.imp;

import com.ardctraining.core.model.CustomerFeedbackModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

public interface CustomerFeedbackDao {


    List<CustomerFeedbackModel> findByCustomer(CustomerModel customer);





}
