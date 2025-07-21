package sample;

import com.agadasom.hwplib.object.HWPFile;
import com.agadasom.hwplib.tool.blankfilemaker.BlankFileMaker;
import com.agadasom.hwplib.writer.HWPWriter;

import java.io.File;

public class Making_BlankFile {
    public static void main(String[] args) throws Exception {
        HWPFile hwpFile = BlankFileMaker.make();
        if (hwpFile != null) {
            String writePath = "sample_hwp" + File.separator + "result-making-blankfile.hwp";
            HWPWriter.toFile(hwpFile, writePath);
        }
    }
}
