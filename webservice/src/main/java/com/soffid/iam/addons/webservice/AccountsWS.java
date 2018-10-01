package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.Date;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.RoleGrant;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface AccountsWS {

	/**
	 * @param name account Name (% wildcard is accepted)
	 * @param description account Description (% wildcard is accepted)
	 * @param type account Type. Null for any account Type
	 * @param grantedUser granted userName filter
	 * @param grantedGroups granted group name filter
	 * @param grantedRoles granted role name filter
	 * @param system managed system filter
	 * @return list of accounts matching the criteria.
	 * @throws UnexpectedException
	 */
	Collection<Account> findAccounts(
			@WebParam(name = "name") String name,
			@WebParam(name = "description") String description,
			@WebParam(name = "accountType") String accountType,
			@WebParam(name = "grantedUser") String grantedUser,
			@WebParam(name = "grantedGroups") String grantedGroups,
			@WebParam(name = "grantedRoles") String grantedRoles,
			@WebParam(name = "system") String system) throws UnexpectedException;

	/**
	 * Creates a new account
	 * 
	 * @param account the account to create
	 * @return the created account
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	Account create(Account account) throws UnexpectedException, AccountAlreadyExistsException;

	/**
	 * Removes an account
	 * 
	 * @param account the account to remove
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	void remove(Account account) throws UnexpectedException;

	/**
	 * Update the account data
	 * 
	 * @param account account to update
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	void update(Account account) throws UnexpectedException, AccountAlreadyExistsException;

	/**
	 * Update the account password
	 * 
	 * @param account account to update
	 * @param password the password to set
	 * 
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	void setAccountPassword(Account account, String password) throws UnexpectedException;

	/**
	 * Update the account password for High Privileged accounts.
	 * 
	 * @param account account to update
	 * @param password the password to set
	 * @param untilDate the expiration date for the password
	 * @param force true to steal account from password ownere
	 * 
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	void setHPAccountPassword(Account account, String password, Date untilDate, boolean force)
			throws UnexpectedException;

	/**
	 * Gets the account passsword
	 * 
	 * @param account account to get password of
	 * @return the stored password for the account
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	String getAccountPassword(Account account) throws UnexpectedException;

	/**
	 * Get the role grants for an account
	 * 
	 * @param account account to query on
	 * @return the list of granted roles
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	Collection<RoleGrant> getRoleGrants(Account account) throws UnexpectedException;

	/**
	 * Get the effective role grants for an account
	 * 
	 * @param account account to query on
	 * @return the list of granted roles, either directly or not
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	Collection<RoleGrant> getEffectiveRoleGrants(Account account) throws UnexpectedException;
}
