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
public class LoopCommand extends Command{
    public LoopBody lb;
    public LoopCommand(LoopBody loopBody, SourcePosition thePosition) {
        super(thePosition);
        lb = loopBody;
    }

    @Override
    public Object visit(Visitor v, Object o) {
        return v.visitLoopCommand(this, o);
    }

    
    
}
