package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.PasswordPolicy;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
		portName = "PasswordPolicies",
		serviceName = "services/PasswordPolicies",
		targetNamespace = "http://iam.soffid.com/wsdl",
		endpointInterface = "com.soffid.iam.addons.webservice.PasswordPoliciesWS"
)
public class PasswordPolicies extends AbstractService implements PasswordPoliciesWS {

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordPoliciesWS#findPasswordPolicy(java.lang.String, java.lang.String)
	 */
	public PasswordPolicy findPasswordPolicy(String passwordPolicy, String passwordDomain)
			throws UnexpectedException {
		try {
			return getUserDomainService().findPolicyByTypeAndPasswordDomain(passwordPolicy, passwordDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordPoliciesWS#findPasswordPolicies(java.lang.String)
	 */
	public Collection<PasswordPolicy> findPasswordPolicies(String passwordDomain)
			throws UnexpectedException {
		try {
			return getUserDomainService().findAllPasswordPolicyDomain(passwordDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordPoliciesWS#create(com.soffid.iam.api.PasswordPolicy)
	 */
	public PasswordPolicy create (PasswordPolicy passwordPolicy) throws UnexpectedException
	{
		try {
			return getUserDomainService().create(passwordPolicy);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordPoliciesWS#remove(com.soffid.iam.api.PasswordPolicy)
	 */
	public void remove (PasswordPolicy passwordPolicy) throws UnexpectedException
	{
		try {
			getUserDomainService().delete(passwordPolicy);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.PasswordPoliciesWS#update(com.soffid.iam.api.PasswordPolicy)
	 */
	public PasswordPolicy update (PasswordPolicy passwordPolicy) throws UnexpectedException
	{
		try {
			getUserDomainService().update(passwordPolicy);
			return passwordPolicy;
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}
