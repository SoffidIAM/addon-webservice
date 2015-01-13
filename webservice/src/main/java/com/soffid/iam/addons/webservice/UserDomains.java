package com.soffid.iam.addons.webservice;

import java.util.Collection;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.UserDomain;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class UserDomains extends AbstractService {
	
	/**
	 * Find all user domains
	 * 
	 * @return the user domains list
	 * @throws UnexpectedException
	 */
	public Collection<UserDomain> findUserDomains()
			throws UnexpectedException {
		try {
			return getUserDomainService().findAllUserDomain();
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	
	/**
	 * Creates a UserDomain
	 * 
	 * @param userDomain UserDomain to create
	 * @return created UserDomain
	 * @throws UnexpectedException
	 */
	public UserDomain create (UserDomain userDomain) throws UnexpectedException
	{
		try {
			return getUserDomainService().create(userDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Removes a UserDomain
	 * 
	 * @param userDomain userDomain to remove
	 * @throws UnexpectedException
	 */
	public void remove (UserDomain userDomain) throws UnexpectedException
	{
		try {
			getUserDomainService().delete(userDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Update UserDomain data
	 * 
	 * @param userDomain the UserDomain to update
	 * @return the updated UserDomain
	 * @throws UnexpectedException
	 */
	public UserDomain update (UserDomain userDomain) throws UnexpectedException
	{
		try {
			getUserDomainService().update(userDomain);
			return userDomain;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

}

