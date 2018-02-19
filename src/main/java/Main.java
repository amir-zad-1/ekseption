import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.PrettyPrinterConfiguration;
import utils.HtmlHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        System.out.print(":) works");
        System.out.println(System.getProperty("user.dir"));
        FileInputStream in = null;

        try
        {
            in = new FileInputStream("src/main/java/Main.java");
        }
        catch (FileNotFoundException e)
        {

        }

        // parse it
        CompilationUnit cu = JavaParser.parse(in);

        // visit and print the methods names
        HtmlHelper html = new HtmlHelper();

        cu.accept(new TryVisitor(), html);
//        html.addAnomaly();
//        html.addAnomaly();
//        html.addSection("file2");
//        html.addAnomaly();
//        html.addAnomaly();
        html.generateMarkup("Report");

    }

    private static class TryVisitor extends VoidVisitorAdapter<HtmlHelper> {
        @Override
        public void visit(TryStmt n, HtmlHelper arg) {

            for (CatchClause c : n.getCatchClauses())
            {
                CompilationUnit cu = ((CompilationUnit)n.findRootNode());
                String className = cu.getType(0).asTypeDeclaration().getName().asString() + ".java";
                NodeList<Statement> statements = c.getBody().getStatements();
                int line = c.getRange().get().begin.line;
                int statement_len = statements.size();
                //System.out.println(">>" + line + ", " + className);


                arg.addSection(className);
            }


            super.visit(n, arg);
        }

    }


}
