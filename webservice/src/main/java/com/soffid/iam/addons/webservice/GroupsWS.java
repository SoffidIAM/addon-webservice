package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.RoleGrant;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface GroupsWS {

	/**
	 * Retrieves groups
	 * 
	 * @param name the group name (accepts % wildcard
	 * @param parent will find children of this one  (accepts % wildcard
	 * @param description group description
	 * @param type group type
	 * @param removed will search for removed groups
	 * @param drive shraed drive letter
	 * @param driveHost shared drive host
	 * @return a group collection
	 * 
	 * @throws UnexpectedException
	 */
	Collection<Group> findGroups(String name, String parent, String description, String type, Boolean removed,
			String drive, String driveHost) throws UnexpectedException;

	/**
	 * Creates a group
	 * 
	 * @param group group to create
	 * @return created group
	 * @throws UnexpectedException
	 */
	Group create(Group group) throws UnexpectedException;

	/**
	 * Removes a group
	 * 
	 * @param group to remove
	 * @return the removed group
	 * @throws UnexpectedException
	 */
	Group remove(Group group) throws UnexpectedException;

	/**
	 * Update group data
	 * 
	 * @param group the group to update
	 * @return the updated group
	 * @throws UnexpectedException
	 */
	Group update(Group group) throws UnexpectedException;

	/**
	 * Get the role grants for a group
	 * 
	 * @param group group to query on
	 * @return the list of granted roles
	 * @throws UnexpectedException
	 */
	Collection<RoleGrant> getRoleGrants(Group group) throws UnexpectedException;
}
