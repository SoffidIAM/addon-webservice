package com.soffid.iam.addons.webservice;

import java.util.Collection;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.UserType;

import es.caib.seycon.ng.exception.InternalErrorException;


public class UserTypes extends AbstractService {
	
	/**
	 * Find all user domains
	 * 
	 * @return the user domains list
	 * @throws UnexpectedException
	 */
	public Collection<UserType> findUserTypes()
			throws UnexpectedException {
		try {
			return getUserDomainService().findAllUserType();
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	
	/**
	 * Creates a UserType
	 * 
	 * @param userType User type to create
	 * @return created User type
	 * @throws UnexpectedException
	 */
	public UserType create (UserType userType) throws UnexpectedException
	{
		try {
			return getUserDomainService().create(userType);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Removes a UserType
	 * 
	 * @param userType User type to remove
	 * @throws UnexpectedException
	 */
	public void remove (UserType userType) throws UnexpectedException
	{
		try {
			getUserDomainService().delete(userType);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Update UserType data
	 * 
	 * @param userType the User type to update
	 * @return the updated User type
	 * @throws UnexpectedException
	 */
	public UserType update (UserType userType) throws UnexpectedException
	{
		try {
			getUserDomainService().update(userType);
			return userType;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

}

