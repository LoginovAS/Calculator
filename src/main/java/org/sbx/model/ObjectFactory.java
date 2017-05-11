package org.sbx.model;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public SimpleCalculator createSimpleCalculator() {
        return new SimpleCalculator();
    }

    public SimpleCalculator.ExpressionResults createSimpleCalculatorExpressionResults() {
        return new SimpleCalculator.ExpressionResults();
    }

    public SimpleCalculator.Expressions createSimpleCalculatorExpressions() {
        return new SimpleCalculator.Expressions();
    }

    public Term createTerm() {
        return new Term();
    }

    public SimpleCalculator.ExpressionResults.ExpressionResult createSimpleCalculatorExpressionResultsExpressionResult() {
        return new SimpleCalculator.ExpressionResults.ExpressionResult();
    }

    public SimpleCalculator.Expressions.Expression createSimpleCalculatorExpressionsExpression() {
        return new SimpleCalculator.Expressions.Expression();
    }

}
