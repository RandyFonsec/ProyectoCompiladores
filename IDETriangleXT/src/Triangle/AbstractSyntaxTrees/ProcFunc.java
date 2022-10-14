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
public class ProcFunc extends Declaration{
    public Declaration D; 

    public ProcFunc(Declaration d, SourcePosition thePosition) {
        super(thePosition);
        D = d;
    }


    @Override
    public Object visit(Visitor v, Object o) {
        return v.visitProcFunc(this, v);
    }
}
