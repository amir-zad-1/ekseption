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
        this.Table = TagCreator.table().attr("").attr("class='table table-striped'")
                .with(tbody()
                );
    }

    public void addAnomaly(int line, int type)
    {
        this.Table.with(tr(
                td(span("Line Number: " + line).attr("class=''")),
                td(span("Anomaly Type: " + type).attr("class=''"))
        ));
    }

    public void addSection(String fileName)
    {
        this.Table.with(tr(
                td(div(i().attr("class='glyphicon glyphicon-chevron-right'"), span(" "+fileName))
                        .attr("class='alert alert-info' role='alert' style='margin-bottom:0'"))
                        .attr("colspan='2'")
        ));
    }

    public String generateMarkup(String title)
    {
        Tag head = TagCreator.head(TagCreator.title(title.trim()))
                .with(meta().attr("charset='utf-8'"))
                .with(meta().attr("name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\""))
                .with(TagCreator.link()
                .attr("rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'")
                //.attr("rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'")
                );
        this.Html = TagCreator.html().with(head);

        String result = this.Html.with(
                body(
                        h1(i().attr("class='glyphicon glyphicon-list-alt text-primary'"), span("Report")
                        ).attr("style='padding:7px;padding-top:0px;'"),
                        h5(i().attr("class='glyphicon glyphicon-chevron-right text-primary'"), span(" Legend:")).attr("style='padding:10px'"),
                        ol(li("Empty catch blocks"), li("Over-catches exceptions and aborts"),
                                li("//TODO or //FixMe"),
                                li("Error")),
                        this.Table,
                        script().attr("src='https://code.jquery.com/jquery-3.1.1.slim.min.js'"),
                        script().attr("src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js'"),
                        script().attr("src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'")
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
