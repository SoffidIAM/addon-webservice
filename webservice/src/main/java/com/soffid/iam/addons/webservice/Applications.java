package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Application;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
        portName = "Applications",
        serviceName = "services/Applications",
        targetNamespace = "http://iam.soffid.com/wsdl",
        endpointInterface = "com.soffid.iam.addons.webservice.ApplicationsWS"
)
public class Applications extends AbstractService implements ApplicationsWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.ApplicationsWS#findApplications(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	public Collection<Application> findApplications(String name, String description, String manager, String rolName, String rolSystem, Boolean bpmEnforced )
			throws UnexpectedException {
		try {
			return getAppService().findApplicationByCriteria(name, description, null, manager, null, rolSystem, rolName, 
					bpmEnforced == null? null: bpmEnforced.booleanValue()?"S": "N");
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.ApplicationsWS#create(com.soffid.iam.api.Application)
	 */
	public Application create (Application application) throws UnexpectedException
	{
		try {
			return getAppService().create(application);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.ApplicationsWS#remove(com.soffid.iam.api.Application)
	 */
	public void remove (Application application) throws UnexpectedException
	{
		try {
			getAppService().delete(application);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.ApplicationsWS#update(com.soffid.iam.api.Application)
	 */
	public Application update (Application application) throws UnexpectedException
	{
		try {
			getAppService().update(application);
			return application;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
