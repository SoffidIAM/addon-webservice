package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.RoleGrant;

import es.caib.seycon.ng.comu.AccountCriteria;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class RoleGrants extends AbstractService {
	/**
	 * Grants a role to a account
	 * 
	 * @param account account to grant the role
	 * @role the granted role
	 * @scopeValue (optional) the scope to assign the role on
	 * @throws UnexpectedException
	 */
	public void grantToAccount (Account account, Role role, String scopeValue) throws UnexpectedException
	{
		try {
			RoleAccount roleAccount = new RoleAccount();
			roleAccount.setAccountSystem(account.getSystem());
			roleAccount.setAccountId(account.getId());
			roleAccount.setAccountName(account.getName());
			roleAccount.setInformationSystemName(role.getInformationSystemName());
			roleAccount.setBpmEnforced(role.getBpmEnforced()? "S": "N");
			roleAccount.setSystem(role.getSystem());
			
			DomainValue domainValue = new DomainValue ();
			domainValue.setDescription("");
			domainValue.setDomainName(role.getDomain().getName());
			domainValue.setExternalCodeDomain(role.getDomain().getExternalCode());
			domainValue.setValue(scopeValue);
			
			roleAccount.setDomainValue(domainValue);
			roleAccount.setGroupDescription(null);
			roleAccount.setRoleDescription(role.getDescription());
			roleAccount.setRoleName(role.getName());
			roleAccount.setUserCode(null);
			roleAccount.setUserFullName(account.getDescription());
			
			getAppService().create(roleAccount);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Reovkes a role from a account
	 * 
	 * @param account account to grant the role
	 * @role the granted role
	 * @scopeValue (optional) the scope to assign the role on
	 * @throws UnexpectedException
	 */
	public void revokeFromAccount (Account account, Role role, String scopeValue) throws UnexpectedException
	{
		try {
			for (RoleAccount ra: getAppService().findRoleAccountByAccount(account.getId()))
			{
				if (ra.getRoleName().equals (role.getName()) &&
					ra.getSystem().equals(role.getSystem()) &&
					( scopeValue == null 
					  || scopeValue.equals(ra.getDomainValue().getValue())))
				{
					getAppService().delete(ra);
					
				}
			}
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * Grants a role to another role
	 * 
	 * @param ownerRole the role whose owners will also have grantedRole granted
	 * @param ownedRole the role to be granted to any ownerRole owner
	 * @scopeValue (optional) the scope to assign the role on
	 * @throws UnexpectedException
	 */
	public void grantToRole (Role ownerRole, Role ownedRole, String scopeValue) throws UnexpectedException
	{
		try {
			Role grantedRole = getAppService().findRoleById(ownedRole.getId());
			
			RoleGrant rg = new RoleGrant();
			rg.setSystem(grantedRole.getSystem());
			rg.setDomainValue(scopeValue);
			rg.setHasDomain(scopeValue != null);
			rg.setOwnerRole(ownerRole.getId());
			rg.setRoleId(grantedRole.getId());
			rg.setRoleName(grantedRole.getName());
			
			grantedRole.getOwnerRoles().add(rg);
			
			getAppService().update (grantedRole);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Reovkes a role from another role
	 * 
	 * @param account account to grant the role
	 * @role the granted role
	 * @scopeValue (optional) the scope to assign the role on
	 * @throws UnexpectedException
	 */
	public void revokeFromRole (Role ownerRole, Role ownedRole, String scopeValue) throws UnexpectedException
	{
		try {
			Role grantedRole = getAppService().findRoleById(ownedRole.getId());

			Iterator<RoleGrant> it = grantedRole.getOwnerRoles().iterator();
			while (it.hasNext())
			{
				RoleGrant rg = it.next();
				if (rg.getOwnerRole() != null && rg.getOwnerRole().equals (ownerRole.getId()))
				{
					if (scopeValue == null || scopeValue.equals (rg.getDomainValue()))
						it.remove();
				}
					
			}
			getAppService().update (grantedRole);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	
	/**
	 * Grants a role to another role
	 * 
	 * @param ownerRole the role whose owners will also have grantedRole granted
	 * @param ownedRole the role to be granted to any ownerRole owner
	 * @throws UnexpectedException
	 */
	public void grantToGroup (Group group, Role ownedRole) throws UnexpectedException
	{
		try {
			Role grantedRole = getAppService().findRoleById(ownedRole.getId());
			grantedRole.getOwnerGroups().add(group);
			
			getAppService().update (grantedRole);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Reovkes a role from another role
	 * 
	 * @param account account to grant the role
	 * @role the granted role
	 * @scopeValue (optional) the scope to assign the role on
	 * @throws UnexpectedException
	 */
	public void revokeFromGroup (Group group, Role ownedRole, String scopeValue) throws UnexpectedException
	{
		try {
			Role grantedRole = getAppService().findRoleById(ownedRole.getId());

			Iterator<Group> it = grantedRole.getOwnerGroups().iterator();
			while (it.hasNext())
			{
				Group g2 = it.next();
				if (g2.getId().equals((group.getId())))
				{
					it.remove();
				}
					
			}
			getAppService().update (grantedRole);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
}
