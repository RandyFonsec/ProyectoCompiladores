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
public class ForBody extends LoopBody{
    public Identifier I;
    public Expression E1, E2, E3;
    public Command C;
    public ForBody(Identifier iAST, Expression eAST1, Expression eAST2, Expression eAST3, Command command,SourcePosition thePosition) {
        super(thePosition);
        I = iAST;
        E1 = eAST1;
        E2 = eAST2;
        E3 = eAST3;
        C = command;
    }

    @Override
    public Object visit(Visitor v, Object o) {
        return v.visitForBody(this, o);
    }
    
}
