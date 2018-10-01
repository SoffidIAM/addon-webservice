package com.soffid.iam.addons.webservice;

import java.util.Iterator;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.RoleGrant;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
		portName = "RoleGrants",
		serviceName = "services/RoleGrants",
		targetNamespace = "http://iam.soffid.com/wsdl",
		endpointInterface = "com.soffid.iam.addons.webservice.RoleGrantsWS"
)
public class RoleGrants extends AbstractService implements RoleGrantsWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.RoleGrantsWS#grantToAccount(com.soffid.iam.api.Account, com.soffid.iam.api.Role, java.lang.String)
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

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.RoleGrantsWS#revokeFromAccount(com.soffid.iam.api.Account, com.soffid.iam.api.Role, java.lang.String)
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

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.RoleGrantsWS#grantToRole(com.soffid.iam.api.Role, com.soffid.iam.api.Role, java.lang.String)
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

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.RoleGrantsWS#revokeFromRole(com.soffid.iam.api.Role, com.soffid.iam.api.Role, java.lang.String)
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

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.RoleGrantsWS#grantToGroup(com.soffid.iam.api.Group, com.soffid.iam.api.Role)
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

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.RoleGrantsWS#revokeFromGroup(com.soffid.iam.api.Group, com.soffid.iam.api.Role, java.lang.String)
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
