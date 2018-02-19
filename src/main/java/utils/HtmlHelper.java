package utils;

import j2html.TagCreator;
import j2html.tags.ContainerTag;
import j2html.tags.Tag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static j2html.TagCreator.*;

public class HtmlHelper {
    private ContainerTag Html;
    private ContainerTag Table;

    public HtmlHelper()
    {
        this.Table = TagCreator.table().attr("").attr("class='table ttable-striped'")
                .with(tbody()
                );
    }

    public void addAnomaly()
    {
        this.Table.with(tr(
                td("label"),
                td("test")
        ));
    }

    public void addSection(String fileName)
    {
        this.Table.with(tr(
                td(div(fileName).attr("class='alert alert-info' role='alert'")).attr("colspan='2'")//.attr("style='background-color:green;padding:7px'")
                //td(h4(fileName).attr("class='link text-danger'")).attr("colspan='2'")//.attr("style='background-color:green;padding:7px'")
        ));
    }

    public String generateMarkup(String title)
    {
        Tag head = TagCreator.head(TagCreator.title(title.trim()))
                .with(meta().attr("charset='utf-8'"))
                .with(meta().attr("name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\""))
                .with(TagCreator.link()
                .attr("rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css'")
                .attr("rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'")
                );
        this.Html = TagCreator.html().with(head);

        String result = this.Html.with(
                body(
                        h1(" Report").attr("style='padding:10px'"),
                        br(),
                        h5(" Legend:").attr("style='padding:10px'"),
                        ol(li("Empty catch blocks"), li("Over-catches exceptions and aborts"), li("//TODO or //FixMe")),
                        this.Table,
                        script().attr("src='https://code.jquery.com/jquery-3.1.1.slim.min.js'"),
                        script().attr("src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js'"),
                        script().attr("src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js'")
                )).render();
        System.out.println(result);
        try
        {
            this.saveToFile(result);
        }
        catch (IOException e)
        {

        }

        return result;
    }

    public void saveToFile(String content) throws IOException {
        File myFoo = new File("output/foo.html");
        FileWriter fooWriter = new FileWriter(myFoo, false); // true to append
        // false to overwrite.
        fooWriter.write(content);
        fooWriter.close();
    }





}
