package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import org.omg.CORBA.UnknownUserException;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.addons.webservice.exception.UnknownSystemException;
import com.soffid.iam.api.UserAccount;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface UserAccountsWS {

	/**
	 * Retrieves user's accounts
	 * 
	 * @param userName the user name
	 * @return a collection of user owned accounts
	 * @throws UnexpectedException
	 */
	Collection<UserAccount> findAccountsByUser(String userName) throws UnexpectedException;

	/**
	 * Creates a user owned account on a managed system.
	 * 
	 * @param userName the user name
	 * @param system the system to create the account on
	 * @param accountName account name (optional)
	 * @return the created account
	 * 
	 * @throws UnknownUserException
	 * @throws UnknownSystemException
	 * @throws NeedsAccountNameException
	 * @throws AccountAlreadyExistsException
	 * @throws UnexpectedException
	 */
	UserAccount create(String userName, String system, String accountName) throws UnknownUserException,
			UnknownSystemException, NeedsAccountNameException, AccountAlreadyExistsException, UnexpectedException;

	/**
	 * Remove an account
	 * 
	 * @param account the account to remove
	 * @throws UnexpectedException
	 */
	void remove(UserAccount account) throws UnexpectedException;
}
