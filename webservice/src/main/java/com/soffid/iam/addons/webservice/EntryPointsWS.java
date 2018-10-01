package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeAuthorization;
import com.soffid.iam.api.AccessTreeExecution;
import com.soffid.iam.api.AccessTreeExecutionType;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface EntryPointsWS {

	/**
	 * Gets the main access tree menu
	 *
	 * @return root entry poing
	 * @throws UnexpectedException
	 */
	AccessTree getRoot() throws UnexpectedException;

	/**
	 * Retrieves the children menus and entry points
	 * 
	 * @param accessTree the parent access tree item
	 * @return the children access tree items
	 * @throws UnexpectedException
	 */
	Collection<AccessTree> getChildren(AccessTree accessTree) throws UnexpectedException;

	/**
	 * Creates a new access tree menu or entry point
	 * 
	 * @param accessTree the item to create
	 * @return the created item
	 * @throws UnexpectedException
	 */
	AccessTree create(AccessTree accessTree) throws UnexpectedException;

	/**
	 * Removes a access tree menu or entry point. If the entry point is linked on more than one menu,
	 * those copies will be kept.
	 * 
	 * @param accessTree the item to remove
	 * @throws UnexpectedException
	 */
	void remove(AccessTree accessTree) throws UnexpectedException;

	/**
	 * Updates an access tree menu or entry point
	 * 
	 * @param accessTree the entry point to update 
	 * @return the updated entry point
	 * @throws UnexpectedException
	 */
	AccessTree update(AccessTree accessTree) throws UnexpectedException;

	/**
	 * Changes an access tree menu or entry point parent
	 * 
	 * @param accessTree the entry point to change
	 * @param newParent the new parent
	 * @return true if the operation is successful
	 * @throws UnexpectedException
	 */
	boolean move(AccessTree accessTree, AccessTree newParent) throws UnexpectedException;

	/**
	 * Changes the order of two menu or entry points on the same access tree menu
	 * 
	 * @param previousAccessTree the entry point to place before nextAccessTree
	 * @param nextAccessTree the reference entry point.
	 * @return true if he operation is successful
	 * @throws UnexpectedException
	 */
	boolean reorder(AccessTree previousAccessTree, AccessTree nextAccessTree) throws UnexpectedException;

	/**
	 * Creates a hard link for a existing accessTree.
	 * @param accessTree the entry point to link to
	 * @param newParent the menu where to place the entry point.
	 * @return true if the opreaction is successful
	 * @throws UnexpectedException
	 */
	boolean link(AccessTree accessTree, AccessTree newParent) throws UnexpectedException;

	/**
	 * Creates a clone of an existing menu or entry point
	 * 
	 * @param accessTree the entry point to clone
	 * @param newParent the parent where to place the cloned entry point
	 * @return true if the operation is successful
	 * @throws UnexpectedException
	 */
	boolean clone(AccessTree accessTree, AccessTree newParent) throws UnexpectedException;

	/**
	 * Gets a menu or entry point Access Control List
	 * @param accessTree the entry point
	 * @return the access control list
	 * @throws UnexpectedException
	 */
	Collection<AccessTreeAuthorization> getPermissions(AccessTree accessTree) throws UnexpectedException;

	/**
	 * Sets a menu or entry point access control list
	 * @param accessTree the entry point
	 * @param authorizations the desired acccess control list
	 * @throws UnexpectedException
	 */
	void setPermissions(AccessTree accessTree, Collection<AccessTreeAuthorization> authorizations)
			throws UnexpectedException;

	/**
	 * Gets the execution scripts to be launched when the user opens a entry point
	 * @param accessTree the entry point
	 * @return the list of enabled executions
	 * @throws UnexpectedException
	 */
	Collection<AccessTreeExecution> getExecutions(AccessTree accessTree) throws UnexpectedException;

	/**
	 * Creates an execution for an access tree entry point
	 * @param accessTree the entry point
	 * @param execution the execution to create
	 * @return the created execution
	 * @throws UnexpectedException
	 */
	AccessTreeExecution createExecution(AccessTree accessTree, AccessTreeExecution execution)
			throws UnexpectedException;

	/**
	 * Updates a entry point exeuction
	 * 
	 * @param accessTree the entry point
	 * @param execution the execution to update
	 * @return the updated execution
	 * @throws UnexpectedException
	 */
	AccessTreeExecution updateExecution(AccessTree accessTree, AccessTreeExecution execution)
			throws UnexpectedException;

	/**
	 * Removes a entry point execution
	 * @param accessTree the entry point
	 * @param execution the execution to remove
	 * @throws UnexpectedException
	 */
	void removeExecution(AccessTree accessTree, AccessTreeExecution execution) throws UnexpectedException;

	/**
	 * Obtains the list of allowed mime types
	 * 
	 * @return the list of allowed mime types
	 * @throws UnexpectedException
	 */
	Collection<AccessTreeExecutionType> getAllowedMimeTypes() throws UnexpectedException;
}
