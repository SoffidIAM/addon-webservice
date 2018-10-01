package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.UserType;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
		portName = "UserTypes",
		serviceName = "services/UserTypes",
		targetNamespace = "http://iam.soffid.com/wsdl",
		endpointInterface = "com.soffid.iam.addons.webservice.UserTypesWS"
)
public class UserTypes extends AbstractService implements UserTypesWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UserTypesWS#findUserTypes()
	 */
	public Collection<UserType> findUserTypes()
			throws UnexpectedException {
		try {
			return getUserDomainService().findAllUserType();
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UserTypesWS#create(com.soffid.iam.api.UserType)
	 */
	public UserType create (UserType userType) throws UnexpectedException
	{
		try {
			return getUserDomainService().create(userType);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UserTypesWS#remove(com.soffid.iam.api.UserType)
	 */
	public void remove (UserType userType) throws UnexpectedException
	{
		try {
			getUserDomainService().delete(userType);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UserTypesWS#update(com.soffid.iam.api.UserType)
	 */
	public UserType update (UserType userType) throws UnexpectedException
	{
		try {
			getUserDomainService().update(userType);
			return userType;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
