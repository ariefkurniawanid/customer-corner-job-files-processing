package id.co.chubb.fileprocess.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class Base64Utils {

    public static String convertPdf(Path pdfPath) throws Exception {
        byte[] pdfBytes = Files.readAllBytes(pdfPath);
        return Base64.getEncoder().encodeToString(pdfBytes);
    }
}
