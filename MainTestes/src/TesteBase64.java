import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class TesteBase64 {

  public static void main(String[] args) {
    byte[] fileContent = null;

    List<File> listFiles = Arrays.asList(
//        new File("C:\\Users\\lucas.silva\\Downloads\\whatsapp-logo-image-8.png")
//        new File("C:\\Users\\lucas.silva\\Downloads\\wmmeasy-img-brog-whatsapp.jpg")
//        new File("C:\\Users\\lucas.silva\\Downloads\\add-3d-photos-facebook-using.jpg")
        new File("C:\\Users\\lucas.silva\\Downloads\\download.png")
       );

    for (File fileteste : listFiles) {

      try {
        fileContent = FileUtils.readFileToByteArray(fileteste);
      } catch (IOException E) {
        E.printStackTrace();
      }
      String encodedString = Base64.getEncoder().encodeToString(fileContent);

//       String hash = montaMD5Hash(fileContent, encodedString);

      System.out.println(encodedString);
//       System.out.println(hash);

    }
  }

  private static String stringHexa(byte[] bytes) {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
      int parteBaixa = bytes[i] & 0xf;
      if (parteAlta == 0)
        s.append('0');
      s.append(Integer.toHexString(parteAlta | parteBaixa));
    }
    return s.toString();
  }

  public static String montaMD5Hash(byte[] fileContent, String encodedString) {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException E) {
      E.printStackTrace();
    }
    md.update(fileContent);
    byte[] hashMd5 = md.digest();

    String hash = stringHexa(hashMd5);

    String _rImportacao = "<j59:fncGDImportaArquivos>\r\n" + "      <j59:NrInst>1163</j59:NrInst>\r\n"
        + "      <j59:IdAcao>1</j59:IdAcao>\r\n" + "      <j59:CdTpDoc>1</j59:CdTpDoc>\r\n"
        + "      <j59:NrArea>1</j59:NrArea>\r\n" + "      <j59:NrPasta>1</j59:NrPasta>\r\n"
        + "      <j59:DtDoc>20190627</j59:DtDoc>\r\n" + "      <j59:DtDig>20190627</j59:DtDig>\r\n"
        + "      <j59:DtValid/>\r\n" + "      <j59:DtPrvExp/>\r\n" + "      <j59:CdOrig>1</j59:CdOrig>\r\n"
        + "      <j59:IdOCR>10</j59:IdOCR>\r\n" + "      <j59:NrVrsTer>1</j59:NrVrsTer>\r\n"
        + "      <j59:IdIdioma>1</j59:IdIdioma>\r\n" + "      <j59:NmLoginAutor/>\r\n"
        + "      <j59:NmLogin>softpar</j59:NmLogin>\r\n" + "      <j59:DsObserv>teste observa��o</j59:DsObserv>\r\n"
        + "      <j59:DsDoc>desc documento</j59:DsDoc>\r\n" + "      <j59:Arquivo>" + encodedString
        + "</j59:Arquivo>\r\n" + "      <j59:NmArq>teste.txt</j59:NmArq>\r\n" + "      <j59:DsHash>" + hash
        + "</j59:DsHash>\r\n" + "      <j59:NrNdu></j59:NrNdu>\r\n" + "      <j59:NrArq></j59:NrArq>\r\n"
        + "      <j59:ListaCampos>\r\n" + "        <xsd:campo>5</xsd:campo>\r\n"
        + "        <xsd:valor>teste campo</xsd:valor>\r\n" + "      </j59:ListaCampos>\r\n"
        + "    </j59:fncGDImportaArquivos>";

    return hash;
  }

}
