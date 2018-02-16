import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

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
        { }

        // parse it
        CompilationUnit cu = JavaParser.parse(in);

        // visit and print the methods names
        cu.accept(new TryVisitor(), null);

    }

    private static class TryVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(TryStmt n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this
             CompilationUnit, including inner class methods */

            for (CatchClause c : n.getCatchClauses())
            {
                NodeList<Statement> statements = c.getBody().getStatements();
                System.out.println(statements.size());
            }


            super.visit(n, arg);
        }

    }


}
