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
public class VarInit extends Declaration{
    public Identifier ident;
    public Expression expr;
    public VarInit(Identifier id, Expression ex, SourcePosition thePosition) {
        super(thePosition);
        ident = id;
        expr = ex;
    }

    @Override
    public Object visit(Visitor v, Object o) {
        return v.visitVarInit(this, o);
    }
    
}
