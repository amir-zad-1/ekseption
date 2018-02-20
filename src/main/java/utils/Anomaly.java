package utils;

public class Anomaly {

    private int line = -1;
    private int type = -1;

    public Anomaly(int line, int type)
    {
        this.line = line;
        this.type = type;
    }

    public int getLine()
    {
        return this.line;
    }

    public int getType()
    {
        return this.type;
    }

}
