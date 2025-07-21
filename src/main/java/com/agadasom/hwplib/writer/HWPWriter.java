package com.agadasom.hwplib.writer;

import com.agadasom.hwplib.object.HWPFile;
import com.agadasom.hwplib.object.bindata.EmbeddedBinaryData;
import com.agadasom.hwplib.object.bodytext.Section;
import com.agadasom.hwplib.object.bodytext.paragraph.memo.Memo;
import com.agadasom.hwplib.object.docinfo.bindata.BinDataCompress;
import com.agadasom.hwplib.object.fileheader.FileVersion;
import org.apache.poi.hpsf.WritingNotSupportedException;
import com.agadasom.hwplib.util.compoundFile.writer.CompoundFileWriter;
import com.agadasom.hwplib.util.compoundFile.writer.StreamWriter;
import com.agadasom.hwplib.writer.autosetter.AutoSetter;
import com.agadasom.hwplib.writer.autosetter.InstanceID;
import com.agadasom.hwplib.writer.bodytext.ForSection;
import com.agadasom.hwplib.writer.bodytext.paragraph.memo.ForMemo;
import com.agadasom.hwplib.writer.docinfo.ForDocInfo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 한글 파일을 쓰기 위한 객체
 *
 * @author neolord
 */
public class HWPWriter {
    /**
     * 한글 파일 객체를 파일로 쓴다.
     *
     * @param hwpFile  한글 파일 객체
     * @param filepath 파일 경로
     * @throws Exception
     */
    public static void toFile(HWPFile hwpFile, String filepath)
            throws Exception {
        if (hwpFile.getFileHeader().hasPassword()) {
            throw new Exception("Files with passwords are not supported.");
        }

        HWPWriter w = new HWPWriter(hwpFile);
        w.autoSet();
        w.fileHeader();
        w.docInfo();
        w.bodyText();
        w.binData();
        w.summaryInformation();
        w.scripts();
        w.docOptions();
        w.writeAndClose(filepath);
    }

    /**
     * 한글 파일 객체를 파일로 쓴다.
     *
     * @param hwpFile 한글 파일 객체
     * @param os      출력 스트림
     * @throws Exception
     */
    public static void toStream(HWPFile hwpFile, OutputStream os) throws Exception {
        if (hwpFile.getFileHeader().hasPassword()) {
            throw new Exception("Files with passwords are not supported.");
        }

        HWPWriter w = new HWPWriter(hwpFile);
        w.autoSet();
        w.fileHeader();
        w.docInfo();
        w.bodyText();
        w.binData();
        w.summaryInformation();
        w.scripts();
        w.docOptions();
        w.writeAndClose(os);
    }

    /**
     * 한긆 파일
     */
    private HWPFile hwpFile;
    /**
     * MS Compound 파일을 읽기 위한 라이터 객체
     */
    private CompoundFileWriter cfw;

    /**
     * 생성자
     *
     * @param hwpFile 한글 파일
     */
    private HWPWriter(HWPFile hwpFile) {
        this.hwpFile = hwpFile;
        cfw = new CompoundFileWriter();
    }

    /**
     * 파일을 쓰기 전에 자동으로 설정할 수 있는 값들을 설정한다.
     */
    private void autoSet() {
        InstanceID iid = new InstanceID();
        AutoSetter.autoSet(hwpFile, iid);
    }

    /**
     * FileHeader 스트림을 쓴다.
     *
     * @throws IOException
     */
    private void fileHeader() throws IOException {
        StreamWriter sw = cfw.openCurrentStream("FileHeader", false, getVersion());
        ForFileHeader.write(hwpFile.getFileHeader(), sw);
        cfw.closeCurrentStream();
    }

    /**
     * 파일 버전을 반환한다.
     *
     * @return 파일 버전
     */
    private FileVersion getVersion() {
        return hwpFile.getFileHeader().getVersion();
    }

    /**
     * DocInfo 스트림을 쓴다.
     *
     * @throws Exception
     */
    private void docInfo() throws Exception {
        StreamWriter sw = cfw.openCurrentStream("DocInfo", isCompressed(), getVersion());
        sw.setDocInfo(hwpFile.getDocInfo());
        new ForDocInfo().write(hwpFile.getDocInfo(), sw);
        cfw.closeCurrentStream();
    }

    /**
     * 압축된 파일인지 여부를 반환한다.
     *
     * @return 압축된 파일인지 여부
     */
    private boolean isCompressed() {
        return hwpFile.getFileHeader().isCompressed();
    }

