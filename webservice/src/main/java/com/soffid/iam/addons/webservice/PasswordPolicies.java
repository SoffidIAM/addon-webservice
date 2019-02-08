package com.soffid.iam.addons.webservice;

import java.util.Collection;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.PasswordPolicy;

import es.caib.seycon.ng.exception.InternalErrorException;


public class PasswordPolicies extends AbstractService {
	
	
	/**
	 * Finds the password policy to apply on a password domain and user type
	 * 
	 * @param passwordPolicy the user type
	 * @param passwordDomain the  password domain
	 * @return the password policy, if any
	 * @throws UnexpectedException
	 */
	public PasswordPolicy findPasswordPolicy(String passwordPolicy, String passwordDomain)
			throws UnexpectedException {
		try {
			return getUserDomainService().findPolicyByTypeAndPasswordDomain(passwordPolicy, passwordDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * finds password policies defined for a password domain
	 * @param passwordDomain the password domain to look at
	 * @return the list of password policies
	 * @throws UnexpectedException
	 */
	public Collection<PasswordPolicy> findPasswordPolicies(String passwordDomain)
			throws UnexpectedException {
		try {
			return getUserDomainService().findAllPasswordPolicyDomain(passwordDomain);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	
	
	/**
	 * Creates a PasswordPolicy
	 * 
	 * @param passwordPolicy Password policy to create
	 * @return created Password policy
	 * @throws UnexpectedException
	 */
	public PasswordPolicy create (PasswordPolicy passwordPolicy) throws UnexpectedException
	{
		try {
			return getUserDomainService().create(passwordPolicy);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Removes a PasswordPolicy
	 * 
	 * @param passwordPolicy Password policy to remove
	 * @throws UnexpectedException
	 */
	public void remove (PasswordPolicy passwordPolicy) throws UnexpectedException
	{
		try {
			getUserDomainService().delete(passwordPolicy);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Update PasswordPolicy data
	 * 
	 * @param passwordPolicy the Password policy to update
	 * @return the updated Password policy
	 * @throws UnexpectedException
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

