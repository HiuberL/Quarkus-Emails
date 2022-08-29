package com.email.andes;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.email.andes.emails.EmailsService;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.smallrye.mutiny.Uni;

@Path("/hello")

public class GreetingResource {
    @Inject
    Mailer mailer;
    @Inject
    ReactiveMailer reactiveMailer;
    @Inject
    EmailsService emails;

    @Path("/reactive")                                      
    public Uni<Void> sendEmailUsingReactiveMailer() throws Exception {       
        byte[] bytes = emails.email();
        return reactiveMailer.send(                         
                    Mail.withText("klizano12@gmail.com",
                        "Ahoy from Quarkus",
                        "A simple email sent from a Quarkus application using the reactive API."
                    ).addAttachment("Roles.pdf", bytes, "application/pdf")
            );
    }
}