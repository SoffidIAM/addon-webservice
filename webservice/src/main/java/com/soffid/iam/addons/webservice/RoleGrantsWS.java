package com.soffid.iam.addons.webservice;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Role;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")	
public interface RoleGrantsWS {

	/**
	 * Grants a role to a account
	 * 
	 * @param account account to grant the role
	 * @role the granted role
	 * @scopeValue (optional) the scope to assign the role on
	 * @throws UnexpectedException
	 */
	void grantToAccount(Account account, Role role, String scopeValue) throws UnexpectedException;

	/**
	 * Reovkes a role from a account
	 * 
	 * @param account account to grant the role
	 * @role the granted role
	 * @scopeValue (optional) the scope to assign the role on
	 * @throws UnexpectedException
	 */
	void revokeFromAccount(Account account, Role role, String scopeValue) throws UnexpectedException;

	/**
	 * Grants a role to another role
	 * 
	 * @param ownerRole the role whose owners will also have grantedRole granted
	 * @param ownedRole the role to be granted to any ownerRole owner
	 * @scopeValue (optional) the scope to assign the role on
	 * @throws UnexpectedException
	 */
	void grantToRole(Role ownerRole, Role ownedRole, String scopeValue) throws UnexpectedException;

	/**
	 * Reovkes a role from another role
	 * 
	 * @param account account to grant the role
	 * @role the granted role
	 * @scopeValue (optional) the scope to assign the role on
	 * @throws UnexpectedException
	 */
	void revokeFromRole(Role ownerRole, Role ownedRole, String scopeValue) throws UnexpectedException;

	/**
	 * Grants a role to another role
	 * 
	 * @param ownerRole the role whose owners will also have grantedRole granted
	 * @param ownedRole the role to be granted to any ownerRole owner
	 * @throws UnexpectedException
	 */
	void grantToGroup(Group group, Role ownedRole) throws UnexpectedException;

	/**
	 * Reovkes a role from another role
	 * 
	 * @param account account to grant the role
	 * @role the granted role
	 * @scopeValue (optional) the scope to assign the role on
	 * @throws UnexpectedException
	 */
	void revokeFromGroup(Group group, Role ownedRole, String scopeValue) throws UnexpectedException;
}
