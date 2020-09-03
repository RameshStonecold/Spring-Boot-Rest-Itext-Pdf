package com.example.demo.pdf


import com.example.demo.controller.PageNumbersWatermark
import com.example.demo.model.Products
import com.lowagie.text.*
import com.lowagie.text.Element.*
import com.lowagie.text.alignment.HorizontalAlignment
import com.lowagie.text.alignment.VerticalAlignment
import com.lowagie.text.pdf.BaseFont
import com.lowagie.text.pdf.PdfWriter
import com.lowagie.text.pdf.RGBColor
import org.springframework.stereotype.Component
import org.springframework.util.ResourceUtils
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import javax.imageio.ImageIO


@Component
class PdfExecution {


fun  getPdfReport() :ByteArrayInputStream {


    val document = Document(PageSize.A4)
    val out = ByteArrayOutputStream()

    try {
        val pdfWriter = PdfWriter.getInstance(document, out)


        var footer = HeaderFooter(Phrase("PO No: PO/2020/00003, Page "),Phrase(" of "))
        footer.border = Rectangle.NO_BORDER
        footer.setAlignment(Element.ALIGN_RIGHT)
        pdfWriter.setPageEvent(PageNumbersWatermark("PO No: PO/2020/00003"))
//        document.setFooter(footer)

        document.open()

        var rectangle1 = Rectangle(40f, 450f, 250f, 600f)
        rectangle1.border = Rectangle.BOX
        rectangle1.width
        rectangle1.borderWidthLeft=1f
        rectangle1.borderWidthTop = 1f
        rectangle1.borderWidthBottom=1f
        rectangle1.borderColor=Color.BLACK
        rectangle1.borderWidthRight =1f
        document.add(rectangle1)

        var rectangle2 = Rectangle(270f, 495f, 515f, 600f)
        rectangle2.border = Rectangle.RECTANGLE
        rectangle2.width
        rectangle2.borderWidthLeft=1f
        rectangle2.borderWidthTop = 1f
        rectangle2.borderWidthBottom=1f
        rectangle2.borderColor=Color.BLACK
        rectangle2.borderWidthRight =1f
        document.add(rectangle2)

        var guthriesGroupContent = "Guthire's Group \n" +
                                   "Nagarjuna colony , \n" +
                                   "ccc Naspur, \n" +
                                   "Mancherial mdl, \n" +
                                   "Telangana-504302 \n"

        var orderNoAndDate = "Order No : yuddff \n" +
                                 "Date : 27/08/2014 \n"

        var orderTo = "Order To : \n" +
                      "SUPPLIER 23200, \n" +
                      "uk London warehouse 2020,\n" +
                      "railway bridge, \n" +
                      "Paris, FRG666, \n " +
                      "France"


        //Image
        val file = ResourceUtils.getFile("classpath:static/guthriesGroup.png")
        val bImage: BufferedImage = ImageIO.read(file)
        val bos = ByteArrayOutputStream()
        ImageIO.write(bImage, "png", bos)
        val byteData = bos.toByteArray()
        var image = ImageLoader.getPngImage(byteData)
        image.setAbsolutePosition(20f,725f)
        image.scalePercent(30f,30f)
        image.alignment= ALIGN_MIDDLE
        document.add(image)


        var guthriesContent = pdfWriter.directContent
        guthriesContent.beginText()
        val baseFont: BaseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED)
        guthriesContent.setFontAndSize(baseFont, 16f)
        guthriesContent.showTextAligned(10,"Guthrie's Group",400f,800f,0f)
        guthriesContent.saveState()
        guthriesContent.endText()
        guthriesContent.restoreState()

      //Top Right
       //Guthries Group Address
        var para= Paragraph()
        para.spacingBefore=4f
        para.add("Nagarjuna colony , \n" +
                "ccc Naspur, \n" +
                "Mancherial mdl, \n" +
                "Telangana-504302 \n")
        para.alignment = ALIGN_RIGHT
        para.spacingAfter=30f
        para.indentationRight=50f
        para.font = Font(Font.HELVETICA,12f)
        document.add(para)



       //Heading Purchase Order in Middle
       var para2 = Paragraph("PURCHASE ORDER",FontFactory.getFont(FontFactory.HELVETICA,15f,Font.BOLD))
        para2.alignment = ALIGN_CENTER
        para2.spacingAfter=10f
        para2.font= Font(Font.HELVETICA,20f)
        document.add(para2)

       var para3 = Paragraph(orderNoAndDate)
        para3.alignment = ALIGN_LEFT
        para3.spacingAfter=20f
        para3.indentationLeft=20f
        para3.font= Font(Font.HELVETICA,16f)
        document.add(para3)



       var orderPhrase = Paragraph(Phrase("Order To :",FontFactory.getFont(FontFactory.HELVETICA,14f,Font.BOLD)))
        orderPhrase.alignment = ALIGN_LEFT
        orderPhrase.indentationLeft=20f
       var para4 = Paragraph("SUPPLIER 23200, \n" +
                            "uk London warehouse 2020,\n" +
                             "railway bridge, \n" +
                             "Paris, FRG666, \n " +
                             "France")
        para4.alignment = ALIGN_LEFT
        para4.indentationLeft=20f
        para4.font=Font(Font.HELVETICA,12f)
        para4.spacingAfter=30f
        document.add(orderPhrase)
        document.add(para4)

        var cb1 = pdfWriter.directContent
        cb1.beginText()
        val bf: BaseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED)
        cb1.setFontAndSize(bf, 12f)
        cb1.showTextAligned(10,"Deliver To :" ,300f,570f,0f)
        cb1.saveState()
        cb1.endText()
        cb1.restoreState()
        var cb2 = pdfWriter.directContent
        cb2.beginText()
        cb2.setFontAndSize(bf, 12f)
        cb2.showTextAligned(10,"Continental Warehouse",300f,550f,0f)
        cb2.saveState()
        cb2.endText()
        cb2.restoreState()
        var cb3 = pdfWriter.directContent
        cb3.beginText()
        cb3.setFontAndSize(bf, 12f)
        cb3.showTextAligned(10,"For Testing Purpose.",300f,530f,0f)
        cb3.saveState()
        cb3.endText()
        cb3.restoreState()

