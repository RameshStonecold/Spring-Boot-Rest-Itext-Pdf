package com.example.demo.controller

import com.lowagie.text.*
import com.lowagie.text.pdf.*
import java.awt.Color
import java.awt.Image
import java.io.FileOutputStream
import com.lowagie.text.Font;



class PageNumbersWatermark(private val initialText:String) : PdfPageEventHelper() {
    /** An Image that goes in the header.  */
//    var headerImage: Image? = null

    /** The headertable.  */
    var table: PdfPTable? = null

    /** The Graphic state  */
    var gstate: PdfGState? = null

    /** A template that will hold the total number of pages.  */
    var tpl: PdfTemplate? = null

    /** The font that will be used.  */
    var helv: BaseFont? = null

    /**
     * @see com.lowagie.text.pdf.PdfPageEventHelper.onOpenDocument
     */
    override fun onOpenDocument(writer: PdfWriter, document: Document?) {
        try {
            // initialization of the header table
//            headerImage = Image("logo.gif")
            table = PdfPTable(2)
//            val p = Phrase()
//            var ck: Chunk? = Chunk("lowagie.com\n", Font(Font.TIMES_ROMAN, 16f, Font.BOLDITALIC, Color.blue))
//            p.add(ck)
//            ck = Chunk("Ghent\nBelgium", Font(Font.HELVETICA, 12f, Font.NORMAL, Color.darkGray))
//            p.add(ck)
            table!!.defaultCell.backgroundColor = Color.white
            table!!.defaultCell.borderWidth = 0f
//            table!!.addCell(p)
            table!!.defaultCell.horizontalAlignment = Element.ALIGN_RIGHT

            // initialization of the Graphic State
            gstate = PdfGState()
            gstate!!.setFillOpacity(0.3f)
            gstate!!.setStrokeOpacity(0.3f)
            // initialization of the template
            tpl = writer.directContent.createTemplate(100f, 100f)
           // tpl!!.boundingBox = Rectangle(-20f, -20f, 100f, 100f)
             //initialization of the font
            helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false)
        } catch (e: Exception) {
            throw ExceptionConverter(e)
        }
    }

    /**
     * @see com.lowagie.text.pdf.PdfPageEventHelper.onEndPage
     */
    override fun onEndPage(writer: PdfWriter, document: Document) {
        val cb: PdfContentByte = writer.directContent
        cb.saveState()
        // write the headertable
        table!!.totalWidth = document.right() - document.left()
        table!!.writeSelectedRows(0, -1, document.left(), document.getPageSize().getHeight() - 50, cb)
        // compose the footer
        val text = "${this.initialText}, Page " + writer.getPageNumber().toString() + " of "
        val textSize = helv!!.getWidthPoint(text, 12f)
        val textBase: Float = document.bottom() - 20
        cb.beginText()
        cb.setFontAndSize(helv, 12f)
//        // for odd pagenumbers, show the footer at the left
//        if (writer.getPageNumber() and 1 === 1) {
//            cb.setTextMatrix(document.right(), textBase)
//            cb.showText(text)
//            cb.endText()
//            cb.addTemplate(tpl, document.left() + textSize, textBase)
//        } else {
//            val adjust = helv!!.getWidthPoint("0", 12f)
//            cb.setTextMatrix(document.right() - textSize - adjust, textBase)
//            cb.showText(text)
//            cb.endText()
//            cb.addTemplate(tpl, document.right() - adjust, textBase)
//        }
        // footer
        val adjust = helv!!.getWidthPoint("0", 12f)
        cb.setTextMatrix(document.right() - textSize - adjust, textBase)
        cb.showText(text)
        cb.endText()
        cb.addTemplate(tpl, document.right() - adjust, textBase)

        cb.saveState()
        // draw a Rectangle around the page
//        cb.setColorStroke(Color.orange)
//        cb.setLineWidth(2f)
//        cb.rectangle(20f, 20f, document.getPageSize().getWidth() - 40, document.getPageSize().getHeight() - 40)
//        cb.stroke()
        cb.restoreState()
        // starting on page 3, a watermark with an Image that is made transparent
       // if (writer.pageNumber >= 3) {
//            cb.setGState(gstate)
//            cb.setColorFill(Color.red)
//            cb.beginText()
//            cb.setFontAndSize(helv, 48f)
//            cb.showTextAligned(Element.ALIGN_CENTER, "Watermark Opacity " + writer.getPageNumber(), document.getPageSize().getWidth() / 2, document.getPageSize().getHeight() / 2, 45f)
//            cb.endText()

       // }
        cb.restoreState()
        cb.sanityCheck()
    }

    /**
     * @see com.lowagie.text.pdf.PdfPageEventHelper.onStartPage
     */
//    override fun onStartPage(writer: PdfWriter, document: Document) {
//        if (writer.getPageNumber() < 3) {
//            val cb: PdfContentByte = writer.getDirectContentUnder()
//            cb.saveState()
//            cb.setColorFill(Color.pink)
//            cb.beginText()
//            cb.setFontAndSize(helv, 48f)
//            cb.showTextAligned(Element.ALIGN_CENTER, "My Watermark Under " + writer.getPageNumber(), document.getPageSize().getWidth() / 2, document.getPageSize().getHeight() / 2, 45f)
//            cb.endText()
//            cb.restoreState()
//        }
//    }

    /**
     * @see com.lowagie.text.pdf.PdfPageEventHelper.onCloseDocument
     */
    override fun onCloseDocument(writer: PdfWriter, document: Document?) {
        tpl!!.beginText()
        tpl!!.setFontAndSize(helv, 12f)
        tpl!!.setTextMatrix(0f, 0f)
        tpl!!.showText(Integer.toString(writer.pageNumber - 1))
        tpl!!.endText()
        tpl!!.sanityCheck()
    }

}
