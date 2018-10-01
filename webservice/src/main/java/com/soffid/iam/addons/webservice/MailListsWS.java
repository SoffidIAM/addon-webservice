package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.MailList;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface MailListsWS {

	/**
	 * Retrieves a list of mail lists
	 * @param name the name filter (accepts % wildcard)
	 * @param domain the domain filter (accepts % wildcard)
	 * @param description the description filter  (accepts % wildcard)
	 * @param members members filter  (accepts % wildcard)
	 * @return the mail lists that match the specified criteria
	 * @throws UnexpectedException
	 */
	Collection<MailList> findMailLists(String name, String domain, String description, String members)
			throws UnexpectedException;

	/**
	 * Creates a new mailList
	 * 
	 * @param mailList the mail list to create
	 * @return the created mail list
	 * @throws UnexpectedException
	 */
	MailList create(MailList mailList) throws UnexpectedException;

	/**
	 * Removes an mailList
	 * 
	 * @param mailList the mail list to remove
	 * @throws UnexpectedException
	 */
	void remove(MailList mailList) throws UnexpectedException;

	/**
	 * Update the mailList data
	 * 
	 * @param mailList mail list to update
	 * @throws UnexpectedException
	 */
	void update(MailList mailList) throws UnexpectedException;
}
