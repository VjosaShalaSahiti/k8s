package com.example.pdf;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.ByteArrayOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class PdfController {
    @PostMapping(value = "/pdf")
    public byte[] generatePdf(@RequestBody String input, @RequestParam String res) throws DocumentException {
        // Handle POST request for /pdf endpoint
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        document.add(new Paragraph("Inputed text:"));
        document.add(new Paragraph(input));
        document.add(new Paragraph("Redacted text:"));
        document.add(new Paragraph(res));
        document.close();

        return outputStream.toByteArray();
    }

    @RequestMapping(value = "/pdf")
    public ResponseEntity<byte[]> generatePdfs(@RequestParam String input, @RequestParam String res)
            throws DocumentException {
        // Handle the request and generate the PDF using the input parameter
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        document.add(new Paragraph("Inputed text:"));
        document.add(new Paragraph(input));
        document.add(new Paragraph("Redacted text:"));
        document.add(new Paragraph(res));
        document.close();

        byte[] pdfBytes = outputStream.toByteArray();

        // Set the appropriate headers for the response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "redacted_pdf.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
