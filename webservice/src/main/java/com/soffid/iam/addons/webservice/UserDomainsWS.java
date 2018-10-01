package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.UserDomain;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface UserDomainsWS {

	/**
	 * Find all user domains
	 * 
	 * @return the user domains list
	 * @throws UnexpectedException
	 */
	Collection<UserDomain> findUserDomains() throws UnexpectedException;

	/**
	 * Creates a UserDomain
	 * 
	 * @param userDomain UserDomain to create
	 * @return created UserDomain
	 * @throws UnexpectedException
	 */
	UserDomain create(UserDomain userDomain) throws UnexpectedException;

	/**
	 * Removes a UserDomain
	 * 
	 * @param userDomain userDomain to remove
	 * @throws UnexpectedException
	 */
	void remove(UserDomain userDomain) throws UnexpectedException;

	/**
	 * Update UserDomain data
	 * 
	 * @param userDomain the UserDomain to update
	 * @return the updated UserDomain
	 * @throws UnexpectedException
	 */
	UserDomain update(UserDomain userDomain) throws UnexpectedException;
}
