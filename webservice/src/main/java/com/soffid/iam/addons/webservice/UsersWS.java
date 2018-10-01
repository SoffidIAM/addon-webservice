package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.Map;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.User;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface UsersWS {

	/**
	 * Retrives a user based on it's user name
	 * 
	 * @param userName usre name to search for
	 * @return User structure
	 * @throws InternalErrorException when an error happens
	 */
	User findUserByUserName(String userName) throws UnexpectedException;

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
	java.util.Collection<com.soffid.iam.api.User> findUsers(java.lang.String userName, java.lang.String firstName,
			java.lang.String lastName, java.lang.String shortName, java.lang.String creationDate,
			java.lang.String createdBy, Boolean active, Boolean multiSession, java.lang.String comments,
			java.lang.String userType, java.lang.String profileServer, java.lang.String homeServer,
			java.lang.String mailServer, java.lang.String primaryGroup, java.lang.String mailDomain,
			java.lang.String secondaryGroup) throws UnexpectedException;

	/**
	 * 
	 * Updates user data
	 * 
	 * @param user the new user data
	 * @return updated user data.
	 * @throws UnexpectedException 
	 * @throws InternalErrorException
	 */
	com.soffid.iam.api.User update(com.soffid.iam.api.User user) throws UnexpectedException;

	/**
	 * 
	 * Creates new user
	 * 
	 * @param user new user data
	 * @return the just created user
	 * @throws UnexpectedException 
	 */
	com.soffid.iam.api.User create(com.soffid.iam.api.User user) throws UnexpectedException;

	/**
	 * Remove a user
	 * 
	 * @param user the user to remove
	 * @throws UnexpectedException 
	 */
	void remove(User user) throws UnexpectedException;

	/**
	 * Sets a new password for the user
	 * 
	 * @param user user data
	 * @param passwordDomain password domain to change password for
	 * @return the new password
	 * @throws InternalErrorException
	 * @throws UnexpectedException 
	 */
	String resetPassword(User user, String passwordDomain) throws UnexpectedException;

	/**
	 * Gets the secondary group assignments for a user
	 * 
	 * @param user
	 * @return
	 * @throws InternalErrorException
	 * @throws UnexpectedException 
	 */
	Collection<GroupUser> getUserGroups(User user) throws UnexpectedException;

	/**
	 * A user joins a secondary group
	 * 
	 * @param user user descriptor
	 * @param groupName group to join
	 * @throws InternalErrorException
	 * @throws UnexpectedException 
	 */
	void joinGroup(User user, String groupName) throws UnexpectedException;

	/**
	 * A user leaves a secondary gorup
	 * 
	 * @param user user descriptor
	 * @param groupName group to leave from
	 * @throws InternalErrorException
	 * @throws UnexpectedException 
	 */
	void leaveGroup(User user, String groupName) throws UnexpectedException;

	/**
	 * Get the role grants for a user
	 * 
	 * @param account account to query on
	 * @return the list of granted roles
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	Collection<RoleGrant> getDirectRoleGrants(User user) throws UnexpectedException;

	/**
	 * Get the role grants for a auser
	 * 
	 * @param account account to query on
	 * @return the list of granted roles, either directly or not
	 * @throws UnexpectedException
	 * @throws AccountAlreadyExistsException
	 */
	Collection<RoleGrant> getEffectiveRoleGrants(User user) throws UnexpectedException;

	Map<String, String> getExtraAttributes(User user) throws UnexpectedException;

	String getExtraAttribute(User user, String attribute) throws UnexpectedException;

	void setExtraAttribute(User user, String attribute, String value) throws UnexpectedException;
}
