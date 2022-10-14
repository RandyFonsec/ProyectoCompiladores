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
public class UntilBody extends LoopBody{
    public Expression E;
    public Command C;

    public UntilBody(Expression exprAST, Command commandAST, SourcePosition thePosition) {
        super(thePosition);
        E = exprAST;
        C = commandAST;
    }

    @Override
    public Object visit(Visitor v, Object o) {
        return v.visitUntilBody(this, o);
    }
    
}
