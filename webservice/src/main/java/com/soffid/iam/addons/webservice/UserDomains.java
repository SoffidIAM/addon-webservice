package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.UserDomain;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
		portName = "UserDomains",
		serviceName = "services/UserDomains",
		targetNamespace = "http://iam.soffid.com/wsdl",
		endpointInterface = "com.soffid.iam.addons.webservice.UserDomainsWS"
)
public class UserDomains extends AbstractService implements UserDomainsWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UserDomainsWS#findUserDomains()
	 */
	public Collection<UserDomain> findUserDomains()
			throws UnexpectedException {
		try {
			return getUserDomainService().findAllUserDomain();
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UserDomainsWS#create(com.soffid.iam.api.UserDomain)
	 */
	public UserDomain create (UserDomain userDomain) throws UnexpectedException
	{
		try {
			return getUserDomainService().create(userDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UserDomainsWS#remove(com.soffid.iam.api.UserDomain)
	 */
	public void remove (UserDomain userDomain) throws UnexpectedException
	{
		try {
			getUserDomainService().delete(userDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.UserDomainsWS#update(com.soffid.iam.api.UserDomain)
	 */
	public UserDomain update (UserDomain userDomain) throws UnexpectedException
	{
		try {
			getUserDomainService().update(userDomain);
			return userDomain;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
