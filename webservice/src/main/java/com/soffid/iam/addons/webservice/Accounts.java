package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.Date;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.RoleGrant;

import es.caib.seycon.ng.comu.AccountCriteria;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
@WebService(
        portName = "Accounts",
        serviceName = "services/Accounts",
        targetNamespace = "http://iam.soffid.com/wsdl",
        endpointInterface = "com.soffid.iam.addons.webservice.AccountsWS"
)
// @HandlerChain(file = "server-handlers.xml")
public class Accounts extends AbstractService implements AccountsWS {
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.AccountsWS#findAccounts(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Collection<Account> findAccounts(String name,
			String description, String  accountType, String grantedUser,
			String grantedGroups, String grantedRoles, String system)
			throws UnexpectedException {
		try {
			AccountCriteria criteria = new AccountCriteria();
			criteria.setName(name);
			criteria.setDescription(description);
			if (accountType != null && ! accountType.isEmpty())
				criteria.setType(AccountType.fromString(accountType));
			criteria.setGrantedUsers(grantedUser);
			criteria.setGrantedGroups(grantedGroups);
			criteria.setGrantedRoles(grantedRoles);
			criteria.setDispatcher(system);
			return getAccountService().findAccountsByCriteria(criteria);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.AccountsWS#create(com.soffid.iam.api.Account)
	 */
	public Account create (Account account) throws UnexpectedException, AccountAlreadyExistsException
	{
		try {
			return getAccountService().createAccount(account);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.AccountsWS#remove(com.soffid.iam.api.Account)
	 */
	public void remove (Account account) throws UnexpectedException
	{
		try {
			getAccountService().removeAccount(account);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.AccountsWS#update(com.soffid.iam.api.Account)
	 */
	public void update (Account account) throws UnexpectedException, AccountAlreadyExistsException
	{
		try {
			getAccountService().updateAccount(account);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.AccountsWS#setAccountPassword(com.soffid.iam.api.Account, java.lang.String)
	 */
	public void setAccountPassword (Account account, String password) throws UnexpectedException
	{
		try {
			getAccountService().setAccountPassword(account, new Password(password));
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.AccountsWS#setHPAccountPassword(com.soffid.iam.api.Account, java.lang.String, java.util.Date, boolean)
	 */
	public void setHPAccountPassword (Account account, String password, Date untilDate, boolean force) throws UnexpectedException
	{
		try {
			getAccountService().setHPAccountPassword(account, new Password(password), untilDate, force);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.AccountsWS#getAccountPassword(com.soffid.iam.api.Account)
	 */
	public String getAccountPassword (Account account) throws UnexpectedException
	{
		try {
			com.soffid.iam.api.Password p = getAccountService().queryAccountPassword(account);
			if (p == null)
				return null;
			else
				return p.getPassword();
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.AccountsWS#getRoleGrants(com.soffid.iam.api.Account)
	 */
	public Collection<RoleGrant> getRoleGrants (Account account) throws UnexpectedException
	{
		try {
			return getAppService().findRoleGrantByAccount(account.getId());
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.AccountsWS#getEffectiveRoleGrants(com.soffid.iam.api.Account)
	 */
	public Collection<RoleGrant> getEffectiveRoleGrants (Account account) throws UnexpectedException
	{
		try {
			return getAppService().findEffectiveRoleGrantByAccount(account.getId());
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
