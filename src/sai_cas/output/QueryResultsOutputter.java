package sai_cas.output;
import java.io.PrintWriter;
import sai_cas.db.DBInterface;

public interface QueryResultsOutputter
{
	public void print(PrintWriter out, DBInterface dbi);
	public void printError(PrintWriter out, String message);

}