package sample;

import com.agadasom.hwplib.object.HWPFile;
import com.agadasom.hwplib.object.bodytext.control.Control;
import com.agadasom.hwplib.object.bodytext.control.ControlTable;
import com.agadasom.hwplib.object.bodytext.control.ControlType;
import com.agadasom.hwplib.reader.HWPReader;
import com.agadasom.hwplib.tool.TableCellMerger;
import com.agadasom.hwplib.writer.HWPWriter;

import java.io.File;

/**
 * 표의 셀을 병합하는 샘플 프로그램.
 */
public class Merging_Cell {
    public static void main(String[] args) throws Exception {
        String filename = "sample_hwp" + File.separator + "merging-cell.hwp";

        HWPFile hwpFile = HWPReader.fromFile(filename);
        if (hwpFile != null) {
            Control control = hwpFile.getBodyText().getSectionList().get(0).getParagraph(0).getControlList().get(2);
            if (control.getType() == ControlType.Table) {
                ControlTable table = (ControlTable) control;
                TableCellMerger.mergeCell(table, 2, 2, 4, 3);
            }

            String writePath = "sample_hwp" + File.separator + "result-merging-cell.hwp";
            HWPWriter.toFile(hwpFile, writePath);
        }
    }
}
