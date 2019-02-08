package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserData;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
		portName = "Users",
		serviceName = "services/Users",
		targetNamespace = "http://iam.soffid.com/wsdl",
		endpointInterface = "com.soffid.iam.addons.webservice.UsersWS"
)
public class Users extends AbstractService implements UsersWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UsersWS#findUserByUserName(java.lang.String)
	 */
	public User findUserByUserName(String userName) throws UnexpectedException
	{
		try {
			return getUsersService().findUserByUserName(userName);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UsersWS#findUsers(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
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

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UsersWS#update(com.soffid.iam.api.User)
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
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UsersWS#create(com.soffid.iam.api.User)
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
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UsersWS#remove(com.soffid.iam.api.User)
	 */
	public void remove (User user) throws UnexpectedException 
	{
		try {
			throw new InternalErrorException("Not supported");
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UsersWS#resetPassword(com.soffid.iam.api.User, java.lang.String)
	 */
	public String resetPassword (User user, String passwordDomain) throws UnexpectedException
	{
		try {
			return getUsersService().changePassword(user.getUserName(), passwordDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UsersWS#getUserGroups(com.soffid.iam.api.User)
	 */
	public Collection<GroupUser> getUserGroups (User user) throws UnexpectedException
	{
		try {
			return getGroupService().findUsersGroupByUserName(user.getUserName()); 
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UsersWS#joinGroup(com.soffid.iam.api.User, java.lang.String)
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
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UsersWS#leaveGroup(com.soffid.iam.api.User, java.lang.String)
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
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UsersWS#getDirectRoleGrants(com.soffid.iam.api.User)
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

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UsersWS#getEffectiveRoleGrants(com.soffid.iam.api.User)
	 */
	public Collection<RoleGrant> getEffectiveRoleGrants (User user) throws UnexpectedException
	{
		try {
			return getAppService().findEffectiveRoleGrantByUser(user.getId());
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UsersWS#getExtraAttributes(com.soffid.iam.api.User)
	 */
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

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UsersWS#getExtraAttribute(com.soffid.iam.api.User, java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UsersWS#setExtraAttribute(com.soffid.iam.api.User, java.lang.String, java.lang.String)
	 */
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
 	
