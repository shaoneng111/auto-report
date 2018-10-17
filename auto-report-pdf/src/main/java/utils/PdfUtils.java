package utils;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.attach.impl.OutlineHandler;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.PageSize;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

public class PdfUtils {

    public static byte[] htmlString2pdf(String html){

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pf = new PdfDocument(writer);

        ConverterProperties properties = new ConverterProperties();
        //处理中文问题
        String font_str = null;
        DefaultFontProvider fontProvider = new DefaultFontProvider(false, false, false );
        fontProvider.addStandardPdfFonts();
        try {
            //          font_str = IOUtils.toString(PdfUtils.class.getResourceAsStream("/font/NotoSansCJKsc-Regular.otf"));
            //         FontProgram fontProgram = FontProgramFactory.createFont(font_str);
            FontProgram fontProgram = FontProgramFactory.createFont(toByteArray(PdfUtils.class.getResourceAsStream("/font/NotoSansCJKsc-Regular.otf")));
            fontProvider.addFont(fontProgram);
            properties.setFontProvider(fontProvider);
        } catch (IOException e) {
            e.printStackTrace();
        }

        OutlineHandler outlineHandler = new OutlineHandler();
        outlineHandler.putTagPriorityMapping("h1", 1);
        outlineHandler.putTagPriorityMapping("h2", 2);
        outlineHandler.putTagPriorityMapping("h3", 3);
        outlineHandler.putTagPriorityMapping("h4", 4);
        outlineHandler.putTagPriorityMapping("h5", 5);

        properties.setOutlineHandler(outlineHandler);

        try {

            Document document = HtmlConverter.convertToDocument(html, pf, properties);
            document.close();

            ByteArrayOutputStream newOutput = new ByteArrayOutputStream();
            PdfReader new_reader = new PdfReader(new ByteArrayInputStream(outputStream.toByteArray()));
            PdfWriter new_writer = new PdfWriter(newOutput);


            //生成目录pdf
            PdfDocument newpf = new PdfDocument(new_reader, new_writer);
            int ps = newpf.getNumberOfPages();
            //
            System.out.println(ps);

            Table table = new Table(new float[]{PageSize.A4.getWidth() - 150, 100}, true);
            //字体
            PdfFont font = PdfFontFactory.createFont(toByteArray(PdfUtils.class.getResourceAsStream("/font/NotoSansCJKsc-Regular.otf")), PdfEncodings.IDENTITY_H, false);

            for (int i = 1; i <= ps; i++){
                PdfPage page = newpf.getPage(i);
                List<PdfOutline> outlines = page.getOutlines(true);
                if (outlines != null){
                    for (PdfOutline outline : outlines) {
                        int level = _getOutlineLeve(outline) - 1;
                        StringBuilder st = new StringBuilder("");
                        StringBuilder st1 = new StringBuilder("");
                        for (int j = 1; j < level; j++){
                            st.append("\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0");
                            st1.append("目目");
                        }

                        st.append(outline.getTitle());
                        Cell cell1 = new Cell();
                        Paragraph p = new Paragraph(st.toString());
                        p.setTextAlignment(TextAlignment.LEFT).setFont(font);
                        cell1.setBorder(Border.NO_BORDER);


                        StringBuilder dt = new StringBuilder("");
                        System.out.println(st1.toString().getBytes("GB2312").length + "==");
                        System.out.println(86 - (st1.toString().getBytes("GB2312").length%86)/2);
                        for (int k = 0; k < (86 - (st1.toString().getBytes("GB2312").length%86)/2); k++) {
                            dt.append("⋯");
                        }

                        Paragraph pline = new Paragraph(dt.toString() + i);
                        pline.setMarginTop(-23);
                        pline.setMarginRight(10);
                        pline.setTextAlignment(TextAlignment.RIGHT).setFont(font);

                        cell1.add(p);
                        cell1.add(pline);

                        table.startNewRow().addCell(cell1);


                    }
                }
            }





        } catch (IOException e) {
            e.printStackTrace();
        }
        return  outputStream.toByteArray();
    }

    private static int _getOutlineLeve(PdfOutline outline) {
        if (outline.getParent() == null){
            return  1;
        }
        return 1 + _getOutlineLeve(outline.getParent());
    }


    public void saveFile(byte[] fileContent, String filePath){
        try {

//            File file = new File(filePath);
//            file.createNewFile();
 //           String path = PdfUtils.class.getResource(filePath).getFile().toString();
            File file = new File(filePath);
//            OutputStream outputStream = new FileOutputStream(new File(filePath));

            OutputStream outputStream = new FileOutputStream(file);
            IOUtils.write(fileContent, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static byte[] toByteArray(InputStream inputStream){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        copy(inputStream, outputStream);
        return  outputStream.toByteArray();
    }

    private static int copy(InputStream inputStream, ByteArrayOutputStream outputStream) {
        long count = 0;
        try {
            count = copyLarge(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    private static final int DEFAULT_BUFFER_SIZE = 1024*4;


    private static long copyLarge(InputStream inputStream, ByteArrayOutputStream outputStream) throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        long count = 0;
        int n = 0;
        while (-1 != (n = inputStream.read(buffer))){
            outputStream.write(buffer, 0, n);
            count += n;
        }
        return  count;
    }

    public static void main(String[] args) {
        PdfUtils pdfUtils = new PdfUtils();

        try {
            //     String html = IOUtils.toString(pdfUtils.getClass().getResourceAsStream("/html/test1.html"),"UTF-8");
            String html = IOUtils.toString(pdfUtils.getClass().getResourceAsStream("/html/all.html"),"UTF-8");
            System.out.println(html);
            byte[] b = PdfUtils.htmlString2pdf(html);

           String s =  IOUtils.toString(pdfUtils.getClass().getResourceAsStream(""),"UTF-8");
            System.out.println(s);
            pdfUtils.saveFile(b, "auto-report-pdf/target/test.pdf");
            System.out.println(System.getProperty("user.dir"));
            //           System.out.println(Arrays.toString(b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
