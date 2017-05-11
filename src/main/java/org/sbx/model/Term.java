package org.sbx.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "term", propOrder = {
    "arg",
    "arg1",
    "operation1",
    "operation2",
    "arg2",
    "operation"
})
public class Term {

    @XmlSchemaType(name = "nonNegativeInteger")
    protected List<BigInteger> arg;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger arg1;
    protected Term operation1;
    protected Term operation2;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger arg2;
    protected List<Term> operation;
    @XmlAttribute(name = "OperationType")
    protected String operationType;

    public List<BigInteger> getArg() {
        if (arg == null) {
            arg = new ArrayList<BigInteger>();
        }
        return this.arg;
    }

    public BigInteger getArg1() {
        return arg1;
    }

    public void setArg1(BigInteger value) {
        this.arg1 = value;
    }

    public Term getOperation1() {
        return operation1;
    }

    public void setOperation1(Term value) {
        this.operation1 = value;
    }

    public Term getOperation2() {
        return operation2;
    }

    public void setOperation2(Term value) {
        this.operation2 = value;
    }

    public BigInteger getArg2() {
        return arg2;
    }

    public void setArg2(BigInteger value) {
        this.arg2 = value;
    }

    public List<Term> getOperation() {
        if (operation == null) {
            operation = new ArrayList<Term>();
        }
        return this.operation;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String value) {
        this.operationType = value;
    }

}
