package org.bonita.eventHandler;

/**
 * Created by HAPPYBONITA on 02/03/2017.
 */

import java.util.UUID;

import org.bonitasoft.engine.events.model.SEvent;
import org.bonitasoft.engine.events.model.SHandler;
import org.bonitasoft.engine.events.model.SHandlerExecutionException;
import org.bonitasoft.engine.identity.IdentityService;
import org.bonitasoft.engine.log.technical.TechnicalLogSeverity;
import org.bonitasoft.engine.log.technical.TechnicalLoggerService;
import org.bonitasoft.engine.service.TenantServiceAccessor;
import org.bonitasoft.engine.service.impl.ServiceAccessorFactory;

public class EventHandlerExample implements SHandler<SEvent> {

    private final TechnicalLoggerService technicalLoggerService;
    private TechnicalLogSeverity technicalLogSeverity;
    private final Long tenantId;
    private TenantServiceAccessor tenantServiceAccessor;

    public EventHandlerExample(TechnicalLoggerService technicalLoggerService, String loggerSeverity, Long tenantId) {
        this.technicalLoggerService = technicalLoggerService;
        this.tenantId = tenantId;
        this.technicalLogSeverity = TechnicalLogSeverity.valueOf(loggerSeverity);
    }

    public void execute(SEvent event) throws SHandlerExecutionException {
        if (technicalLoggerService.isLoggable(this.getClass(), technicalLogSeverity)) {
            technicalLoggerService.log(this.getClass(), technicalLogSeverity, "ExampleHandler: executing event " + event.getType());
        }

        // add your business logic here

    }

    public boolean isInterested(SEvent event) {
        if (technicalLoggerService.isLoggable(this.getClass(), technicalLogSeverity)) {
            technicalLoggerService.log(this.getClass(), technicalLogSeverity,
                    "ExampleHandler - event "
                            + event.getType()
                            + " - asks if we are interested in handling this event instance");
        }

        IdentityService identityService = getTenantServiceAccessor().getIdentityService();

        return true;
    }

    public String getIdentifier() {
        //ensure this handler is registered only once
        return UUID.randomUUID().toString();
    }

    private TenantServiceAccessor getTenantServiceAccessor() {
        try {
            if(this.tenantServiceAccessor != null){
                return this.tenantServiceAccessor;
            }else{
                ServiceAccessorFactory serviceAccessorFactory = ServiceAccessorFactory.getInstance();
                return serviceAccessorFactory.createTenantServiceAccessor(this.tenantId);
            }
        } catch (Exception e) {
            Lib.traceExeption(e);
            return null;
        }
    }
}