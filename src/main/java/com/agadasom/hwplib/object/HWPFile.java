package com.agadasom.hwplib.object;

import com.agadasom.hwplib.object.bindata.BinData;
import com.agadasom.hwplib.object.bodytext.BodyText;
import com.agadasom.hwplib.object.docinfo.DocInfo;
import com.agadasom.hwplib.object.fileheader.FileHeader;
import ext.org.apache.poi.hpsf.HwpSummaryInformation;
import org.apache.poi.hpsf.*;

import java.io.IOException;

/**
 * HWP File를 나타내는 객체
 *
 * @author neolord
 */
public class HWPFile {
    /**
     * 파일 인식 정보를 나타내는 객체. "FileHeader" stream에 저장된다.
     */
    private FileHeader fileHeader;
    /**
     * 문서 정보를 나타내는 객체. "DocInfo" stream에 저장된다.
     */
    private DocInfo docInfo;
    /**
     * 본문을 나타내는 객체. "BodyText" storage에 저장된다.
     */
    private BodyText bodyText;
    /**
     * 바이너리 데이터를 나타내는 객체. "BinData" storage에 저장된다.
     */
    private BinData binData;
    private HwpSummaryInformation summaryInformation;
    private Scripts scripts;

    /**
     * 생성자
     */
    public HWPFile() {
        fileHeader = new FileHeader();
        docInfo = new DocInfo();
        bodyText = new BodyText();
        binData = new BinData();
        scripts = new Scripts();
    }

    /**
     * 파일 인식 정보를 나타내는 객체를 반환한다.
     *
     * @return 파일 인식 정보를 나타내는 객체
     */
    public FileHeader getFileHeader() {
        return fileHeader;
    }

    /**
     * 문서 정보를 나타내는 객체를 반환한다.
     *
     * @return 문서 정보를 나타내는 객체
     */
    public DocInfo getDocInfo() {
        return docInfo;
    }

    /**
     * 본문을 나타내는 객체를 반환한다.
     *
     * @return 본문을 나타내는 객체
     */
    public BodyText getBodyText() {
        return bodyText;
    }

    /**
     * 바이너리 데이터를 나타내는 객체를 반환한다.
     *
     * @return 바이너리 데이터를 나타내는 객체
     */
    public BinData getBinData() {
        return binData;
    }

    public HwpSummaryInformation getSummaryInformation() {
        return summaryInformation;
    }

    public void setSummaryInformation(HwpSummaryInformation summaryInformation) {
        this.summaryInformation = summaryInformation;
    }

    public Scripts getScripts() {
        return scripts;
    }

    public HWPFile clone(boolean deepCopyImage) {
        HWPFile cloned = new HWPFile();
        cloned.copy(this, deepCopyImage);
        return cloned;
    }

    public void copy(HWPFile from, boolean deepCopyImage) {
        fileHeader.copy(from.fileHeader);
        docInfo.copy(from.docInfo);
        bodyText.copy(from.bodyText);
        binData.copy(from.binData, deepCopyImage);
        if (from.summaryInformation != null) {
            copySummaryInformation(from.summaryInformation);
        }
        scripts.copy(from.scripts);
    }

    private void copySummaryInformation(HwpSummaryInformation from) {
        try {
            summaryInformation = new HwpSummaryInformation(from.toInputStream());
        } catch (WritingNotSupportedException | IOException | NoPropertySetStreamException e) {
            throw new RuntimeException(e);
        }
    }
}
