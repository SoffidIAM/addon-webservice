package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeAuthorization;
import com.soffid.iam.api.AccessTreeExecution;
import com.soffid.iam.api.AccessTreeExecutionType;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
        portName = "EntryPoints",
        serviceName = "services/EntryPoints",
        targetNamespace = "http://iam.soffid.com/wsdl",
        endpointInterface = "com.soffid.iam.addons.webservice.EntryPointsWS"
)
public class EntryPoints extends AbstractService implements EntryPointsWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#getRoot()
	 */
	public AccessTree getRoot () throws UnexpectedException
	{
		try {
			return getAccessTreeService().findRoot();
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#getChildren(com.soffid.iam.api.AccessTree)
	 */
	public Collection<AccessTree> getChildren (AccessTree accessTree) throws UnexpectedException
	{
		try {
			return getAccessTreeService().findChildren(accessTree);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#create(com.soffid.iam.api.AccessTree)
	 */
	public AccessTree create (AccessTree accessTree) throws UnexpectedException
	{
		try {
			return getAccessTreeService().create(accessTree);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#remove(com.soffid.iam.api.AccessTree)
	 */
	public void remove (AccessTree accessTree) throws UnexpectedException
	{
		try {
			getAccessTreeService().delete(accessTree);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#update(com.soffid.iam.api.AccessTree)
	 */
	public AccessTree update (AccessTree accessTree) throws UnexpectedException
	{
		try {
			return getAccessTreeService().update(accessTree);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#move(com.soffid.iam.api.AccessTree, com.soffid.iam.api.AccessTree)
	 */
	public boolean move (AccessTree accessTree, AccessTree newParent) throws UnexpectedException
	{
		try {
			return getAccessTreeService().moveApplicationAccessTreeMenu(accessTree, newParent);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#reorder(com.soffid.iam.api.AccessTree, com.soffid.iam.api.AccessTree)
	 */
	public boolean reorder (AccessTree previousAccessTree, AccessTree nextAccessTree) throws UnexpectedException
	{
		try {
			return getAccessTreeService().reorderApplicationAccess(previousAccessTree, nextAccessTree);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}


	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#link(com.soffid.iam.api.AccessTree, com.soffid.iam.api.AccessTree)
	 */
	public boolean link (AccessTree accessTree, AccessTree newParent) throws UnexpectedException
	{
		try {
			return getAccessTreeService().copyApplicationAccessLink(accessTree, newParent);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#clone(com.soffid.iam.api.AccessTree, com.soffid.iam.api.AccessTree)
	 */
	public boolean clone (AccessTree accessTree, AccessTree newParent) throws UnexpectedException
	{
		try {
			return getAccessTreeService().copyApplicationAccess(accessTree, newParent);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#getPermissions(com.soffid.iam.api.AccessTree)
	 */
	public Collection<AccessTreeAuthorization> getPermissions (AccessTree accessTree) throws UnexpectedException
	{
		try {
			return getAccessTreeService().getAuthorizationsApplicationAcessTree(accessTree);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#setPermissions(com.soffid.iam.api.AccessTree, java.util.Collection)
	 */
	public void setPermissions (AccessTree accessTree, Collection<AccessTreeAuthorization> authorizations) throws UnexpectedException
	{
		try {
			for (AccessTreeAuthorization auth: getAccessTreeService().getAuthorizationsApplicationAcessTree(accessTree))
			{
				getAccessTreeService().deleteAuthorization(accessTree, auth);
			}
			for (AccessTreeAuthorization auth: authorizations)
			{
				getAccessTreeService().createAuthorization(accessTree, auth);
			}
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#getExecutions(com.soffid.iam.api.AccessTree)
	 */
	public Collection<AccessTreeExecution> getExecutions (AccessTree accessTree) throws UnexpectedException
	{
		try {
			return getAccessTreeService().getExecutions(accessTree);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#createExecution(com.soffid.iam.api.AccessTree, com.soffid.iam.api.AccessTreeExecution)
	 */
	public AccessTreeExecution createExecution (AccessTree accessTree, AccessTreeExecution execution) throws UnexpectedException
	{
		try {
			return getAccessTreeService().createExecution(accessTree, execution);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#updateExecution(com.soffid.iam.api.AccessTree, com.soffid.iam.api.AccessTreeExecution)
	 */
	public AccessTreeExecution updateExecution (AccessTree accessTree, AccessTreeExecution execution) throws UnexpectedException
	{
		try {
			return getAccessTreeService().updateExecution(accessTree, execution);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#removeExecution(com.soffid.iam.api.AccessTree, com.soffid.iam.api.AccessTreeExecution)
	 */
	public void removeExecution (AccessTree accessTree, AccessTreeExecution execution) throws UnexpectedException
	{
		try {
			getAccessTreeService().deleteExecution(accessTree, execution);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.EntryPointsWS#getAllowedMimeTypes()
	 */
	public Collection<AccessTreeExecutionType> getAllowedMimeTypes () throws UnexpectedException
	{
		try {
			return getAccessTreeService().getAllMimeTypeExecution();
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
