package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.Date;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Account;

import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
		portName = "PasswordVault",
		serviceName = "services/PasswordVault",
		targetNamespace = "http://iam.soffid.com/wsdl",
		endpointInterface = "com.soffid.iam.addons.webservice.PasswordVaultWS"
)
public class PasswordVault extends AbstractService implements PasswordVaultWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordVaultWS#getAccounts()
	 */
	public Collection<Account> getAccounts()
			throws UnexpectedException {
		try {
			return getSelfService().getUserAccounts();
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordVaultWS#getPassword(java.lang.String, java.lang.String)
	 */
	public String getPassword (String account, String dispatcher) throws UnexpectedException
	{
		try {
			for (Account acc: getSelfService().getUserAccounts())
			{
				if (acc.getName().equals(account))
				{
					com.soffid.iam.api.Password p = getSelfService().queryAccountPassword(acc);
					if (p != null)
						return p.getPassword();
					
				}
			}
			return null;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordVaultWS#setPassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void setPassword (String account, String dispatcher, String password) throws UnexpectedException
	{
		try {
			for (Account acc: getSelfService().getUserAccounts())
			{
				if (acc.getName().equals(account))
				{
					getSelfService().setAccountPassword(acc, new Password(password));
					
				}
			}
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordVaultWS#lockPrivilegedAccount(java.lang.String, java.lang.String, java.lang.String, java.util.Date, boolean)
	 */
	public void lockPrivilegedAccount (String account, String dispatcher, String password, Date until, boolean force) throws UnexpectedException
	{
		try {
			for (Account acc: getSelfService().getUserAccounts())
			{
				if (acc.getName().equals(account))
				{
					getSelfService().setHPAccountPassword(acc,  new Password(password), until, force);
				}
			}
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordVaultWS#unlockPrivilegedAccount(java.lang.String, java.lang.String)
	 */
	public void unlockPrivilegedAccount (String account, String dispatcher)
			throws UnexpectedException
	{
		try {
			for (Account acc: getSelfService().getUserAccounts())
			{
				if (acc.getName().equals(account))
				{
					getAccountService().checkinHPAccount(acc);
				}
			}
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
