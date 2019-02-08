package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserData;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 *
 */
public class Users extends AbstractService {
	/**
	 * Retrives a user based on it's user name
	 * 
	 * @param userName usre name to search for
	 * @return User structure
	 * @throws InternalErrorException when an error happens
	 */
	public User findUserByUserName(String userName) throws UnexpectedException
	{
		try {
			return getUsersService().findUserByUserName(userName);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Finds users matching a criteria. Use % as wildcard charracter
	 * 
	 * @param userName user name pattern
	 * @param firstName first name pattern
	 * @param lastName last name pattern
	 * @param shortName short name (email address left side) pattern
	 * @param creationDate creation Date in start - end format.
	 * @param createdBy user who created this user
	 * @param active true or false. null stands for any of them
	 * @param multiSession true or false. null stands for any of them
	 * @param comments comments pattern
	 * @param userType user type pattern
	 * @param profileServer profile server name pattern
	 * @param homeServer home server name pattern
	 * @param mailServer mail server name pattern
	 * @param primaryGroup primary group pattern
	 * @param mailDomain mail domain (email address right sied) pattern
	 * @param secondaryGroup secondary group pattern
	 * @return list of users matching the speceified criteria
	 * @throws InternalErrorException when an error happens.
	 * @throws UnexpectedException 
	 */
	public java.util.Collection<com.soffid.iam.api.User> findUsers (
			java.lang.String userName, 
			java.lang.String firstName, 
			java.lang.String lastName, 
			java.lang.String shortName, 
			java.lang.String creationDate, 
			java.lang.String createdBy, 
			Boolean active, 
			Boolean multiSession, 
			java.lang.String comments, 
			java.lang.String userType, 
			java.lang.String profileServer, 
			java.lang.String homeServer, 
			java.lang.String mailServer, 
			java.lang.String primaryGroup, 
			java.lang.String mailDomain, 
			java.lang.String secondaryGroup) throws UnexpectedException
	{
		try {
			return getUsersService().findUserByCriteria(userName, firstName, lastName, shortName, creationDate, createdBy,
				active == null ? null: active.booleanValue() ? "S": "N" , null, 
				null, comments, userType, profileServer, homeServer, mailServer, primaryGroup, null, mailDomain, secondaryGroup, Boolean.FALSE);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * 
	 * Updates user data
	 * 
	 * @param user the new user data
	 * @return updated user data.
	 * @throws UnexpectedException 
	 * @throws InternalErrorException
	 */
	public	com.soffid.iam.api.User update(
			com.soffid.iam.api.User user) throws UnexpectedException
	{
		try {
			return getUsersService().update(user);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * 
	 * Creates new user
	 * 
	 * @param user new user data
	 * @return the just created user
	 * @throws UnexpectedException 
	 */
	public	com.soffid.iam.api.User create(
			com.soffid.iam.api.User user) throws UnexpectedException
	{
		try {
			return getUsersService().create(user);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * Remove a user
	 * 
	 * @param user the user to remove
	 * @throws UnexpectedException 
	 */
	public void remove (User user) throws UnexpectedException 
	{
		try {
			throw new InternalErrorException("Not supported");
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Sets a new password for the user
	 * 
	 * @param user user data
	 * @param passwordDomain password domain to change password for
	 * @return the new password
	 * @throws InternalErrorException
	 * @throws UnexpectedException 
	 */
	public String resetPassword (User user, String passwordDomain) throws UnexpectedException
	{
		try {
			return getUsersService().changePassword(user.getUserName(), passwordDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * Gets the secondary group assignments for a user
	 * 
	 * @param user
	 * @return
	 * @throws InternalErrorException
	 * @throws UnexpectedException 
	 */
	public Collection<GroupUser> getUserGroups (User user) throws UnexpectedException
	{
		try {
			return getGroupService().findUsersGroupByUserName(user.getUserName()); 
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * A user joins a secondary group
	 * 
	 * @param user user descriptor
	 * @param groupName group to join
	 * @throws InternalErrorException
	 * @throws UnexpectedException 
	 */
	public void joinGroup (User user, String groupName) throws UnexpectedException
	{
		try {
			GroupUser gu = new GroupUser();
			gu.setGroup(groupName);
			gu.setUser(user.getUserName());
			
			getGroupService().create(gu);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * A user leaves a secondary gorup
	 * 
	 * @param user user descriptor
	 * @param groupName group to leave from
	 * @throws InternalErrorException
	 * @throws UnexpectedException 
	 */
	public void leaveGroup(User user, String groupName) throws UnexpectedException 
	{
		try {
			for (GroupUser gu: getGroupService().findUsersGroupByUserName(user.getUserName()))
			{
				if (gu.getGroup().equals (groupName))
					getGroupService().delete(gu);
			}
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * Get the role grants for a user
	 * 
	 * @param account account to query on
	 * @return the list of granted roles
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	public Collection<RoleGrant> getDirectRoleGrants (User user) throws UnexpectedException
	{
		try {
			LinkedList<RoleGrant> grants = new LinkedList<RoleGrant>();
			for (Account account : getAccountService().getUserAccounts(user))
			{
				grants.addAll (getAppService().findRoleGrantByAccount(account.getId()));
			}
			return grants;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Get the role grants for a auser
	 * 
	 * @param account account to query on
	 * @return the list of granted roles, either directly or not
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	public Collection<RoleGrant> getEffectiveRoleGrants (User user) throws UnexpectedException
	{
		try {
			return getAppService().findEffectiveRoleGrantByUser(user.getId());
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	public Map<String,String> getExtraAttributes(User user) throws UnexpectedException
	{
		try {
			Map<String, String> m = new HashMap<String, String>();
			for (UserData data: getUsersService().findUserDataByUserName(user.getUserName()))
			{
				m.put(data.getAttribute(), data.getValue());
			}
			return m;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	public String getExtraAttribute(User user, String attribute) throws UnexpectedException
	{
		try {
			for (UserData data: getUsersService().findUserDataByUserName(user.getUserName()))
			{
				if (data.getAttribute().equals(attribute))
					return data.getValue();
			}
			return null;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	public void setExtraAttribute(User user, String attribute, String value) throws UnexpectedException
	{
		try {
			for (UserData data: getUsersService().findUserDataByUserName(user.getUserName()))
			{
				if (data.getAttribute().equals(attribute))
				{
					data.setValue(value);
					getAdditionalDataService().update(data);
				}
			}
			UserData userData = new UserData();
			userData.setAttribute(attribute);
			userData.setValue(value);
			userData.setUser(user.getUserName());
			getAdditionalDataService().create(userData);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

}
 	