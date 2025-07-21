package com.agadasom.hwplib.tool.blankfilemaker;

import com.agadasom.hwplib.object.HWPFile;
import com.agadasom.hwplib.object.bodytext.Section;
import com.agadasom.hwplib.object.docinfo.DocInfo;
import com.agadasom.hwplib.object.docinfo.DocumentProperties;
import com.agadasom.hwplib.object.docinfo.LayoutCompatibility;
import com.agadasom.hwplib.object.docinfo.compatibledocument.CompatibleDocumentSort;
import com.agadasom.hwplib.object.docinfo.documentproperties.CaretPosition;
import com.agadasom.hwplib.object.docinfo.documentproperties.StartNumber;
import com.agadasom.hwplib.object.fileheader.FileHeader;

public class BlankFileMaker {
    public static HWPFile make() {
        HWPFile hwpFile = new HWPFile();
        setFileHeader(hwpFile.getFileHeader());

        DocInfo docInfo = hwpFile.getDocInfo();
        setDocumentProperties(docInfo.getDocumentProperties());

        FaceNameAdder.add(docInfo);
        BorderFillAdder.add(docInfo);
        CharShapeAdder.add(docInfo);
        TabDefAdder.add(docInfo);
        NumberingAdder.add(docInfo);
        ParaShapeAdder.add(docInfo);
        StyleAdder.add(docInfo);
        compatibleDocument(docInfo);
        layoutCompatibility(docInfo);

        Section section = hwpFile.getBodyText().addNewSection();
        EmptyParagraphAdder.add(section);

        setScript(hwpFile);
        return hwpFile;
    }

    private static void setFileHeader(FileHeader fileHeader) {
        fileHeader.getVersion().setVersion((short) 5, (short) 0, (short) 3, (short) 4);
        fileHeader.setCompressed(true);
        fileHeader.setHasPassword(false);
        fileHeader.setDistribution(false);
        fileHeader.setSaveScript(false);
        fileHeader.setDRMDocument(false);
        fileHeader.setHasXMLTemplate(false);
        fileHeader.setHasDocumentHistory(false);
        fileHeader.setHasSignature(false);
        fileHeader.setEncryptPublicCertification(false);
        fileHeader.setSavePrepareSignature(false);
        fileHeader.setPublicCertificationDRMDocument(false);
        fileHeader.setCCLDocument(false);
    }

    private static void setDocumentProperties(DocumentProperties documentProperties) {
        documentProperties.setSectionCount(1);

        StartNumber startNumber = documentProperties.getStartNumber();
        startNumber.setPage(1);
        startNumber.setFootnote(1);
        startNumber.setEndnote(1);
        startNumber.setPicture(1);
        startNumber.setTable(1);
        startNumber.setEquation(1);

        CaretPosition caretPosition = documentProperties.getCaretPosition();
        caretPosition.setListID(0);
        caretPosition.setParagraphID(0);
        caretPosition.setPositionInParagraph(0);
    }

    private static void compatibleDocument(DocInfo docInfo) {
        docInfo.createCompatibleDocument();
        docInfo.getCompatibleDocument().setTargetProgream(CompatibleDocumentSort.HWPCurrent);
    }

    private static void layoutCompatibility(DocInfo docInfo) {
        docInfo.createLayoutCompatibility();
        LayoutCompatibility layoutCompatibility = docInfo.getLayoutCompatibility();
        layoutCompatibility.setLetterLevelFormat(0);
        layoutCompatibility.setParagraphLevelFormat(0);
        layoutCompatibility.setSectionLevelFormat(0);
        layoutCompatibility.setObjectLevelFormat(0);
        layoutCompatibility.setFieldLevelFormat(0);
    }

    private static void setScript(HWPFile hwpFile) {
        byte[] compressed_jsVersion = new byte[] { 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
        hwpFile.getScripts().setJScriptVersion(compressed_jsVersion);

        byte[] compressed_defaultJScript = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF };
        hwpFile.getScripts().setDefaultJScript(compressed_defaultJScript);
    }
}
