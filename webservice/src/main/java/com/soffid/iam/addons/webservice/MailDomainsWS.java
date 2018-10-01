package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.MailDomain;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface MailDomainsWS {

	/**
	 * Retrieves mail domains
	 * @param name the domain name filter (accepts % wildcard)
	 * @param description domain description  (accepts % wildcard)
	 * @param obsolete true to find obsolete domains
	 * @return the list of mail domains
	 * @throws UnexpectedException
	 */
	Collection<MailDomain> findMailDomains(String name, String description, Boolean obsolete)
			throws UnexpectedException;

	/**
	 * Creates a new mailDomain
	 * 
	 * @param mailDomain the mail domain to create
	 * @return the created mail domain
	 * @throws UnexpectedException
	 */
	MailDomain create(MailDomain mailDomain) throws UnexpectedException;

	/**
	 * Removes an mailDomain
	 * 
	 * @param mailDomain the mail domain to remove
	 * @throws UnexpectedException
	 */
	void remove(MailDomain mailDomain) throws UnexpectedException;

	/**
	 * Update the mailDomain data
	 * 
	 * @param mailDomain mail domain to update
	 * @throws UnexpectedException
	 */
	void update(MailDomain mailDomain) throws UnexpectedException;
}
