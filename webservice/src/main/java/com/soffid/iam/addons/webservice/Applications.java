package com.soffid.iam.addons.webservice;

import java.util.Collection;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;

import es.caib.seycon.ng.comu.AccountCriteria;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class Applications extends AbstractService {
	/**
	 * Retrieves groups
	 * 
	 * @param name the group name (accepts % wildcard
	 * @param parent will find children of this one  (accepts % wildcard
	 * @param description group description
	 * @param type group type
	 * @param removed will search for removed groups
	 * @param drive shraed drive letter
	 * @param driveHost shared drive host
	 * @return a group collection
	 * 
	 * @throws UnexpectedException
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
	
	
	/**
	 * Creates a new information system (application)
	 * 
	 * @param application the application to create
	 * @return the created application
	 * 
	 * @throws UnexpectedException
	 */
	public Application create (Application application) throws UnexpectedException
	{
		try {
			return getAppService().create(application);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Removes a information system (application)
	 * @param application the application to remove
	 * 
	 * @throws UnexpectedException
	 */
	public void remove (Application application) throws UnexpectedException
	{
		try {
			getAppService().delete(application);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Updates a information system (application)
	 * @param application the application to update
	 * @return the updated application
	 * 
	 * @throws UnexpectedException
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
