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
public class ProcFuncs extends Declaration{
    public Declaration procFunc1;
    public Declaration declaration;
    
    public ProcFuncs(Declaration d, Declaration pf1, SourcePosition thePosition) {
        super(thePosition);
        declaration = d;
        procFunc1 = pf1;
        
    }



    @Override
    public Object visit(Visitor v, Object o) {
        return v.visitProcFuncs(this, o);
    }
    
}
