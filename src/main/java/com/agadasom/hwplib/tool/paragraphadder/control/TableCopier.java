package com.agadasom.hwplib.tool.paragraphadder.control;

import com.agadasom.hwplib.object.bodytext.control.ControlTable;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderGso;
import com.agadasom.hwplib.object.bodytext.control.table.*;
import com.agadasom.hwplib.tool.paragraphadder.ParagraphCopier;
import com.agadasom.hwplib.tool.paragraphadder.docinfo.DocInfoAdder;

public class TableCopier {
    public static void copy(ControlTable source, ControlTable target, DocInfoAdder docInfoAdder) {
        CtrlHeaderGso sourceH = source.getHeader();
        CtrlHeaderGso targetH = target.getHeader();
        targetH.copy(sourceH);

        CtrlDataCopier.copy(source, target, docInfoAdder);
        caption(source, target, docInfoAdder);
        table(source.getTable(), target.getTable(), docInfoAdder);
        rows(source, target, docInfoAdder);
    }

    private static void caption(ControlTable source, ControlTable target, DocInfoAdder docInfoAdder) {
        if (source.getCaption() != null) {
            target.createCaption();
            CaptionCopier.copy(source.getCaption(), target.getCaption(), docInfoAdder);
        } else {
            target.deleteCaption();
        }
    }

    private static void table(Table source, Table target, DocInfoAdder docInfoAdder) {
        target.getProperty().setValue(source.getProperty().getValue());
        target.setRowCount(source.getRowCount());
        target.setColumnCount(source.getColumnCount());
        target.setCellSpacing(source.getCellSpacing());
        target.setLeftInnerMargin(source.getLeftInnerMargin());
        target.setRightInnerMargin(source.getRightInnerMargin());
        target.setTopInnerMargin(source.getTopInnerMargin());
        target.setBottomInnerMargin(source.getBottomInnerMargin());
        for (Integer cellCountOfRow : source.getCellCountOfRowList()) {
            target.addCellCountOfRow(cellCountOfRow);
        }
        target.setBorderFillId((docInfoAdder == null) ? source.getBorderFillId()
                : docInfoAdder.forBorderFill().processById(source.getBorderFillId()));
        for (ZoneInfo zoneInfo : source.getZoneInfoList()) {
            zoneInfo(zoneInfo, target.addNewZoneInfo(), docInfoAdder);
        }
    }

    private static void zoneInfo(ZoneInfo source, ZoneInfo target, DocInfoAdder docInfoAdder) {
        target.setStartColumn(source.getStartColumn());
        target.setStartRow(source.getStartRow());
        target.setEndColumn(source.getEndColumn());
        target.setEndRow(source.getEndRow());
        target.setBorderFillId((docInfoAdder == null) ? source.getBorderFillId()
                : docInfoAdder.forBorderFill().processById(source.getBorderFillId()));
    }

    private static void rows(ControlTable source, ControlTable target, DocInfoAdder docInfoAdder) {
        for (Row row : source.getRowList()) {
            row(row, target.addNewRow(), docInfoAdder);
        }
    }

    private static void row(Row source, Row target, DocInfoAdder docInfoAdder) {
        for (Cell cell : source.getCellList()) {
            cell(cell, target.addNewCell(), docInfoAdder);
        }
    }

    private static void cell(Cell source, Cell target, DocInfoAdder docInfoAdder) {
        listHeader(source.getListHeader(), target.getListHeader(), docInfoAdder);
        ParagraphCopier.listCopy(source.getParagraphList(), target.getParagraphList(), docInfoAdder);
    }

    private static void listHeader(ListHeaderForCell source, ListHeaderForCell target, DocInfoAdder docInfoAdder) {
        target.setParaCount(source.getParaCount());
        target.getProperty().setValue(source.getProperty().getValue());
        target.setColIndex(source.getColIndex());
        target.setRowIndex(source.getRowIndex());
        target.setColSpan(source.getColSpan());
        target.setRowSpan(source.getRowSpan());
        target.setWidth(source.getWidth());
        target.setHeight(source.getHeight());
        target.setLeftMargin(source.getLeftMargin());
        target.setRightMargin(source.getRightMargin());
        target.setTopMargin(source.getTopMargin());
        target.setBottomMargin(source.getBottomMargin());
        target.setBorderFillId((docInfoAdder == null) ? source.getBorderFillId()
                : docInfoAdder.forBorderFill().processById(source.getBorderFillId()));
        target.setTextWidth(source.getTextWidth());
        target.setFieldName(source.getFieldName());
    }
}
