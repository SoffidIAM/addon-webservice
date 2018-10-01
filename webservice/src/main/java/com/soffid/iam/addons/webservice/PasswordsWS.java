package com.soffid.iam.addons.webservice;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface PasswordsWS {

	/**
	 * Changes an account password
	 * 
	 * @param account 
	 * @param dispatcher 
	 * @param oldPassword 
	 * @param newPassword 
	 *            
	 * @return list of active sessions on this host
	 * @throws UnexpectedException
	 * @throws com.soffid.iam.addons.webservice.exception.BadPasswordException 
	 */
	void changePassword(String account, String dispatcher, String oldPassword, String newPassword)
			throws UnexpectedException, com.soffid.iam.addons.webservice.exception.BadPasswordException;

	/**
	 * Verifies an account password
	 * 
	 * @param account account name
	 * @param system system name 
	 * @param password password to verify 
	 * @return true if the password is correct
	 *            
	 * @return list of active sessions on this host
	 * @throws UnexpectedException
	 * @throws com.soffid.iam.addons.webservice.exception.BadPasswordException 
	 */
	boolean validatePassword(String account, String system, String password)
			throws UnexpectedException, com.soffid.iam.addons.webservice.exception.BadPasswordException;

	/**
	 * Checks if password is expired
	 * 
	 * @param account account name
	 * @param system system name 
	 * @return true if the password is expired
	 *            
	 * @return list of active sessions on this host
	 * @throws UnexpectedException
	 * @throws com.soffid.iam.addons.webservice.exception.BadPasswordException 
	 */
	boolean isPasswordExpired(String account, String system)
			throws UnexpectedException, com.soffid.iam.addons.webservice.exception.BadPasswordException;
}
