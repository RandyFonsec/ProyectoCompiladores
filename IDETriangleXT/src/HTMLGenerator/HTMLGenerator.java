/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTMLGenerator;

import Triangle.AbstractSyntaxTrees.AnyTypeDenoter;
import Triangle.AbstractSyntaxTrees.ArrayExpression;
import Triangle.AbstractSyntaxTrees.ArrayTypeDenoter;
import Triangle.AbstractSyntaxTrees.AssignCommand;
import Triangle.AbstractSyntaxTrees.BinaryExpression;
import Triangle.AbstractSyntaxTrees.BinaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.BoolTypeDenoter;
import Triangle.AbstractSyntaxTrees.CallCommand;
import Triangle.AbstractSyntaxTrees.CallExpression;
import Triangle.AbstractSyntaxTrees.CharTypeDenoter;
import Triangle.AbstractSyntaxTrees.CharacterExpression;
import Triangle.AbstractSyntaxTrees.CharacterLiteral;
import Triangle.AbstractSyntaxTrees.ConstActualParameter;
import Triangle.AbstractSyntaxTrees.ConstDeclaration;
import Triangle.AbstractSyntaxTrees.ConstFormalParameter;
import Triangle.AbstractSyntaxTrees.DoBody;
import Triangle.AbstractSyntaxTrees.DotVname;
import Triangle.AbstractSyntaxTrees.EmptyActualParameterSequence;
import Triangle.AbstractSyntaxTrees.EmptyCommand;
import Triangle.AbstractSyntaxTrees.EmptyExpression;
import Triangle.AbstractSyntaxTrees.EmptyFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.ErrorTypeDenoter;
import Triangle.AbstractSyntaxTrees.ForBody;
import Triangle.AbstractSyntaxTrees.FuncActualParameter;
import Triangle.AbstractSyntaxTrees.FuncDeclaration;
import Triangle.AbstractSyntaxTrees.FuncFormalParameter;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.IfCommand;
import Triangle.AbstractSyntaxTrees.IfExpression;
import Triangle.AbstractSyntaxTrees.IntTypeDenoter;
import Triangle.AbstractSyntaxTrees.IntegerExpression;
import Triangle.AbstractSyntaxTrees.IntegerLiteral;
import Triangle.AbstractSyntaxTrees.LetCommand;
import Triangle.AbstractSyntaxTrees.LetExpression;
import Triangle.AbstractSyntaxTrees.LocalDeclaration;
import Triangle.AbstractSyntaxTrees.LoopCommand;
import Triangle.AbstractSyntaxTrees.MultipleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleArrayAggregate;
import Triangle.AbstractSyntaxTrees.MultipleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.MultipleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleRecordAggregate;
import Triangle.AbstractSyntaxTrees.Operator;
import Triangle.AbstractSyntaxTrees.OrBody;
import Triangle.AbstractSyntaxTrees.ProcActualParameter;
import Triangle.AbstractSyntaxTrees.ProcDeclaration;
import Triangle.AbstractSyntaxTrees.ProcFormalParameter;
import Triangle.AbstractSyntaxTrees.ProcFunc;
import Triangle.AbstractSyntaxTrees.ProcFuncs;
import Triangle.AbstractSyntaxTrees.Program;
import Triangle.AbstractSyntaxTrees.RecDeclaration;
import Triangle.AbstractSyntaxTrees.RecordExpression;
import Triangle.AbstractSyntaxTrees.RecordTypeDenoter;
import Triangle.AbstractSyntaxTrees.SequentialCommand;
import Triangle.AbstractSyntaxTrees.SequentialDeclaration;
import Triangle.AbstractSyntaxTrees.SimpleTypeDenoter;
import Triangle.AbstractSyntaxTrees.SimpleVname;
import Triangle.AbstractSyntaxTrees.SingleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleArrayAggregate;
import Triangle.AbstractSyntaxTrees.SingleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.SingleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleRecordAggregate;
import Triangle.AbstractSyntaxTrees.SubscriptVname;
import Triangle.AbstractSyntaxTrees.TypeDeclaration;
import Triangle.AbstractSyntaxTrees.UnaryExpression;
import Triangle.AbstractSyntaxTrees.UnaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.UntilBody;
import Triangle.AbstractSyntaxTrees.VarActualParameter;
import Triangle.AbstractSyntaxTrees.VarDeclaration;
import Triangle.AbstractSyntaxTrees.VarFormalParameter;
import Triangle.AbstractSyntaxTrees.VarInit;
import Triangle.AbstractSyntaxTrees.Visitor;
import Triangle.AbstractSyntaxTrees.VnameExpression;
import Triangle.AbstractSyntaxTrees.WhileBody;
import Triangle.AbstractSyntaxTrees.WhileCommand;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Randy
 */
