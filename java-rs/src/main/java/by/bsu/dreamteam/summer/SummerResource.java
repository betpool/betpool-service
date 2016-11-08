package by.bsu.dreamteam.summer;

import com.airhacks.porcupine.execution.boundary.Dedicated;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Stateless
@Path("sum")
public class SummerResource {

    @Inject
    SummerManager sm;

    @Inject
    @Dedicated
    ExecutorService summerPipe;

    @GET
    public String message() {
        return "Hello, world!";
    }

    @GET
    @Path("{n}")
    public void message(@PathParam("n") long n, @Suspended AsyncResponse response) {
        response.setTimeout(1, TimeUnit.SECONDS);
        CompletableFuture.supplyAsync(() -> sm.calculateSum(n), summerPipe).thenAccept(response::resume);
    }

}
