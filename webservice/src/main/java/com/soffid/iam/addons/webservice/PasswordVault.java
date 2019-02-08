package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.Date;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Account;

import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class PasswordVault extends AbstractService {
	/**
	 * Gets user accounts
	 * 
	 * @return list user accounts
	 * 
	 * @throws UnexpectedException
	 */
	public Collection<Account> getAccounts()
			throws UnexpectedException {
		try {
			return getSelfService().getUserAccounts();
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Gets the account password
	 * 
	 * @param account account name
	 * @param dispatcher managed system name
	 * @return the unencrypted password
	 * @throws UnexpectedException
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

	/**
	 * Changes the password for an account
	 * 
	 * @param account the account name
	 * @param dispatcher the managed system name
	 * @param password the password itself
	 * @throws UnexpectedException
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

	/**
	 * Reserves and changes the password for a privileged account
	 * 
	 * @param account the account name
	 * @param dispatcher the managed system name
	 * @param password the password itself
	 * @param until date to checkout the account
	 * @param force true to bypass current account ownership
	 * @throws UnexpectedException
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
	
	/**
	 * Releases a privileged account
	 * 
	 * @param account the account name
	 * @param dispatcher the managed system name
	 * @throws UnexpectedException
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