public class HTMLGenerator implements Visitor {

    private FileWriter fileWriter;
    private String content;
    private File html;
    private final String SPACE = "&nbsp;";
    private final String TAB = "&emsp;";
    private final String EOL = "<br>";
    private final String RESERVADA = "<span class = \"reservada\" >";
    private final String IDENTIFICADOR = "<span class = \"identificador\" >";
    private final String LITERAL = "<span class = \"literal\" >";
    private final String COMENTARIO = "<span class = \"comentario\" >";
    private final String ENDTAG = "</span>";
    private String fileName;

    public HTMLGenerator(String fileName, Program root) {
        this.fileName = fileName;
        content = "";
        crearArchivo(fileName);
        agregarEncabezado();
        root.visit(this, null);
        agregarCierre();

    }

    private String getLiteral(String spelling) {
        String sentence = LITERAL + spelling + " " + ENDTAG;
        return sentence;
    }

    private String getIdentificador(String spelling) {
        String sentence = IDENTIFICADOR + spelling + " " + ENDTAG;
        return sentence;
    }

    private String getReservada(String spelling) {
        String sentence = RESERVADA + spelling + " " + ENDTAG;
        return sentence;
    }

    private String getTabs(int tabs) {
        String result = "";
        for (int i = 0; i < tabs; i++) {
            result += TAB;
        }
        return result;
    }

