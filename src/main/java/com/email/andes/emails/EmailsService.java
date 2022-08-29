package com.email.andes.emails;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
 
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.jboss.logging.Logger;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;

@ApplicationScoped
public class EmailsService {
    @Inject
    Mailer mailer;

    @Inject
    ReactiveMailer reactiveMailer;
    private static final Logger LOG = Logger.getLogger(EmailsService.class);

    public byte[] email() throws Exception {         
        String content = "dummy content"; //this will be the text of the email
  
        ByteArrayOutputStream outputStream = null;
         
        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setText(content);

        //now write the PDF content to the output stream
        outputStream = new ByteArrayOutputStream();
        writePdf(outputStream);
        byte[] bytes = outputStream.toByteArray();
            
        return bytes;                        
    }

    public void writePdf(OutputStream outputStream) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        document.addTitle("Test PDF");
        document.addSubject("Testing email PDF");
        document.addKeywords("iText, email");
        document.addAuthor("Jee Vang");
        document.addCreator("Jee Vang");
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk("hello!"));
        document.add(paragraph);
        document.close();
    }
}
