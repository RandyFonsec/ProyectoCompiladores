/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Randy
 */
public class WhileBody extends LoopBody{
    public Expression E;
    public Command C;
    public WhileBody(Expression expression, Command command,  SourcePosition thePosition) {
        super(thePosition);
        E = expression;
        C = command;
    }
    
    @Override
    public Object visit(Visitor v, Object o) {
        return v.visitWhileBody(this, o); //To change body of generated methods, choose Tools | Templates.
    }
}