    public void crearArchivo(String fileName) {
        html = new File(fileName + ".html");
        //Creacion archivo
        try {

            if (!html.createNewFile()) {
                html.delete();
                html = new File(fileName + ".html");
            }
        } catch (IOException ex) {
            Logger.getLogger(HTMLGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Writer
        try {
            fileWriter = new FileWriter(this.fileName + ".html");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void agregarEncabezado() {
        String encabezado = "<!doctype html>\n"
                + "<html>\n"
                +"<style> span {font-family:'Courier New', monospace; font-size: 1em;}\n"
                + ".reservada{font-weight: bold;}\n"
                + ".literal{color: darkblue;}\n"
                + ".identificador{color: black;}\n"
                + "</style>"
                
                + "<head>\n"
                + "<title>" + this.fileName + "</title>\n"
                + "</head>\n"
                + "<body>";
        try {
            fileWriter.write(encabezado);
        } catch (IOException ex) {
            Logger.getLogger(HTMLGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void agregarCierre() {
        String footer = " </body>";
        try {
            fileWriter.write(content);
            fileWriter.write(footer);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(HTMLGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Object visitAssignCommand(AssignCommand ast, Object o) {

        String sentence = "";
        String tabs = getTabs((Integer)o);
        sentence += tabs;
        sentence += ast.V.visit(this, 0);

        sentence += (getIdentificador(":= "));

        sentence += ast.E.visit(this, 0);

        return sentence;

    }

    @Override
    public Object visitCallCommand(CallCommand ast, Object o) {
        String sentence = "";
        String tabs = getTabs((Integer)o);
        sentence += tabs;
        sentence += ast.I.visit(this, 0);
        sentence += "( ";
        sentence += ast.APS.visit(this, 0);
        sentence += ") ";

        return sentence;
    }

    @Override
    public Object visitEmptyCommand(EmptyCommand ast, Object o) {

        String sentence = RESERVADA + "nil " + ENDTAG;
        String tabs = getTabs((Integer)o);
        sentence += tabs;
        sentence += sentence;
        return sentence;
    }

    @Override
    public Object visitIfCommand(IfCommand ast, Object o) {
        String sentence = "";
        String tabs = getTabs((Integer)o);
        sentence += tabs;
        sentence += (getReservada("if "));
        sentence += ast.E.visit(this, 0);
        sentence += tabs;
        sentence += (getReservada(" then ") + EOL);
        sentence += ast.C1.visit(this, (Integer)(o)+1);

        if (ast.orbody != null) {
            sentence += ast.orbody.visit(this, (Integer)(o)+1);
        }

        sentence += tabs;
        sentence += (getReservada(" else") + EOL);
        sentence += ast.C2.visit(this, (Integer)(o)+1);

        sentence += (getReservada(" end ") + EOL);

        return sentence;
    }

    @Override
    public Object visitLetCommand(LetCommand ast, Object o) {

        String sentence = "";
        String tabs = getTabs((Integer)o);
        sentence += tabs;
        sentence += getReservada("let ") + EOL;
        
        sentence += ast.D.visit(this, (Integer)o+1);
        sentence += tabs;
        sentence += getReservada("in ") + EOL;
        sentence += ast.C.visit(this, (Integer)o+1);
        sentence += getReservada("end ") + EOL;

        return sentence;
    }

    @Override
    public Object visitSequentialCommand(SequentialCommand ast, Object o) {
        String sentence = "";
        sentence += ast.C1.visit(this, (Integer)o+1);
        sentence += "; " + EOL;
        sentence += ast.C2.visit(this, (Integer)o+1);
        return sentence;
    }

    @Override
    public Object visitWhileCommand(WhileCommand ast, Object o) {
        String sentence = "";
        sentence += ast.E.visit(this, 0);
        sentence += ast.C.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitArrayExpression(ArrayExpression ast, Object o) {

        return ast.AA.visit(this, 0);
    }

    @Override
    public Object visitBinaryExpression(BinaryExpression ast, Object o) {
        String sentence = "";
        sentence += ast.E1.visit(this, 0);
        sentence += ast.O.visit(this, 0) + " ";
        sentence += ast.E2.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitCallExpression(CallExpression ast, Object o) {
        String sentence = "";
        sentence += ast.I.visit(this, 0);
        sentence += "(" + ast.APS.visit(this, 0) + ")";
        return sentence;

    }

    @Override
    public Object visitCharacterExpression(CharacterExpression ast, Object o) {

        return ast.CL.visit(this, 0);
    }

    @Override
    public Object visitEmptyExpression(EmptyExpression ast, Object o) {

        return " ";
    }

    @Override
    public Object visitIfExpression(IfExpression ast, Object o) {
        String sentence = "";
        String tabs = getTabs((Integer)o);
        sentence += tabs;
        sentence += getReservada("if ");
        sentence += ast.E1.visit(this, (Integer)o+1);
        sentence += tabs;
        sentence += getReservada("then ");
        sentence += ast.E2.visit(this, (Integer)o+1);
        sentence += tabs;
        sentence += getReservada("else ") ;
        sentence += ast.E3.visit(this, (Integer)o+1) + EOL;
        return sentence;
    }

    @Override
    public Object visitIntegerExpression(IntegerExpression ast, Object o) {

        return ast.IL.visit(this, 0);
    }

    @Override
    public Object visitLetExpression(LetExpression ast, Object o) {
        String tabs = getTabs((Integer)o);
        
        String sentence = "";
        sentence += tabs;
        sentence += getReservada("let ") + EOL;
        sentence += ast.D.visit(this, (Integer)o+1);
        sentence += getReservada("in ") + EOL;
        sentence += ast.E.visit(this, (Integer)o+1) + EOL;

        return sentence;
    }

    @Override
    public Object visitRecordExpression(RecordExpression ast, Object o) {

        return ast.RA.visit(this, 0);
    }

    @Override
    public Object visitUnaryExpression(UnaryExpression ast, Object o) {
        String sentence = "";
        sentence += ast.O.visit(this, 0);
        sentence += ast.E.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitVnameExpression(VnameExpression ast, Object o) {

        return ast.V.visit(this, 0);
    }

    @Override
    public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object o) {
        String sentence = "";
        String tabs = getTabs((Integer)o);
        sentence += tabs;
        sentence += ast.ARG1.visit(this, 0);
        sentence += ast.O.visit(this, 0);
        sentence += ast.ARG2.visit(this, 0);
        sentence += getReservada("of ");
        sentence += ast.RES.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitConstDeclaration(ConstDeclaration ast, Object o) {
        String tabs = getTabs((Integer)o);
        String sentence = ""; 
        sentence += tabs;
        sentence += getReservada("const ");
                
        sentence += ast.I.visit(this, 0);
        sentence += getReservada(": ");
        sentence += ast.E.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitFuncDeclaration(FuncDeclaration ast, Object o) {
        String sentence = "";
        if(o != null){
            String tabs = getTabs((Integer)o);
        
            sentence += tabs;
        }
        sentence += getReservada("func ");
        sentence += ast.I.visit(this, (Integer)o+1);
        sentence += ast.FPS.visit(this, 0);
        sentence += getReservada(": ");
        sentence += ast.T.visit(this, 0);
        sentence += getReservada("~ ");
        sentence += ast.E.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitProcDeclaration(ProcDeclaration ast, Object o) {
        String sentence = "";
        if(o != null){
            String tabs = getTabs((Integer)o);
        
            sentence += tabs;
        }
        
        sentence += getReservada("proc ");
        sentence += ast.I.visit(this, (Integer)o);
        sentence += ast.FPS.visit(this, 0);
        sentence += "~ ";
        sentence += ast.C.visit(this, 0);
        sentence += getReservada("end ");
        return sentence;
    }

    @Override
    public Object visitSequentialDeclaration(SequentialDeclaration ast, Object o) {
        String sentence = "";
        sentence += ast.D1.visit(this, (Integer)o+1);
        sentence += "; " + EOL;
        sentence += ast.D2.visit(this, (Integer)o+1);
        return sentence;
    }

    @Override
    public Object visitTypeDeclaration(TypeDeclaration ast, Object o) {
        String sentence = getReservada("type ");
        String tabs = getTabs((Integer)o);
        
        sentence += tabs;
        sentence += ast.I.visit(this, (Integer)o+1);
        sentence += getReservada("~ ");
        sentence += ast.T.visit(this, 0) + EOL;
        return sentence;
    }

    @Override
    public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object o) {
        String sentence = "";
        String tabs = getTabs((Integer)o);
        
        sentence += tabs;
        sentence += ast.ARG.visit(this, 0);
        sentence += ast.O.visit(this, 0);
        sentence += ast.RES.visit(this, 0) + EOL;
        return sentence;
    }

    @Override
    public Object visitVarDeclaration(VarDeclaration ast, Object o) {
        String sentence = "";
        String tabs = getTabs((Integer)o);
        
        sentence += tabs;
        sentence += getReservada("var ");
        sentence += ast.I.visit(this, 0);
        sentence += getReservada(": ");
        sentence += ast.T.visit(this, 0) + EOL;
        return sentence;
    }

    @Override
    public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object o) {
        String sentence = "";
        sentence += "[ ";
        sentence += ast.E.visit(this, 0);
        sentence += ", ";
        sentence += ast.AA.visit(this, 0);
        sentence += "] ";
        return sentence;
    }

    @Override
    public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object o) {

        return ast.E.visit(this, 0);
    }

    @Override
    public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object o) {
        String sentence = "";
        sentence += ast.I.visit(this, 0);
        sentence += ast.E.visit(this, 0);
        sentence += ", ";
        sentence += ast.RA.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object o) {
        String sentence = "";
        sentence += ast.I.visit(this, 0);
        sentence += "~ ";
        sentence += ast.E.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitConstFormalParameter(ConstFormalParameter ast, Object o) {
        String sentence = getReservada("const ");
        sentence += ast.I.visit(this, 0);
        sentence += getReservada(" ~ ");
        sentence += ast.T.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitFuncFormalParameter(FuncFormalParameter ast, Object o) {
        String sentence = getReservada("func ");
        sentence += ast.I.visit(this, 0);
        sentence += "( ";
        sentence += ast.FPS.visit(this, 0);
        sentence += ") ";
        sentence += "~ ";

        sentence += ast.T.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitProcFormalParameter(ProcFormalParameter ast, Object o) {
        String sentence = "";
        sentence += ast.I.visit(this, 0);
        sentence += "( ";
        sentence += ast.FPS.visit(this, 0);
        sentence += ") ";
        return sentence;
    }

    @Override
    public Object visitVarFormalParameter(VarFormalParameter ast, Object o) {
        String sentence = "";
        sentence += getReservada("var ");
        sentence += ast.I.visit(this, 0);
        sentence += getReservada("of ");
        sentence += ast.T.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object o) {
        return " ";
    }

    @Override
    public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object o) {
        String sentence = "";
        sentence += ast.FP.visit(this, 0);
        sentence += ", ";
        sentence += ast.FPS.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object o) {

        return ast.FP.visit(this, 0);
    }

    @Override
    public Object visitConstActualParameter(ConstActualParameter ast, Object o) {

        return ast.E.visit(this, 0);
    }

    @Override
    public Object visitFuncActualParameter(FuncActualParameter ast, Object o) {

        return ast.I.visit(this, 0);
    }

    @Override
    public Object visitProcActualParameter(ProcActualParameter ast, Object o) {

        return ast.I.visit(this, 0);
    }

    @Override
    public Object visitVarActualParameter(VarActualParameter ast, Object o) {

        return ast.V.visit(this, 0);
    }

    @Override
    public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object o) {
        return " ";
    }

    @Override
    public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object o) {
        String sentence = "";
        sentence += ast.AP.visit(this, 0);
        sentence += ", ";
        sentence += ast.APS.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object o) {

        return ast.AP.visit(this, 0);
    }

    @Override
    public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object o) {
        return " any ";
    }

    @Override
    public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object o) {
        String sentence = "";
        String tabs = getTabs((Integer)o);
        
        sentence += tabs;
        sentence += getReservada("array ");
        sentence += ast.IL.visit(this, 0);
        sentence += getReservada("of ");
        sentence += ast.T.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object o) {
        return " bool ";
    }

    @Override
    public Object visitCharTypeDenoter(CharTypeDenoter ast, Object o) {
        return " char ";
    }

    @Override
    public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object o) {
        return " error ";
    }

    @Override
    public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object o) {

        return ast.I.visit(this, 0);
    }

    @Override
    public Object visitIntTypeDenoter(IntTypeDenoter ast, Object o) {
        return " int ";
    }

    @Override
    public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object o) {

        return ast.FT.visit(this, 0);
    }

    @Override
    public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object o) {
        String sentence = "";
        sentence += ast.I.visit(this, 0);
        sentence += ast.T.visit(this, 0);
        sentence += ast.FT.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object o) {
        String sentence = "";
        sentence += ast.I.visit(this, 0);
        sentence += getReservada("of ");
        sentence += ast.T.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitCharacterLiteral(CharacterLiteral ast, Object o) {
        String sentence = getLiteral(ast.spelling) + " ";
        return sentence;
    }

    @Override
    public Object visitIdentifier(Identifier ast, Object o) {
        String sentence = "";
        if(o != null){
            String tabs = getTabs((Integer)o);
            sentence += tabs;
        }
        
        
        sentence = getIdentificador(ast.spelling) + " ";
        return sentence;
    }

    @Override
    public Object visitIntegerLiteral(IntegerLiteral ast, Object o) {
        
        String sentence = "";
        if(o != null){
            String tabs = getTabs((Integer)o);
            sentence += tabs;
        }
        sentence += getLiteral(ast.spelling) + " ";
        return sentence;
    }

    @Override
    public Object visitOperator(Operator ast, Object o) {
        String sentence = getIdentificador(ast.spelling);
        if (sentence.compareTo("<") == 0) {
            sentence = "&lt;";
        } else if (sentence.compareTo("<=") == 0) {
            sentence = "&lt;";
        } else {
            sentence += " ";
        }

        return sentence;
    }

    @Override
    public Object visitDotVname(DotVname ast, Object o) {
        String sentence = "";
        if(o != null){
            String tabs = getTabs((Integer)o);
            sentence += tabs;
        }
        sentence += ast.I.visit(this, (Integer)o+1);
        sentence += ".";
        sentence += ast.V.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitSimpleVname(SimpleVname ast, Object o) {

        return ast.I.visit(this, 0);
    }

    @Override
    public Object visitSubscriptVname(SubscriptVname ast, Object o) {
        String sentence = "";
        if(o != null){
            String tabs = getTabs((Integer)o);
            sentence += tabs;
        }
        sentence += ast.V.visit(this, 0);
        sentence += "[";
        sentence += ast.E.visit(this, 0);
        sentence += "]";
        return sentence;
    }

    @Override
    public Object visitProgram(Program ast, Object o) {
        return content += ast.C.visit(this, 1);
    }

    @Override
    public Object visitOrBody(OrBody aThis, Object o) {
        String sentence = "";
        sentence += EOL + " | ";
        sentence += aThis.command.visit(this, 0);
        sentence += getReservada("then ");
        sentence += aThis.expression.visit(this, 0);
        if (aThis.orBody != null) {
            sentence += aThis.orBody.visit(this, 0);
        }

        return sentence;
    }

    @Override
    public Object visitLoopCommand(LoopCommand aThis, Object o) {
        String sentence = getReservada("loop ");
        sentence += aThis.lb.visit(this, o);
        return sentence;
    }

    @Override
    public Object visitWhileBody(WhileBody aThis, Object object) {
        String sentence = getReservada("while ");
        sentence += aThis.E.visit(this, object);
        sentence += getReservada("do ");
        sentence += aThis.C.visit(this, object);
        sentence += getReservada("end ");
        return sentence;
    }

    @Override
    public Object visitUntilBody(UntilBody aThis, Object object) {
        String sentence = getReservada(" until ");
        sentence += aThis.E.visit(this, 0);
        sentence += getReservada(" do ");
        sentence += aThis.C.visit(this, 0);
        sentence += getReservada(" end ");
        return sentence;
    }

    @Override
    public Object visitDoBody(DoBody aThis, Object o) {
        String sentence = getReservada(" until ");
        sentence += aThis.C.visit(this, 0);
        sentence += getReservada("do ");
        sentence += aThis.E.visit(this, 0);
        sentence += getReservada("end ");
        return sentence;
    }

    @Override
    public Object visitForBody(ForBody aThis, Object o) {
        String sentence = getReservada(" until ");
        sentence += aThis.I.visit(this, 0);
        sentence += getReservada("do ");
        sentence += aThis.E1.visit(this, 0);

        sentence += getReservada("to ");
        sentence += aThis.E2.visit(this, 0);
        if(aThis.E3 != null)
            sentence += aThis.E3.visit(this, 0);

        sentence += EOL + getReservada("do ") + EOL;
        sentence += aThis.C.visit(this, 0);

        sentence += getReservada("end ") + EOL;

        return sentence;
    }

    @Override
    public Object visitLocalDeclaration(LocalDeclaration aThis, Object o) {
        String sentence = getReservada(" local ");
        sentence += aThis.D1.visit(this, 0);
        sentence += getReservada(" in ");
        sentence += aThis.D2.visit(this, 0) + EOL;
        return sentence;
    }

    @Override
    public Object visitRecDeclaration(RecDeclaration aThis, Object o) {
        String sentence = getReservada("rec ");
        sentence += aThis.proc.visit(this, 0);
        sentence += getReservada("in ");
        if(aThis.procBody!= null)
            sentence += aThis.procBody.visit(this, 0) + EOL;
        return sentence;
    }

    @Override
    public Object visitProcFuncs(ProcFuncs aThis, Object o) {

        return aThis.procFunc1.visit(this, 0);
    }

    @Override
    public Object visitVarInit(VarInit aThis, Object o) {
        String sentence = getReservada("var ");
        sentence += aThis.ident.visit(this, 0);
        sentence += getReservada("init");
        sentence += aThis.expr.visit(this, 0);
        return sentence;
    }

    @Override
    public Object visitProcFunc(ProcFunc aThis, Visitor v) {

        return aThis.D.visit(this, 0);
    }

}
