package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.PasswordPolicy;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface PasswordPoliciesWS {

	/**
	 * Finds the password policy to apply on a password domain and user type
	 * 
	 * @param passwordPolicy the user type
	 * @param passwordDomain the  password domain
	 * @return the password policy, if any
	 * @throws UnexpectedException
	 */
	PasswordPolicy findPasswordPolicy(String passwordPolicy, String passwordDomain) throws UnexpectedException;

	/**
	 * finds password policies defined for a password domain
	 * @param passwordDomain the password domain to look at
	 * @return the list of password policies
	 * @throws UnexpectedException
	 */
	Collection<PasswordPolicy> findPasswordPolicies(String passwordDomain) throws UnexpectedException;

	/**
	 * Creates a PasswordPolicy
	 * 
	 * @param passwordPolicy Password policy to create
	 * @return created Password policy
	 * @throws UnexpectedException
	 */
	PasswordPolicy create(PasswordPolicy passwordPolicy) throws UnexpectedException;

	/**
	 * Removes a PasswordPolicy
	 * 
	 * @param passwordPolicy Password policy to remove
	 * @throws UnexpectedException
	 */
	void remove(PasswordPolicy passwordPolicy) throws UnexpectedException;

	/**
	 * Update PasswordPolicy data
	 * 
	 * @param passwordPolicy the Password policy to update
	 * @return the updated Password policy
	 * @throws UnexpectedException
	 */
	PasswordPolicy update(PasswordPolicy passwordPolicy) throws UnexpectedException;
}
