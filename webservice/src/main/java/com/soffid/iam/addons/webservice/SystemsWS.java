package com.soffid.iam.addons.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.soffid.iam.addons.webservice.exception.UnexpectedException;
import com.soffid.iam.api.System;

@WebService(targetNamespace = "http://iam.soffid.com/wsdl")
public interface SystemsWS {

	Collection<System> findDispatchers(String name, String className, String url, Boolean roleBased, Boolean trusted,
			Boolean active) throws UnexpectedException;

}
