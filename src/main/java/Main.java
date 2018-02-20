import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.PrettyPrinterConfiguration;
import utils.Anomaly;
import utils.HtmlHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    private static HtmlHelper html;
    private static ArrayList<File> files = new ArrayList<File>();

    public static void main(String[] args) {
        FileInputStream in = null;

        try
        {
            html = new HtmlHelper();
            String path = "/home/amir/Desktop/6441/Java-Risk-Strategy-Game";
            File folder = new File(path);
            listFilesForFolder(folder);

            for(File f : files)
            {
                if(f.getName().contains(".java"))
                {
                    html.addSection(f.getName(), f.getAbsolutePath());
                    System.out.println(f.getAbsolutePath());
                    in = new FileInputStream(f.getAbsolutePath());
                    parse(in);
                }
            }

            html.generateMarkup("Report");

        }
        catch (FileNotFoundException e)
        {
            //todo
        }


    }

    private static void parse(FileInputStream input)
    {
        CompilationUnit cu = JavaParser.parse(input);
        cu.accept(new TryVisitor(), html);
    }

    private static void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                files.add(fileEntry);
            }
        }
    }

    private static class TryVisitor extends VoidVisitorAdapter<HtmlHelper> {
        @Override
        public void visit(TryStmt n, HtmlHelper arg) {

            CompilationUnit cu = ((CompilationUnit)n.findRootNode());
            for (CatchClause c : n.getCatchClauses())
            {
                Anomaly anomaly = getAnomalyType(c);
                if(anomaly != null)
                    arg.addAnomaly(anomaly.getLine(), anomaly.getType());
            }

            super.visit(n, arg);
        }

        private Anomaly getAnomalyType(CatchClause c)
        {
            Anomaly result = null;
            try
            {
                NodeList<Statement> statements = c.getBody().getStatements();
                String body = c.getBody().toString();
                int line = c.getRange().get().begin.line;

                if(body.toLowerCase().contains("todo") || body.toLowerCase().contains("fixme"))
                    result = new Anomaly(line, 3);
                else if (statements.size() == 0)
                    result = new Anomaly(line, 1);

                System.out.println(body + result );
            }
            catch (Exception e)
            {
                result = new Anomaly(-1, 4);
            }

            return result;
        }

    }


}
