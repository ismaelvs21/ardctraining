package com.ardctraining.facades.product.impl;


import com.ardctraining.facades.product.data.CustomProductLabelData;
import de.hybris.platform.commercefacades.product.ProductFacade;

import java.util.List;

public interface ArdctrainingProductFacade extends ProductFacade {

    List<CustomProductLabelData>getCustomLabels(final String product);



}
