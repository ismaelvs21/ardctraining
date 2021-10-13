package feedback.dao.imp;

import com.ardctraining.core.model.CustomerFeedbackModel;
import com.ardctraining.core.product.dao.impl.DefaultCustomProductLabelDao;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import com.ardctraining.core.enums.FeedbackStatusEnum;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultCustomerFeedbackDao implements  CustomerFeedbackDao{
    private FlexibleSearchService flexibleSearchService;

    private static final Logger LOG = Logger.getLogger(DefaultCustomProductLabelDao.class);

    private static final String FIND_BY_CUSTOMER_QUERY =
            "SELECT {"+ ItemModel.PK+"}"
                    +"FROM {"+CustomerFeedbackModel._TYPECODE+"}"+
                    "WHERE  {" + CustomerFeedbackModel.CUSTOMER + "} = ?customer" ;



    @Override
    public List<CustomerFeedbackModel> findByCustomer(final CustomerModel customer) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_BY_CUSTOMER_QUERY);
        query.addQueryParameter("customer", customer);

        List<CustomerFeedbackModel>results=findResult(query);

        return results.stream().filter(comment->isFiltered(comment)).collect(Collectors.toList());
    }
private Boolean isFiltered(CustomerFeedbackModel feedback){
     return   feedback.getStatus()==(FeedbackStatusEnum.PASTDUE);
}
    private List<CustomerFeedbackModel>findResult(FlexibleSearchQuery query){
        SearchResult<CustomerFeedbackModel>result=getFlexibleSearchService().search(query);

        if (Objects.nonNull(result)&&CollectionUtils.isNotEmpty(result.getResult())){
            return  result.getResult();
        }
        LOG.warn("uNABLE TO FIND RESULT");
        return Collections.emptyList();
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
        //everythinggit init

    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
