package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.Date;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Account;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface PasswordVaultWS {

	/**
	 * Gets user accounts
	 * 
	 * @return list user accounts
	 * 
	 * @throws UnexpectedException
	 */
	Collection<Account> getAccounts() throws UnexpectedException;

	/**
	 * Gets the account password
	 * 
	 * @param account account name
	 * @param dispatcher managed system name
	 * @return the unencrypted password
	 * @throws UnexpectedException
	 */
	String getPassword(String account, String dispatcher) throws UnexpectedException;

	/**
	 * Changes the password for an account
	 * 
	 * @param account the account name
	 * @param dispatcher the managed system name
	 * @param password the password itself
	 * @throws UnexpectedException
	 */
	void setPassword(String account, String dispatcher, String password) throws UnexpectedException;

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
	void lockPrivilegedAccount(String account, String dispatcher, String password, Date until, boolean force)
			throws UnexpectedException;

	/**
	 * Releases a privileged account
	 * 
	 * @param account the account name
	 * @param dispatcher the managed system name
	 * @throws UnexpectedException
	 */
	void unlockPrivilegedAccount(String account, String dispatcher) throws UnexpectedException;
}
