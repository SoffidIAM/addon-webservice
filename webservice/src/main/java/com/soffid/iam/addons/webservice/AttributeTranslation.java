package com.soffid.iam.addons.webservice;

import java.util.ArrayList;
import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebService(
        portName = "AttributeTranslation",
        serviceName = "services/AttributeTranslation",
        targetNamespace = "http://iam.soffid.com/wsdl",
        endpointInterface = "com.soffid.iam.addons.webservice.AttributeTranslationWS"
)
public class AttributeTranslation extends AbstractService implements AttributeTranslationWS {
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.AttributeTranslationWS#getAttributes()
	 */
	public Collection<String> getAttributes() throws UnexpectedException {
		try {
			return getAttributeTranslationServiceService().findDomains();
		} catch (InternalErrorException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.AttributeTranslationWS#findAttributes(java.lang.String)
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
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.AttributeTranslationWS#findTranslations(java.lang.String)
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
	
	/* (non-Javadoc)
	 * @see com.soffid.iam.addons.webservice.AttributeTranslationWS#findTranslationsByColumn1(java.lang.String, java.lang.String)
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
