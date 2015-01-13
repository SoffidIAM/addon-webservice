package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.Date;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.User;

import es.caib.seycon.ng.comu.AccountCriteria;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class Accounts extends AbstractService {
	/**
	 * @param name
	 *            account Name (% wildcard is accepted)
	 * @param description
	 *            account Description (% wildcard is accepted)
	 * @param type
	 *            account Type. Null for any account Type
	 * @param grantedUser
	 *            granted userName filter
	 * @param grantedGroups
	 *            granted group name filter
	 * @param grantedRoles
	 *            granted role name filter
	 * @param system
	 *            managed system filter
	 * @return list of accounts matching the criteria.
	 * @throws UnexpectedException
	 */
	public Collection<Account> findAccounts(String name,
			String description, AccountType type, String grantedUser,
			String grantedGroups, String grantedRoles, String system)
			throws UnexpectedException {
		try {
			AccountCriteria criteria = new AccountCriteria();
			criteria.setName(name);
			criteria.setDescription(description);
			criteria.setType(type);
			criteria.setGrantedUsers(grantedUser);
			criteria.setGrantedGroups(grantedGroups);
			criteria.setGrantedRoles(grantedRoles);
			criteria.setDispatcher(system);
			return getAccountService().findAccountsByCriteria(criteria);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Creates a new account
	 * 
	 * @param account the account to create
	 * @return the created account
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	public Account create (Account account) throws UnexpectedException, AccountAlreadyExistsException
	{
		try {
			return getAccountService().createAccount(account);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Removes an account
	 * 
	 * @param account the account to remove
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	public void remove (Account account) throws UnexpectedException
	{
		try {
			getAccountService().removeAccount(account);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Update the account data
	 * 
	 * @param account account to update
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	public void update (Account account) throws UnexpectedException, AccountAlreadyExistsException
	{
		try {
			getAccountService().updateAccount(account);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Update the account password
	 * 
	 * @param account account to update
	 * @param password the password to set
	 * 
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	public void setAccountPassword (Account account, String password) throws UnexpectedException
	{
		try {
			getAccountService().setAccountPassword(account, new Password(password));
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

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
	public void setHPAccountPassword (Account account, String password, Date untilDate, boolean force) throws UnexpectedException
	{
		try {
			getAccountService().setHPAccountPassword(account, new Password(password), untilDate, force);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Gets the account passsword
	 * 
	 * @param account account to get password of
	 * @return the stored password for the account
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	public String getAccountPassword (Account account) throws UnexpectedException
	{
		try {
			Password p = getAccountService().queryAccountPassword(account);
			if (p == null)
				return null;
			else
				return p.getPassword();
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * Get the role grants for an account
	 * 
	 * @param account account to query on
	 * @return the list of granted roles
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	public Collection<RoleGrant> getRoleGrants (Account account) throws UnexpectedException
	{
		try {
			return getAppService().findRoleGrantByAccount(account.getId());
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Get the effective role grants for an account
	 * 
	 * @param account account to query on
	 * @return the list of granted roles, either directly or not
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	public Collection<RoleGrant> getEffectiveRoleGrants (Account account) throws UnexpectedException
	{
		try {
			return getAppService().findEffectiveRolGrantByAccount(account.getId());
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
