package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.UserType;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface UserTypesWS {

	/**
	 * Find all user domains
	 * 
	 * @return the user domains list
	 * @throws UnexpectedException
	 */
	Collection<UserType> findUserTypes() throws UnexpectedException;

	/**
	 * Creates a UserType
	 * 
	 * @param userType User type to create
	 * @return created User type
	 * @throws UnexpectedException
	 */
	UserType create(UserType userType) throws UnexpectedException;

	/**
	 * Removes a UserType
	 * 
	 * @param userType User type to remove
	 * @throws UnexpectedException
	 */
	void remove(UserType userType) throws UnexpectedException;

	/**
	 * Update UserType data
	 * 
	 * @param userType the User type to update
	 * @return the updated User type
	 * @throws UnexpectedException
	 */
	UserType update(UserType userType) throws UnexpectedException;
}
