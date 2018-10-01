package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.LinkedList;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleGrant;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
        portName = "Groups",
        serviceName = "services/Groups",
        targetNamespace = "http://iam.soffid.com/wsdl",
        endpointInterface = "com.soffid.iam.addons.webservice.GroupsWS"
)
public class Groups extends AbstractService implements GroupsWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.GroupsWS#findGroups(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String)
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

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.GroupsWS#create(com.soffid.iam.api.Group)
	 */
	public Group create (Group group) throws UnexpectedException
	{
		try {
			return getGroupService().create(group);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.GroupsWS#remove(com.soffid.iam.api.Group)
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

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.GroupsWS#update(com.soffid.iam.api.Group)
	 */
	public Group update (Group group) throws UnexpectedException
	{
		try {
			return getGroupService().update(group);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.GroupsWS#getRoleGrants(com.soffid.iam.api.Group)
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