        var table = Table(5)
          //table.spacing=100f
        val columnWidths = intArrayOf(40, 90,25, 25,25)
        table.setWidths(columnWidths)
        table.borderWidth = 1f
        table.padding = 1F
        table.setHorizontalAlignment(HorizontalAlignment.LEFT)
        table.width=95f
        table.lastHeaderRow=1

        var c1 = Cell(Phrase("Supplier Code",FontFactory.getFont(FontFactory.HELVETICA,12f,Font.BOLD)))
        c1.isHeader = true
        c1.border=0
        c1.backgroundColor = RGBColor(239, 230, 237)
        c1.setVerticalAlignment(VerticalAlignment.CENTER)
        c1.setHorizontalAlignment(HorizontalAlignment.LEFT)

        val c2 = Cell(Phrase("Product Name",FontFactory.getFont(FontFactory.HELVETICA,12f,Font.BOLD)))
        c2.isHeader = true;
        c2.border=0
        c2.backgroundColor = RGBColor(239, 230, 237)
        c2.setVerticalAlignment(VerticalAlignment.TOP)
        c2.setHorizontalAlignment(HorizontalAlignment.LEFT)


        val c3 = Cell(Phrase("Quantity",FontFactory.getFont(FontFactory.HELVETICA,12f,Font.BOLD)))
        c3.isHeader=true
        c3.backgroundColor = RGBColor(239, 230, 237)
        c3.border=0
        c3.setVerticalAlignment(VerticalAlignment.TOP)
        c3.setHorizontalAlignment(HorizontalAlignment.RIGHT)

        val c4 = Cell(Phrase("Price",FontFactory.getFont(FontFactory.HELVETICA,12f,Font.BOLD)))
        c4.isHeader = true
        c4.border=0
        c4.backgroundColor = RGBColor(239, 230, 237)
        c4.setVerticalAlignment(VerticalAlignment.TOP)
        c4.setHorizontalAlignment(HorizontalAlignment.RIGHT)

        val c5 = Cell(Phrase("Value",FontFactory.getFont(FontFactory.HELVETICA,12f,Font.BOLD)))
        c5.backgroundColor = RGBColor(239, 230, 237)
        c5.setVerticalAlignment(VerticalAlignment.TOP)
        c5.setHorizontalAlignment(HorizontalAlignment.RIGHT)
        c5.isHeader = true
        c5.border=0


       // lastCell.setRight(5)

        var products1 = Products("1223","46788","AJKK sample xyz / 12 /21-1-45-34/2020","7843","$076728","335")
        val products2 = Products("1224","46790","AJKK sample xyz / 12 /21-1-2","7845","$076728","330")
        val products3 = Products("1225","46792","AJKK sample xyz / 12 /21-1-3","7847","$076728","325")
        val products4 = Products("1226","46794","AJKK sample xyz / 12 /21-1-4","7849","$076728","320")
        val products5 = Products("1276","46796","AJKK sample xyz / 12 /21-1-6","7850","$076729","319")



        var productList = ArrayList<Products>()

        var x=60
        for (x  in 0..60){
            productList.add(products1)
        }

        table.addCell(c1);
        table.addCell(c2)
        table.addCell(c3)
        table.addCell(c4)
        table.addCell(c5)
        table.lastHeaderRow=1
        table.endHeaders()


