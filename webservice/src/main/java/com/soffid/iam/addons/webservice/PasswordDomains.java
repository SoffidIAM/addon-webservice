package com.soffid.iam.addons.webservice;

import java.util.Collection;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.PasswordDomain;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class PasswordDomains extends AbstractService {
	
	/**
	 * Find all user domains
	 * 
	 * @return the user domains list
	 * @throws UnexpectedException
	 */
	public Collection<PasswordDomain> findPasswordDomains()
			throws UnexpectedException {
		try {
			return getUserDomainService().findAllPasswordDomain();
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	
	/**
	 * Creates a PasswordDomain
	 * 
	 * @param passwordDomain Password Domain to create
	 * @return created Password Domain
	 * @throws UnexpectedException
	 */
	public PasswordDomain create (PasswordDomain passwordDomain) throws UnexpectedException
	{
		try {
			return getUserDomainService().create(passwordDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Removes a PasswordDomain
	 * 
	 * @param passwordDomain password Domain to remove
	 * @throws UnexpectedException
	 */
	public void remove (PasswordDomain passwordDomain) throws UnexpectedException
	{
		try {
			getUserDomainService().delete(passwordDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Update PasswordDomain data
	 * 
	 * @param passwordDomain the Password Domain to update
	 * @return the updated Password Domain
	 * @throws UnexpectedException
	 */
	public PasswordDomain update (PasswordDomain passwordDomain) throws UnexpectedException
	{
		try {
			getUserDomainService().update(passwordDomain);
			return passwordDomain;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

}