    /**
     * BodyText 스토리지를 쓴다.
     *
     * @throws Exception
     */
    private void bodyText() throws Exception {
        cfw.openCurrentStorage("BodyText");

        int index = 0;
        for (Section s : hwpFile.getBodyText().getSectionList()) {
            section(index, s);
            index++;
        }

        cfw.closeCurrentStorage();
    }

    /**
     * Section 스트림을 쓴다.
     *
     * @param index 섹션 인덱스
     * @param s     구역 객체
     * @throws Exception
     */
    private void section(int index, Section s) throws Exception {
        StreamWriter sw = cfw.openCurrentStream("Section" + index, isCompressed(), getVersion());
        sw.setDocInfo(hwpFile.getDocInfo());

        ForSection.write(s, sw);
        if (isLastSection(index) && hwpFile.getBodyText().getMemoList() != null) {
            for (Memo memo : hwpFile.getBodyText().getMemoList()) {
                ForMemo.write(memo, sw);
            }
        }

        cfw.closeCurrentStream();
    }

    private boolean isLastSection(int index) {
        return index + 1 == hwpFile.getBodyText().getSectionList().size();
    }

    /**
     * BinData 스토리지를 쓴다.
     *
     * @throws IOException
     */
    private void binData() throws IOException {
        if (hasBinData()) {
            cfw.openCurrentStorage("BinData");

            for (EmbeddedBinaryData ebd : hwpFile.getBinData()
                    .getEmbeddedBinaryDataList()) {
                embeddedBinaryData(ebd);
            }

            cfw.closeCurrentStorage();
        }
    }

    /**
     * 첨부된 바이너리 데이터가 있는지 여부를 반환한다.
     *
     * @return 첨부된 바이너리 데이터가 있는지 여부
     */
    private boolean hasBinData() {
        return hwpFile.getBinData().getEmbeddedBinaryDataList().size() > 0;
    }

    /**
     * 첨부된 바이너리 데이터를 쓴다.
     *
     * @param ebd 첨부된 바이너리 데이터에 대한 정보
     * @throws IOException
     */
    private void embeddedBinaryData(EmbeddedBinaryData ebd) throws IOException {
        StreamWriter sw = cfw.openCurrentStream(ebd.getName(),
                isCompressBinData(ebd.getCompressMethod()),
                getVersion());
        sw.writeBytes(ebd.getData());
        cfw.closeCurrentStream();
    }

    /**
     * BinData의 압축 여부를 반환한다.
     *
     * @param compressMethod 압축 방법
     * @return BinData의 압축 여부
     */
    private boolean isCompressBinData(BinDataCompress compressMethod) {
        switch (compressMethod) {
            case ByStorageDefault:
                return isCompressed();
            case Compress:
                return true;
            case NoCompress:
                return false;
        }
        return false;
    }

    private void summaryInformation() throws WritingNotSupportedException, IOException {
        if (hwpFile.getSummaryInformation() != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            hwpFile.getSummaryInformation().write(bos);
            if (bos.size() > 0) {
                cfw.saveToStream("\u0005HwpSummaryInformation", new ByteArrayInputStream(bos.toByteArray()));
            }
        }
    }

    private void scripts() throws IOException {
        cfw.openCurrentStorage("Scripts");

        if (hwpFile.getScripts().getDefaultJScript() != null) {
            StreamWriter sw = cfw.openCurrentStream("DefaultJScript", isCompressed(), getVersion());
            sw.writeBytes(hwpFile.getScripts().getDefaultJScript());
            cfw.closeCurrentStream();
        }

        if (hwpFile.getScripts().getJScriptVersion() != null) {
            StreamWriter sw = cfw.openCurrentStream("JScriptVersion", isCompressed(), getVersion());
            sw.writeBytes(hwpFile.getScripts().getJScriptVersion());
            cfw.closeCurrentStream();
        }

        cfw.closeCurrentStorage();
    }

    private void docOptions() throws IOException {
        cfw.openCurrentStorage("DocOptions");
        cfw.closeCurrentStorage();
    }

    /**
     * 파일을 쓰고 닫는다.
     *
     * @param filepath 파일 경로
     * @throws IOException
     */
    private void writeAndClose(String filepath) throws IOException {
        cfw.write(filepath);
        cfw.close();
    }

    /**
     * 출력 스트림에 쓰고 닫는다.
     *
     * @param os 출력 스트림
     * @throws IOException
     */
    private void writeAndClose(OutputStream os) throws IOException {
        cfw.write(os);
        cfw.close();
    }
}
