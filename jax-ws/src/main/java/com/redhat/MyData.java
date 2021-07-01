package com.redhat;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

@XmlRootElement(name = "MyData")
public class MyData implements Serializable {

	private static final long serialVersionUID = 3964130905070610518L;
	
	@XmlList
	@XmlElement(name = "bigIntegerList")
	@XmlSchemaType(name = "anySimpleType")
	protected List<BigInteger> bigIntegerList;


	public List<BigInteger> getBigIntegerList() {
	    if (bigIntegerList == null) {
	        bigIntegerList = Arrays.asList(BigInteger.ONE, BigInteger.TWO, BigInteger.ZERO, BigInteger.TEN);
	    }
	    
	    return this.bigIntegerList;
	}

}
