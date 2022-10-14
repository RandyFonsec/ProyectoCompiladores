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
public class OrBody extends AST{

    @Override
    public Object visit(Visitor v, Object o) {
        return v.visitOrBody(this, o);
    }
    
    public OrBody (OrBody orBodyAST, Expression expr, Command comm, SourcePosition thePosition) {
        super (thePosition);
        orBody = orBodyAST;
        expression = expr;
        command = comm;
    
    }

  public OrBody orBody;
  public Expression expression;
  public Command command;
  
    
}
