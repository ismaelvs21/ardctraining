package com.ardctraining.core.product.service.impl;

import com.ardctraining.core.model.CustomProductLabelModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import java.util.List;
import java.util.Date;

public interface CustomProductLabelService {

    List<CustomProductLabelModel> findByCustomerAndProduct(CustomerModel customer, ProductModel product);

    List<CustomProductLabelModel> findExpired();

    List<CustomProductLabelModel>findByCustomerAndProductAndNullCustomer(CustomerModel customer,ProductModel product);


    List<CustomProductLabelModel>findByProduct( ProductModel product);

}