        for (prod in productList){
            var c6 = Cell(prod.supplierCode)
            c6.width=10f
            c6.border=0
            //c6.colspan=0
            c6.setVerticalAlignment(VerticalAlignment.CENTER)
            c6.setHorizontalAlignment(HorizontalAlignment.LEFT)


            val c7 = Cell(prod.productName)

            c7.border=0
           // c7.colspan=0
            c7.setVerticalAlignment(VerticalAlignment.CENTER)
            c7.setHorizontalAlignment(HorizontalAlignment.LEFT)


            val c8 = Cell(prod.quantity)
            c8.border=0
            //c8.colspan=0
            c8.setVerticalAlignment(VerticalAlignment.CENTER)
            c8.setHorizontalAlignment(HorizontalAlignment.RIGHT)

            val c9 = Cell(prod.price)
            c9.border=0
            //c9.colspan=0
            c9.setVerticalAlignment(VerticalAlignment.CENTER)
            c9.setHorizontalAlignment(HorizontalAlignment.RIGHT)

            val c10 = Cell(prod.value)
            c10.setVerticalAlignment(VerticalAlignment.CENTER)
            c10.setHorizontalAlignment(HorizontalAlignment.RIGHT)
            //c10.colspan=0
            c10.border=0

            table.addCell(c6);
            table.addCell(c7)
            table.addCell(c8)
            table.addCell(c9)
            table.addCell(c10)
            table.border=0

        }

        val c11 = Cell(productList.sumBy { it.value.toInt() }.toString())
        c11.setVerticalAlignment(VerticalAlignment.CENTER)
        c11.setHorizontalAlignment(HorizontalAlignment.RIGHT)
        c11.isHeader = false
        c11.colspan =1
        c11.border=0

        val c12 = Cell("Total Value")
        c12.setVerticalAlignment(VerticalAlignment.CENTER)
        c12.setHorizontalAlignment(HorizontalAlignment.RIGHT)
        c12.isHeader = false
        c12.border=0
        c12.colspan=4


        table.addCell(c12)
        table.addCell(c11)

        document.add(table)


        val lastPara =Paragraph("Could you confirm the receipt of PO and prices are correct")
        lastPara.alignment = ALIGN_LEFT
        lastPara.spacingBefore=50f
        lastPara.font= Font(Font.HELVETICA,20f)
        document.add(lastPara)




    } catch (de: DocumentException) {
        println(de.message)
    } catch (de: IOException) {
        System.err.println(de.message)
    }
    document.close()
    return ByteArrayInputStream(out.toByteArray());
    }





















    fun  getPdfContentByteReport() :ByteArrayInputStream {



        val document = Document()
        val out = ByteArrayOutputStream()

        try {
            val pdfWriter = PdfWriter.getInstance(document, out)
            document.open()

            var cb = pdfWriter.directContent
            var cb2 = pdfWriter.directContent
            var cb3 = pdfWriter.directContent
            var cb4 = pdfWriter.directContent

            cb.beginText()
            val bf: BaseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED)
            cb.setFontAndSize(bf, 15f)
            cb.showTextAligned(10,"Guthrie's Group",470f,600f,0f)
            cb.saveState()
            cb.endText()
            cb.restoreState()
            cb2.beginText()
            cb2.showTextAligned(10,"ccc Naspur,",470f,690f,0f)
            cb2.saveState()
            cb2.endText()
            cb2.restoreState()
            cb3.beginText()
            cb3.showTextAligned(10, "Mancherial mdl," ,470f,680f,0f)
            cb3.saveState()
            cb3.endText()
            cb3.restoreState()
            cb4.beginText()
            cb4.showTextAligned(100, "Telangana-504302," ,470f,670f,0f)
            cb4.saveState()
            cb4.endText()
            cb4.restoreState()

            document.add(Paragraph("Hello"))

        }catch (e:DocumentException){
            System.err.println(e.message)
        }
        document.close()
        return ByteArrayInputStream(out.toByteArray());
    }
    /*
        val encoded: ByteArray = Files.readAllBytes(file)
     or
     val resource =  ClassPathResource("static/gthriesGroup.png")
    val stream = resource.getInputStream()
    val jpg = Image.getInstance("static/gthriesGroup.jpg")


      val bImage: BufferedImage = ImageIO.read(File("static/guthriesGroup.png"))
        val bos = ByteArrayOutputStream()
        ImageIO.write(bImage, "png", bos)
        val byteData = bos.toByteArray()
         document.add(imageLoader)*/
    /* var cb = PdfContentByte(pdfWriter)

       val bf: BaseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED)
       cb.saveState()
       cb.beginText()
       cb.moveText(30f, 30f)
       cb.setFontAndSize(bf, 12f)
       cb.showText("Guthrie's Group \n"+
                        "Nagarjuna colony,\n" +
                        "ccc Naspur,\n" +
                        "Mancherial mdl, \n" +
                        "Telangana-504302 \n")
        cb.endText()
       cb.restoreState()



       "Guthrie's Group \n" , FontFactory.getFont( FontFactory.HELVETICA, 15f))
                        */

}

