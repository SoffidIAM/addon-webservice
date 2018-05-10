package com.soffid.iam.addons.webservice;

import java.util.ArrayList;
import java.util.Collection;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;

import es.caib.seycon.ng.exception.InternalErrorException;

public class AttributeTranslation extends AbstractService {
	
	/**
	 * Retrieves the list of attributes
	 * 
	 * @return List of attributes
	 * 
	 * @throws UnexpectedException
	 */
	public Collection<String> getAttributes() throws UnexpectedException {
		try {
			return getAttributeTranslationServiceService().findDomains();
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * Retrieves the list of attributes
	 * 
	 * @param filterName Search text to match with the attribute names
	 * 
	 * @return List of attributes
	 * 
	 * @throws UnexpectedException
	 */
	public Collection<String> findAttributes(String filterName) throws UnexpectedException {
		try {
			Collection<String> domainsDB = getAttributeTranslationServiceService().findDomains();
			Collection<String> domainsWS = new ArrayList<String>();
			for (String name: domainsDB) {
				if (name.toLowerCase().contains(filterName.trim().toLowerCase())) {
					domainsWS.add(name);
				}
			}
			return domainsWS; 
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * Retrieves the values of an attribute
	 * 
	 * @param attribute Name of the attribute
	 * 
	 * @return List of values of an attribute
	 * 
	 * @throws UnexpectedException
	 */
	public Collection<com.soffid.iam.api.AttributeTranslation> findTranslations(String attribute) throws UnexpectedException {
		try {
			if (attribute.trim().isEmpty()) {
				attribute = null;
			}
			return getAttributeTranslationServiceService().findByExample(attribute, null, null);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
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
	public Collection<com.soffid.iam.api.AttributeTranslation> findTranslationsByColumn1(String attribute, String column1) throws UnexpectedException {
		try {
			if (attribute.trim().isEmpty()) {
				attribute = null;
			}
			if (column1.trim().isEmpty()) {
				column1 = null;
			}
			return getAttributeTranslationServiceService().findByExample(attribute, column1, null);
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
}