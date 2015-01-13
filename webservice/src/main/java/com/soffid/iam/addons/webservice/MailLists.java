package com.soffid.iam.addons.webservice;

import java.util.Collection;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.MailList;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class MailLists extends AbstractService {
	/**
	 * Retrieves a list of mail lists
	 * @param name the name filter (accepts % wildcard)
	 * @param domain the domain filter (accepts % wildcard)
	 * @param description the description filter  (accepts % wildcard)
	 * @param members members filter  (accepts % wildcard)
	 * @return the mail lists that match the specified criteria
	 * @throws UnexpectedException
	 */
	public Collection<MailList> findMailLists(String name, String domain, String description, String members)
			throws UnexpectedException {
		try {
			return getMailListsService().findMailListsByData(name, domain, description, members);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Creates a new mailList
	 * 
	 * @param mailList the mail list to create
	 * @return the created mail list
	 * @throws UnexpectedException
	 */
	public MailList create (MailList mailList) throws UnexpectedException
	{
		try {
			return getMailListsService().create(mailList);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Removes an mailList
	 * 
	 * @param mailList the mail list to remove
	 * @throws UnexpectedException
	 */
	public void remove (MailList mailList) throws UnexpectedException
	{
		try {
			getMailListsService().delete(mailList);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Update the mailList data
	 * 
	 * @param mailList mail list to update
	 * @throws UnexpectedException
	 */
	public void update (MailList mailList) throws UnexpectedException
	{
		try {
			getMailListsService().update(mailList);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

}
