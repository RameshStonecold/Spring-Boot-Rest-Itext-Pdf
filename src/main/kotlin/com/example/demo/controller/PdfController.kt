package com.example.demo.controller

import com.example.demo.pdf.PdfExecution
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayInputStream



@RequestMapping
@RestController
class PdfController {

@Autowired
private lateinit var pdfExecution: PdfExecution

@GetMapping("/getPdf")
fun getPdfReport() :ResponseEntity<InputStreamResource>{


    val bis: ByteArrayInputStream = pdfExecution.getPdfReport()
   // val bis: ByteArrayInputStream = pdfWriter.getPdfContentByteReport()

    val headers = HttpHeaders()
    headers.add("Content-Disposition", "inline; filename=OpenPdfDemo.pdf")

    return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(InputStreamResource(bis))

}

}
