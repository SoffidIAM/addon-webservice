package com.soffid.iam.addons.webservice;

import java.util.Collection;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeAuthorization;
import com.soffid.iam.api.AccessTreeExecution;
import com.soffid.iam.api.AccessTreeExecutionType;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 * 
 */
public class EntryPoints extends AbstractService {
	/**
	 * Gets the main access tree menu
	 *
	 * @return root entry poing
	 * @throws UnexpectedException
	 */
	public AccessTree getRoot () throws UnexpectedException
	{
		try {
			return getAccessTreeService().findRoot();
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Retrieves the children menus and entry points
	 * 
	 * @param accessTree the parent access tree item
	 * @return the children access tree items
	 * @throws UnexpectedException
	 */
	public Collection<AccessTree> getChildren (AccessTree accessTree) throws UnexpectedException
	{
		try {
			return getAccessTreeService().findChildren(accessTree);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Creates a new access tree menu or entry point
	 * 
	 * @param accessTree the item to create
	 * @return the created item
	 * @throws UnexpectedException
	 */
	public AccessTree create (AccessTree accessTree) throws UnexpectedException
	{
		try {
			return getAccessTreeService().create(accessTree);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Removes a access tree menu or entry point. If the entry point is linked on more than one menu,
	 * those copies will be kept.
	 * 
	 * @param accessTree the item to remove
	 * @throws UnexpectedException
	 */
	public void remove (AccessTree accessTree) throws UnexpectedException
	{
		try {
			getAccessTreeService().delete(accessTree);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Updates an access tree menu or entry point
	 * 
	 * @param accessTree the entry point to update 
	 * @return the updated entry point
	 * @throws UnexpectedException
	 */
	public AccessTree update (AccessTree accessTree) throws UnexpectedException
	{
		try {
			return getAccessTreeService().update(accessTree);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * Changes an access tree menu or entry point parent
	 * 
	 * @param accessTree the entry point to change
	 * @param newParent the new parent
	 * @return true if the operation is successful
	 * @throws UnexpectedException
	 */
	public boolean move (AccessTree accessTree, AccessTree newParent) throws UnexpectedException
	{
		try {
			return getAccessTreeService().moveApplicationAccessTreeMenu(accessTree, newParent);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Changes the order of two menu or entry points on the same access tree menu
	 * 
	 * @param previousAccessTree the entry point to place before nextAccessTree
	 * @param nextAccessTree the reference entry point.
	 * @return true if he operation is successful
	 * @throws UnexpectedException
	 */
	public boolean reorder (AccessTree previousAccessTree, AccessTree nextAccessTree) throws UnexpectedException
	{
		try {
			return getAccessTreeService().reorderApplicationAccess(previousAccessTree, nextAccessTree);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}


	/**
	 * Creates a hard link for a existing accessTree.
	 * @param accessTree the entry point to link to
	 * @param newParent the menu where to place the entry point.
	 * @return true if the opreaction is successful
	 * @throws UnexpectedException
	 */
	public boolean link (AccessTree accessTree, AccessTree newParent) throws UnexpectedException
	{
		try {
			return getAccessTreeService().copyApplicationAccessLink(accessTree, newParent);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Creates a clone of an existing menu or entry point
	 * 
	 * @param accessTree the entry point to clone
	 * @param newParent the parent where to place the cloned entry point
	 * @return true if the operation is successful
	 * @throws UnexpectedException
	 */
	public boolean clone (AccessTree accessTree, AccessTree newParent) throws UnexpectedException
	{
		try {
			return getAccessTreeService().copyApplicationAccess(accessTree, newParent);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Gets a menu or entry point Access Control List
	 * @param accessTree the entry point
	 * @return the access control list
	 * @throws UnexpectedException
	 */
	public Collection<AccessTreeAuthorization> getPermissions (AccessTree accessTree) throws UnexpectedException
	{
		try {
			return getAccessTreeService().getAuthorizationsApplicationAcessTree(accessTree);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Sets a menu or entry point access control list
	 * @param accessTree the entry point
	 * @param authorizations the desired acccess control list
	 * @throws UnexpectedException
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

	/**
	 * Gets the execution scripts to be launched when the user opens a entry point
	 * @param accessTree the entry point
	 * @return the list of enabled executions
	 * @throws UnexpectedException
	 */
	public Collection<AccessTreeExecution> getExecutions (AccessTree accessTree) throws UnexpectedException
	{
		try {
			return getAccessTreeService().getExecutions(accessTree);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Creates an execution for an access tree entry point
	 * @param accessTree the entry point
	 * @param execution the execution to create
	 * @return the created execution
	 * @throws UnexpectedException
	 */
	public AccessTreeExecution createExecution (AccessTree accessTree, AccessTreeExecution execution) throws UnexpectedException
	{
		try {
			return getAccessTreeService().createExecution(accessTree, execution);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Updates a entry point exeuction
	 * 
	 * @param accessTree the entry point
	 * @param execution the execution to update
	 * @return the updated execution
	 * @throws UnexpectedException
	 */
	public AccessTreeExecution updateExecution (AccessTree accessTree, AccessTreeExecution execution) throws UnexpectedException
	{
		try {
			return getAccessTreeService().updateExecution(accessTree, execution);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * Removes a entry point execution
	 * @param accessTree the entry point
	 * @param execution the execution to remove
	 * @throws UnexpectedException
	 */
	public void removeExecution (AccessTree accessTree, AccessTreeExecution execution) throws UnexpectedException
	{
		try {
			getAccessTreeService().deleteExecution(accessTree, execution);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Obtains the list of allowed mime types
	 * 
	 * @return the list of allowed mime types
	 * @throws UnexpectedException
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
