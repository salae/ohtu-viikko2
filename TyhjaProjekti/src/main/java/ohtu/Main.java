package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "011032054";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2016.herokuapp.com/students/"+studentNr+"/submissions";

        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);

        InputStream stream =  method.getResponseBodyAsStream();

        String bodyText = IOUtils.toString(stream);

//        System.out.println("json-muotoinen data:");
//        System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        System.out.println("Opiskelijanumero " + subs[0].getStudent_number());
        System.out.println();
        for (Submission submission : subs) {
            System.out.println(submission);
        }
        System.out.println();
        System.out.print("yhteensä: ");
        int tehtavat = 0;
        int tunnit = 0;
        for (Submission submission : subs) {
            tehtavat += submission.numberOfDoneTasks();
            tunnit += submission.getHours();
        }
        System.out.print(tehtavat + " tehtävää " + tunnit + " tuntia");
        System.out.println();

    }
}
