package org.sbx.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "expressions",
    "expressionResults"
})
@XmlRootElement(name = "simpleCalculator")
public class SimpleCalculator {

    protected SimpleCalculator.Expressions expressions;
    protected SimpleCalculator.ExpressionResults expressionResults;

    public SimpleCalculator.Expressions getExpressions() {
        return expressions;
    }

    public void setExpressions(SimpleCalculator.Expressions value) {
        this.expressions = value;
    }

    public SimpleCalculator.ExpressionResults getExpressionResults() {
        return expressionResults;
    }

    public void setExpressionResults(SimpleCalculator.ExpressionResults value) {
        this.expressionResults = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "expressionResult"
    })
    public static class ExpressionResults {

        @XmlElement(required = true)
        protected List<SimpleCalculator.ExpressionResults.ExpressionResult> expressionResult;

        public List<SimpleCalculator.ExpressionResults.ExpressionResult> getExpressionResult() {
            if (expressionResult == null) {
                expressionResult = new ArrayList<SimpleCalculator.ExpressionResults.ExpressionResult>();
            }
            return this.expressionResult;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "result"
        })
        public static class ExpressionResult {

            protected double result;

            public double getResult() {
                return result;
            }

            public void setResult(double value) {
                this.result = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="expression" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="operation" type="{}term"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "expression"
    })
    public static class Expressions {

        @XmlElement(required = true)
        protected List<SimpleCalculator.Expressions.Expression> expression;

        /**
         * Gets the value of the expression property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the expression property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getExpression().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SimpleCalculator.Expressions.Expression }
         * 
         * 
         */
        public List<SimpleCalculator.Expressions.Expression> getExpression() {
            if (expression == null) {
                expression = new ArrayList<SimpleCalculator.Expressions.Expression>();
            }
            return this.expression;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="operation" type="{}term"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "operation"
        })
        public static class Expression {

            @XmlElement(required = true)
            protected Term operation;

            /**
             * Gets the value of the operation property.
             * 
             * @return
             *     possible object is
             *     {@link Term }
             *     
             */
            public Term getOperation() {
                return operation;
            }

            /**
             * Sets the value of the operation property.
             * 
             * @param value
             *     allowed object is
             *     {@link Term }
             *     
             */
            public void setOperation(Term value) {
                this.operation = value;
            }

        }

    }

}
