package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface AttributeTranslationWS {

	/**
	 * Retrieves the list of attributes
	 * 
	 * @return List of attributes
	 * 
	 * @throws UnexpectedException
	 */
	Collection<String> getAttributes() throws UnexpectedException;

	/**
	 * Retrieves the list of attributes
	 * 
	 * @param filterName Search text to match with the attribute names
	 * 
	 * @return List of attributes
	 * 
	 * @throws UnexpectedException
	 */
	Collection<String> findAttributes(String filterName) throws UnexpectedException;

	/**
	 * Retrieves the values of an attribute
	 * 
	 * @param attribute Name of the attribute
	 * 
	 * @return List of values of an attribute
	 * 
	 * @throws UnexpectedException
	 */
	Collection<com.soffid.iam.api.AttributeTranslation> findTranslations(String attribute) throws UnexpectedException;

	/**
	 * Retrieves the values of an attribute an the value of its column1
	 * 
	 * @param attribute Name of the attribute
	 * @param column1 Value of the column1
	 * 
	 * @return List of values of an attribute
	 * 
	 * @throws UnexpectedException
	 */
	Collection<com.soffid.iam.api.AttributeTranslation> findTranslationsByColumn1(String attribute, String column1)
			throws UnexpectedException;

}