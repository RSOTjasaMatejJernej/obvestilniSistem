
package si.fri.rso.obvestilnisistem;

import com.kumuluz.ee.common.runtime.EeRuntime;
import org.eclipse.microprofile.metrics.annotation.Metered;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.*;
import java.io.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("obvestilniSistem")
@ApplicationScoped
public class ObvestilniSistemResource {

    @Inject
    private RestProperties restProperties;

    @GET
    @Metered
    public Response getAllProfils() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String output  = getUrlContents("http://api.rtvslo.si/spored/getProvys/TVS1/"+dateFormat.format(date));

        return Response.ok(output).build();
    }

    private static String getUrlContents(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        // many of these calls can throw exceptions, so i've just
        // wrapped them all in one try/catch statement.
        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }

    /*@GET
    @Path("{profilId}")
    public Response getProfil(@PathParam("profilId") String profilId) {
        Profil profil = Database.getProfil(profilId);
        return profil != null
                ? Response.ok(profil).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewProfil(Profil profil) {
        Database.addProfil(profil);
        return Response.ok(profil).build();
    }

    @DELETE
    @Path("{profilId}")
    public Response deleteProfil(@PathParam("profilId") String profilId) {
        Database.deleteProfil(profilId);
        return Response.ok(Response.Status.OK).build();
    }

    @POST
    @Path("healthy")
    public Response setHealth(Boolean healthy) {
        restProperties.setHealthy(healthy);
        return Response.ok().build();
    }

    @POST
    @Path("load")
    public Response loadOrder(Integer n) {

        for (int i = 1; i <= n; i++) {
            fibonacci(i);
        }

        return Response.status(Response.Status.OK).build();
    }

    private long fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }

    @GET
    @Path("instanceid")
    public Response getInstanceId() {

        String instanceId =
                "{\"instanceId\" : \"" + EeRuntime.getInstance().getInstanceId() + "\"}";

        return Response.ok(instanceId).build();
    }*/
}
