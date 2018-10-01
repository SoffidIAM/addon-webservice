package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.PasswordDomain;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface PasswordDomainsWS {

	/**
	 * Find all user domains
	 * 
	 * @return the user domains list
	 * @throws UnexpectedException
	 */
	Collection<PasswordDomain> findPasswordDomains() throws UnexpectedException;

	/**
	 * Creates a PasswordDomain
	 * 
	 * @param passwordDomain Password Domain to create
	 * @return created Password Domain
	 * @throws UnexpectedException
	 */
	PasswordDomain create(PasswordDomain passwordDomain) throws UnexpectedException;

	/**
	 * Removes a PasswordDomain
	 * 
	 * @param passwordDomain password Domain to remove
	 * @throws UnexpectedException
	 */
	void remove(PasswordDomain passwordDomain) throws UnexpectedException;

	/**
	 * Update PasswordDomain data
	 * 
	 * @param passwordDomain the Password Domain to update
	 * @return the updated Password Domain
	 * @throws UnexpectedException
	 */
	PasswordDomain update(PasswordDomain passwordDomain) throws UnexpectedException;
}
