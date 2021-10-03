package com.ardctraining.core.job;

import com.ardctraining.core.model.CustomProductLabelCleanupCronJobModel;
import com.ardctraining.core.model.CustomProductLabelModel;
import com.ardctraining.core.product.service.impl.CustomProductLabelService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.exceptions.ModelRemovalException;
import org.apache.log4j.Logger;

import java.util.List;

public class CustomProductLabelCleanupJobPerfomable extends AbstractJobPerformable<CustomProductLabelCleanupCronJobModel> {

    private CustomProductLabelService customProductLabelService;
    private static final Logger LOG=Logger.getLogger(CustomProductLabelCleanupJobPerfomable.class);
    @Override
    public PerformResult perform(final CustomProductLabelCleanupCronJobModel customProductLabelCleanupCronJobModel) {
     LOG.debug("entering CustomProductLabelCleanupJobPerfomable::perfomable");
     final List<CustomProductLabelModel>labelsToDelete=getCustomProductLabelService().findExpired();
     LOG.info(String.format("labels to delete %s",labelsToDelete.size()));
    try {
        modelService.removeAll(labelsToDelete);
    }catch (final ModelRemovalException ex){
        LOG.error("unable to delte custom labels",ex);
        return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
    }
        return new PerformResult(CronJobResult.SUCCESS,CronJobStatus.FINISHED);
    }
    @Override
    public boolean isAbortable() {
        return Boolean.TRUE;
    }

    public CustomProductLabelService getCustomProductLabelService() {
        return customProductLabelService;
    }

    public void setCustomProductLabelService(CustomProductLabelService customProductLabelService) {
        this.customProductLabelService = customProductLabelService;
    }
}
