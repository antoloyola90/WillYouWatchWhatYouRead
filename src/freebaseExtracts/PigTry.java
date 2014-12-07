package freebaseExtracts;

import java.io.IOException;


import org.apache.pig.ExecType;
import org.apache.pig.PigServer;
//import org.apache.pig.backend.executionengine.ExecException;

public class PigTry {

	public static void main(String[] arg){
        try {
            PigServer pigServer = new PigServer(ExecType.MAPREDUCE);
            runIdQuery(pigServer, "<hdfs input address>");
        } catch (Exception e){
        }
    }

    public static void runIdQuery(PigServer pigServer, String inputFile) throws IOException {
        pigServer.registerQuery("A = load '" + inputFile + "' using PigStorage(',');");
        pigServer.registerQuery("B = foreach A generate $0 as id;");
        pigServer.store("B", "<hdfs output address>");
    }

}
