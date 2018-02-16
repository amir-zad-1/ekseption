import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import sun.reflect.generics.tree.VoidDescriptor;

import java.beans.MethodDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.print(":) works");
        System.out.println(System.getProperty("user.dir"));
        FileInputStream in = null;

        try
        {
            in = new FileInputStream("src/main/java/Main.java");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Do");
        }

        // parse it
        CompilationUnit cu = JavaParser.parse(in);

        // visit and print the methods names
        cu.accept(new MethodVisitor(), null);

    }

    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this
             CompilationUnit, including inner class methods */
            System.out.println("throw > " + n.getThrownExceptions());
            super.visit(n, arg);
        }

    }


}
