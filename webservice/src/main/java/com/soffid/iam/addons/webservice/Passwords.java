package com.soffid.iam.addons.webservice;

import java.util.Collection;
import java.util.Date;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.Session;
import com.soffid.iam.api.User;

import es.caib.seycon.ng.comu.AccountCriteria;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.InvalidPasswordException;

/**
 * @author bubu
 * 
 */
public class Passwords extends AbstractService {
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
	public void changePassword( String account, String dispatcher, String oldPassword, String newPassword)
			throws UnexpectedException, com.soffid.iam.addons.webservice.exception.BadPasswordException {
		try {
			getPasswordService().changePassword(account, dispatcher, new Password(oldPassword), new Password(newPassword));
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		} catch (BadPasswordException e) {
			throw new com.soffid.iam.addons.webservice.exception.BadPasswordException(e.getMessage());
		} catch (InvalidPasswordException e) {
			throw new com.soffid.iam.addons.webservice.exception.BadPasswordException(e.getMessage());
		}
	}

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
	public boolean validatePassword( String account, String system, String password)
			throws UnexpectedException, com.soffid.iam.addons.webservice.exception.BadPasswordException {
		try {
			return getPasswordService().checkPassword(account, system, new Password(password), true, false);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}


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
	public boolean isPasswordExpired ( String account, String system)
			throws UnexpectedException, com.soffid.iam.addons.webservice.exception.BadPasswordException {
		try {
			return getPasswordService().checkExpiredPassword(account, system);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

}
