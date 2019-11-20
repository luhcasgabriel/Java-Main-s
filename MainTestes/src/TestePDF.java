import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.media.jai.RenderedImageAdapter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;

public class TestePDF {

  private static Path _uPathArquivos = Paths.get("C:\\Users\\lucas.argus\\Pictures\\testeimagens");

  private static Long _uStart;

  private static boolean _uProcessaAntigo = true;

  public static void main(String[] args) throws IOException {

    try {
      // PDDocument _rPdfConvertido = new PDDocument();
      // PDDocument _rPdfImagem = new PDDocument();
      //
      // File file = new
      // File("X:\\TI\\JEE6_git\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\J79M01\\Rel\\55067720-493c-4b9e-8f9d-71a176a2ef63_29225D0CE298D76048A5FD0A21ACEEE2.pdf");
      //
      // _rPdfImagem = PDDocument.load(file);
      //
      // PDPage pag = _rPdfImagem.getPage(0);
      //
      // _rPdfConvertido.addPage(pag);
      //
      // File pdf = new
      // File("C:\\Users\\lucas.silva\\Desktop\\LUHCASGABRIEL\\BACKUP\\teste2222222222.pdf");
      //
      // _rPdfConvertido.save(pdf);
      // _rPdfConvertido.close();

      // proSalvaPaginaPDF(_rPdfConvertido, file.toPath() );

      proSalvaPaginaPDF();

    } catch (Exception error) {
      error.printStackTrace();
    }

  }

  private static void proSalvaPaginaPDF() throws IOException {

    File file = new File(
        "C:\\Users\\lucas.silva\\Desktop\\LUHCASGABRIEL\\IMAGENS\\0d572b9c-8545-49a3-ab32-89b9bb7e6e08.png");
    File pdf = new File("C:\\Users\\lucas.silva\\Desktop\\LUHCASGABRIEL\\BACKUP\\teste777777.pdf");
    
    List<File> imagens = Arrays.asList(
        new File(
            "C:\\Users\\lucas.silva\\Desktop\\LUHCASGABRIEL\\IMAGENS\\0d572b9c-8545-49a3-ab32-89b9bb7e6e08.png"),
        new File(
            "C:\\Users\\lucas.silva\\Desktop\\LUHCASGABRIEL\\IMAGENS\\1c904c32-e2e0-49a9-962d-d10f9d4f8dbc.png"),
        new File(
            "C:\\Users\\lucas.silva\\Desktop\\LUHCASGABRIEL\\IMAGENS\\1e376e90-4226-464f-9c98-28c96558cac0.png"),
        new File(
            "C:\\Users\\lucas.silva\\Desktop\\LUHCASGABRIEL\\IMAGENS\\2bb220e8-c636-43bd-bcea-451fc2dcbd99.png")
        );
    try (
        InputStream fis = new FileInputStream(file);
        PDDocument _rPdfConvertido = new PDDocument();
    ) {
      
      for (File imageFile : imagens) {
        BufferedImage image = ImageIO.read(imageFile);
        final PDPage newpage = new PDPage(new PDRectangle(image.getWidth(), image.getHeight()));
//  
//      final PDImageXObject fromImage = PDImageXObject.createFromFile(file.getPath(), _rPdfConvertido);
        _rPdfConvertido.addPage(newpage);
        
        try (PDPageContentStream contentStream = new PDPageContentStream(_rPdfConvertido, newpage);) {
          contentStream.drawImage(
              JPEGFactory.createFromImage(_rPdfConvertido, image, 0.6F)
//              LosslessFactory.createFromImage(_rPdfConvertido, image)
              ,0, 0, image.getWidth(), image.getHeight());
//      contentStream.drawImage(fromImage, 0, 0, fromImage.getWidth(), fromImage.getHeight());
          image.flush();
        }
      }
  
      _rPdfConvertido.save(pdf);
    }
  }

  private List<BufferedImage> getBufferedImage(InputStream imageIS, String ext) throws IOException {
    List<BufferedImage> imagensCarregadas = new ArrayList<>();
    try {
      // trata casos em que o formato e permitido porem o decoder nao possui
      // exatamente nome da extensao
      ext = "jpg".equals(ext) ? "jpeg" : "tif".equals(ext) ? "tiff" : ext;
      ImageDecoder decoder = ImageCodec.createImageDecoder(ext, imageIS, null);
      // verifica se achou decoder para extensao especificada
      if (decoder != null) {
        for (int pgIndex = 0; pgIndex < decoder.getNumPages(); pgIndex++) {
          RenderedImage image = decoder.decodeAsRenderedImage(pgIndex);
          imagensCarregadas.add(new RenderedImageAdapter(image).getAsBufferedImage());
        }

        return imagensCarregadas;
      }
    } catch (Exception e) {
      System.out.println("erro");
    }
    return null;
  }

}
