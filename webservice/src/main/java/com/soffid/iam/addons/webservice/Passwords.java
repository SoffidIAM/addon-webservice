package com.soffid.iam.addons.webservice;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;

import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.InvalidPasswordException;

@WebService(
		portName = "Passwords",
		serviceName = "services/Passwords",
		targetNamespace = "http://iam.soffid.com/wsdl",
		endpointInterface = "com.soffid.iam.addons.webservice.PasswordsWS"
)
public class Passwords extends AbstractService implements PasswordsWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordsWS#changePassword(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
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

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordsWS#validatePassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean validatePassword( String account, String system, String password)
			throws UnexpectedException, com.soffid.iam.addons.webservice.exception.BadPasswordException {
		try {
			return getPasswordService().checkPassword(account, system, new Password(password), true, false);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordsWS#isPasswordExpired(java.lang.String, java.lang.String)
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
