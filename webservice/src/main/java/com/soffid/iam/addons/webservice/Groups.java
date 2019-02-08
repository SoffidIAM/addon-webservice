package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.LinkedList;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleGrant;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class Groups extends AbstractService {
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
	public Collection<Group> findGroups(String name, String parent, String description, String type, Boolean removed, String drive, String driveHost)
			throws UnexpectedException {
		try {
			return getGroupService().findGroupsByFilter(name, parent, drive, description, type, removed == null? null: removed.booleanValue()?"S": "N",
					driveHost, null);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	
	/**
	 * Creates a group
	 * 
	 * @param group group to create
	 * @return created group
	 * @throws UnexpectedException
	 */
	public Group create (Group group) throws UnexpectedException
	{
		try {
			return getGroupService().create(group);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Removes a group
	 * 
	 * @param group to remove
	 * @return the removed group
	 * @throws UnexpectedException
	 */
	public Group remove (Group group) throws UnexpectedException
	{
		try {
			group.setObsolete(true);
			return getGroupService().update(group);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Update group data
	 * 
	 * @param group the group to update
	 * @return the updated group
	 * @throws UnexpectedException
	 */
	public Group update (Group group) throws UnexpectedException
	{
		try {
			return getGroupService().update(group);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	
	/**
	 * Get the role grants for a group
	 * 
	 * @param group group to query on
	 * @return the list of granted roles
	 * @throws UnexpectedException
	 */
	public Collection<RoleGrant> getRoleGrants (Group group) throws UnexpectedException
	{
		try {
			LinkedList<RoleGrant> grants = new LinkedList<RoleGrant>();
			for (Role role: getGroupService().getRolesFromGroup(group))
			{
				RoleGrant rg = new RoleGrant ();
				rg.setSystem(role.getSystem());
				rg.setDomainValue(null);
				rg.setHasDomain(false);
				rg.setId(null);
				rg.setOwnerGroup(group.getName());
				rg.setRoleId(role.getId());
				rg.setRoleName(role.getName());
				grants.add(rg);
			}
			return grants;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}

