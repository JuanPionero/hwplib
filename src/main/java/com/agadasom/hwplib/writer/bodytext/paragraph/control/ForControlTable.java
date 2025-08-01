package com.agadasom.hwplib.writer.bodytext.paragraph.control;

import com.agadasom.hwplib.object.bodytext.control.ControlTable;
import com.agadasom.hwplib.object.bodytext.control.table.Cell;
import com.agadasom.hwplib.object.bodytext.control.table.Row;
import com.agadasom.hwplib.util.compoundFile.writer.StreamWriter;
import com.agadasom.hwplib.writer.bodytext.paragraph.control.gso.part.ForCaption;
import com.agadasom.hwplib.writer.bodytext.paragraph.control.gso.part.ForCtrlHeaderGso;
import com.agadasom.hwplib.writer.bodytext.paragraph.control.tbl.ForCell;
import com.agadasom.hwplib.writer.bodytext.paragraph.control.tbl.ForTable;

/**
 * 표 컨트롤을 쓰기 위한 객체
 *
 * @author neolord
 */
public class ForControlTable {
    /**
     * 표 컨트롤을 쓴다.
     *
     * @param t  표 컨트롤
     * @param sw 스트림 라이터
     * @throws Exception
     */
    public static void write(ControlTable t, StreamWriter sw) throws Exception {
        ForCtrlHeaderGso.write(t.getHeader(), sw);

        sw.upRecordLevel();

        ForCaption.write(t.getCaption(), sw);
        ForTable.write(t.getTable(), sw);
        rows(t, sw);

        sw.downRecordLevel();
    }

    /**
     * 표 컨트롤에 포함된 셀들을 쓴다.
     *
     * @param t  표 컨트롤
     * @param sw 스트림 라이터
     * @throws Exception
     */
    private static void rows(ControlTable t, StreamWriter sw) throws Exception {
        for (Row r : t.getRowList()) {
            for (Cell c : r.getCellList()) {
                ForCell.write(c, sw);
            }
        }
    }
}
