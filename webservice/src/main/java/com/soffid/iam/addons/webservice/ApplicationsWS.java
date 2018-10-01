package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Application;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface ApplicationsWS {

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
	Collection<Application> findApplications(String name, String description, String manager, String rolName,
			String rolSystem, Boolean bpmEnforced) throws UnexpectedException;

	/**
	 * Creates a new information system (application)
	 * 
	 * @param application the application to create
	 * @return the created application
	 * 
	 * @throws UnexpectedException
	 */
	Application create(Application application) throws UnexpectedException;

	/**
	 * Removes a information system (application)
	 * @param application the application to remove
	 * 
	 * @throws UnexpectedException
	 */
	void remove(Application application) throws UnexpectedException;

	/**
	 * Updates a information system (application)
	 * @param application the application to update
	 * @return the updated application
	 * 
	 * @throws UnexpectedException
	 */
	Application update(Application application) throws UnexpectedException;

}