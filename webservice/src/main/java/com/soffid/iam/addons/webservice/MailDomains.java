package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.Date;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.MailDomain;
import com.soffid.iam.api.MailList;

import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class MailDomains extends AbstractService {
	/**
	 * Retrieves mail domains
	 * @param name the domain name filter (accepts % wildcard)
	 * @param description domain description  (accepts % wildcard)
	 * @param obsolete true to find obsolete domains
	 * @return the list of mail domains
	 * @throws UnexpectedException
	 */
	public Collection<MailDomain> findMailDomains(String name, String description, Boolean obsolete)
			throws UnexpectedException {
		try {
			return getMailListsService().findMailDomainsByFilter(name, description, obsolete == null ? null: obsolete.booleanValue() ? "S": "N");
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Creates a new mailDomain
	 * 
	 * @param mailDomain the mail domain to create
	 * @return the created mail domain
	 * @throws UnexpectedException
	 */
	public MailDomain create (MailDomain mailDomain) throws UnexpectedException
	{
		try {
			return getMailListsService().create(mailDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Removes an mailDomain
	 * 
	 * @param mailDomain the mail domain to remove
	 * @throws UnexpectedException
	 */
	public void remove (MailDomain mailDomain) throws UnexpectedException
	{
		try {
			getMailListsService().delete(mailDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Update the mailDomain data
	 * 
	 * @param mailDomain mail domain to update
	 * @throws UnexpectedException
	 */
	public void update (MailDomain mailDomain) throws UnexpectedException
	{
		try {
			getMailListsService().update(mailDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

}
