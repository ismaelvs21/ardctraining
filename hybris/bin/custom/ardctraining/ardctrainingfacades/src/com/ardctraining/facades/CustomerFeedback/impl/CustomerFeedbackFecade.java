package com.ardctraining.facades.CustomerFeedback.impl;

import com.ardctraining.facades.product.data.CustomProductLabelData;
import de.hybris.platform.commercefacades.customer.CustomerFacade;

import java.util.List;

public interface CustomerFeedbackFecade  extends CustomerFacade {

    List<CustomProductLabelData> getCustomLabels(final String product);



}
