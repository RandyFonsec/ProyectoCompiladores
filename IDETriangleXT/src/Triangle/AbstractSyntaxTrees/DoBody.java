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
public class DoBody extends LoopBody{
    public Command C;
    public Expression E;
    public DoBody(Command command, Expression expression, SourcePosition thePosition) {
        super(thePosition);
        C = command;
        E = expression;
    }

    @Override
    public Object visit(Visitor v, Object o) {
        return v.visitDoBody(this, o);
    }
    
}
