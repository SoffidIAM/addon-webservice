package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import org.omg.CORBA.UnknownUserException;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.addons.webservice.exception.UnknownSystemException;
import com.soffid.iam.api.System;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;

@WebService(
		portName = "UserAccounts",
		serviceName = "services/UserAccounts",
		targetNamespace = "http://iam.soffid.com/wsdl",
		endpointInterface = "com.soffid.iam.addons.webservice.UserAccountsWS"
)
public class UserAccounts extends AbstractService implements UserAccountsWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UserAccountsWS#findAccountsByUser(java.lang.String)
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
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UserAccountsWS#create(java.lang.String, java.lang.String, java.lang.String)
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

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UserAccountsWS#remove(com.soffid.iam.api.UserAccount)
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
 	