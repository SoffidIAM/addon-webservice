package com.soffid.iam.addons.webservice;

import java.util.Collection;

import org.omg.CORBA.UnknownUserException;


import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.addons.webservice.exception.UnknownSystemException;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.System;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;

import es.caib.seycon.ng.comu.AccountCriteria;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;

/**
 * @author bubu
 *
 */
public class UserAccounts extends AbstractService {
	/**
	 * Retrieves user's accounts
	 * 
	 * @param userName the user name
	 * @return a collection of user owned accounts
	 * @throws UnexpectedException
	 */
	public Collection<UserAccount> findAccountsByUser (String userName) throws UnexpectedException
	{
		try {
			User user = getUsersService().findUserByUserName(userName);
			return getAccountService().getUserAccounts(user);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
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
	public UserAccount create (String userName, String system, String accountName) throws UnknownUserException, UnknownSystemException, NeedsAccountNameException, AccountAlreadyExistsException, UnexpectedException
	{
		try {
			User user = getUsersService().findUserByUserName(userName);
			if (user == null)
				throw new UnknownUserException();
			System dispatcher = getDispatcherService().findDispatcherByName(system);
			if (dispatcher == null)
				throw new UnknownSystemException ();
			return getAccountService().createAccount(user, dispatcher, accountName);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Remove an account
	 * 
	 * @param account the account to remove
	 * @throws UnexpectedException
	 */
	public void remove (UserAccount account) throws UnexpectedException
	{
		try {
			getAccountService().removeAccount(account);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
 	