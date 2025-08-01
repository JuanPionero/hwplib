package com.agadasom.hwplib.reader;

import com.agadasom.hwplib.object.HWPFile;
import com.agadasom.hwplib.object.docinfo.BinData;
import com.agadasom.hwplib.object.docinfo.bindata.BinDataCompress;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.object.fileheader.FileVersion;
import ext.org.apache.poi.hpsf.HwpPropertySet;
import ext.org.apache.poi.hpsf.HwpSummaryInformation;
import org.apache.poi.hpsf.PropertySet;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import com.agadasom.hwplib.reader.bodytext.ForParagraphList;
import com.agadasom.hwplib.reader.bodytext.ForSection;
import com.agadasom.hwplib.reader.bodytext.memo.ForMemo;
import com.agadasom.hwplib.reader.docinfo.ForDocInfo;
import com.agadasom.hwplib.tool.textextractor.TextExtractMethod;
import com.agadasom.hwplib.tool.textextractor.TextExtractorListener;
import com.agadasom.hwplib.util.compoundFile.reader.CompoundFileReader;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import static com.agadasom.hwplib.Const.DOCUMENT_SUMMARY_INFORMATION_ID;

/**
 * 한글 파일을 읽기 위한 객체
 *
 * @author neolord
 */
public class HWPReader {
    /**
     * hwp 파일을 읽는다.
     *
     * @param filepath hwp파일의 경로
     * @return HWPFile 객체
     * @throws Exception
     */
    public static HWPFile fromFile(String filepath) throws Exception {
        return fromInputStream(new FileInputStream(filepath));
    }

    /**
     * hwp 파일을 읽는다.
     *
     * @param file hwp파일
     * @return HWPFile 객체
     * @throws Exception
     */
    public static HWPFile fromFile(File file) throws Exception {
        HWPReader r = new HWPReader();
        r.hwpFile = new HWPFile();
        r.cfr = new CompoundFileReader(file);

        r.fileHeader();
        if (r.hasPassword()) {
            throw new Exception("Files with passwords are not supported.");
        }

        r.docInfo();
        r.bodyText();
        r.binData();
        r.summaryInformation();

        r.cfr.close();
        return r.hwpFile;
    }

    /**
     * hwp 파일을 읽는다.
     *
     * @param url hwp파일의 경로
     * @return HWPFile 객체
     * @throws Exception
     */
    public static HWPFile fromURL(String url) throws Exception {
        return fromInputStream(new URL(url).openStream());
    }

    /**
     * hwp 파일을 읽는다.
     *
     * @param is hwp파일을 가리키는 Input Stream ㅒ객체
     * @return HWPFile 객체
     * @throws Exception
     */
    public static HWPFile fromInputStream(InputStream is) throws Exception {
        HWPReader r = new HWPReader();
        r.hwpFile = new HWPFile();
        r.cfr = new CompoundFileReader(is);

        r.fileHeader();
        if (r.hasPassword()) {
            throw new Exception("Files with passwords are not supported.");
        }

        r.docInfo();
        r.bodyText();
        r.binData();
        r.summaryInformation();
        r.scripts();

        r.cfr.close();
        return r.hwpFile;
    }

    public static HWPFile fromBase64String(String base64) throws Exception {
        byte[] binary = DatatypeConverter.parseBase64Binary(base64);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(binary);
        return HWPReader.fromInputStream(byteArrayInputStream);
    }

    /**
     * HWP파일을 나타내는 객체
     */
    private HWPFile hwpFile;
    /**
     * MS Compound 파일을 읽기 위한 리더 객체
     */
    private CompoundFileReader cfr;

    /**
     * 생성자
     */
    private HWPReader() {
    }

    /**
     * FileHeader 스트림을 읽는다.
     *
     * @throws Exception
     */
    private void fileHeader() throws Exception {
        StreamReader sr = cfr.getChildStreamReader("FileHeader", false, null);
        ForFileHeader.read(hwpFile.getFileHeader(), sr);
        sr.close();
    }

    /**
     * 암호화된 파일인지 여부를 반환한다.
     *
     * @return 암호화된 파일인지 여부
     */
    private boolean hasPassword() {
        return hwpFile.getFileHeader().hasPassword();
    }

    /**
     * 배포용 문서 파일인지 여부를 반환한다.
     *
     * @return 암호화된 파일인지 여부
     */
    private boolean isDistribution() {
        return hwpFile.getFileHeader().isDistribution();
    }

    /**
     * DocInfo 스트림을 읽는다.
     *
     * @throws Exception
     */
    private void docInfo() throws Exception {
        StreamReader sr = cfr.getChildStreamReader("DocInfo", isCompressed(), getVersion());
        new ForDocInfo().read(hwpFile.getDocInfo(), sr);
        sr.close();
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
     * 파일의 버전을 반환한다.
     *
     * @return 파일의 버전
     */
    private FileVersion getVersion() {
        return hwpFile.getFileHeader().getVersion();
    }

    /**
     * BodyText 스토리지를 읽는다.
     *
     * @throws Exception
     */
    private void bodyText() throws Exception {
        if (!isDistribution()) {
            cfr.moveChildStorage("BodyText");
        } else {
            cfr.moveChildStorage("ViewText");
        }

        int sectionCount = hwpFile.getDocInfo().getDocumentProperties().getSectionCount();
        for (int index = 0; index < sectionCount; index++) {
            section(index);
        }

        cfr.moveParentStorage();
    }

    /**
     * Section 스트림을 읽는다.
     *
     * @param index 섹션 인덱스
     * @throws Exception
     */
    private void section(int index) throws Exception {
        StreamReader sr = streamReader("Section" + index);
        sr.setDocInfo(hwpFile.getDocInfo());
        ForSection.read(hwpFile.getBodyText().addNewSection(), sr);
        if (isLastSection(index)) {
            memo(sr);
        }
        sr.close();
    }

    private StreamReader streamReader(String name) throws Exception {
        if (!isDistribution()) {
            return cfr.getChildStreamReader(name, isCompressed(), getVersion());
        } else {
            return cfr.getChildStreamReaderForDistribution(name, isCompressed(), getVersion());
        }
    }

    private boolean isLastSection(int index) {
        return index + 1 == hwpFile.getDocInfo().getDocumentProperties().getSectionCount();
    }

    private void memo(StreamReader sr) throws Exception {
        while (!sr.isEndOfStream()) {
            if (sr.isImmediatelyAfterReadingHeader() == false) {
                sr.readRecordHeader();
            }

            if (sr.getCurrentRecordHeader().getTagID() == HWPTag.MEMO_LIST) {
                ForMemo.read(hwpFile.getBodyText().addNewMemo(), sr);
            }
        }
    }

    /**
     * BinData 스토리지를 읽는다.
     *
     * @throws Exception
     */
    private void binData() throws Exception {
        if (cfr.isChildStorage("BinData")) {
            cfr.moveChildStorage("BinData");

            Set<String> ss = cfr.listChildNames();
            Iterator<String> it = ss.iterator();
            while (it.hasNext()) {
                String name = it.next();
                int id = nameToID(name);
                BinDataCompress compressMethod = getCompressMethod(id);
                hwpFile.getBinData().addNewEmbeddedBinaryData(name, readEmbeddedBinaryData(name, compressMethod),
                        compressMethod);
            }

            cfr.moveParentStorage();
        }
    }

    private int nameToID(String name) {
        String id = name.substring(3, 7);
        return Integer.parseInt(id, 16);
    }

    private BinDataCompress getCompressMethod(int id) {
        BinData binData;
        try {
            binData = hwpFile.getDocInfo().getBinDataList().get(id - 1);
        } catch (Exception e) {
            binData = null;
        }
        if (binData != null) {
            return binData.getProperty().getCompress();
        }
        return BinDataCompress.ByStorageDefault;
    }

    /**
     * BinData 스토리지 아래에 스트림을 읽는다.
     *
     * @param name           스트림 이름
     * @param compressMethod 압축 방법
     * @return 스트림에 저장된 데이타
     * @throws Exception
     */
    private byte[] readEmbeddedBinaryData(String name, BinDataCompress compressMethod) throws Exception {
        StreamReader sr = cfr.getChildStreamReader(name, isCompressBinData(compressMethod), null);
        byte[] binaryData = new byte[(int) sr.getSize()];
        sr.readBytes(binaryData);
        sr.close();
        return binaryData;
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

    private void summaryInformation() throws Exception {
        DocumentInputStream dis ;
        try {
            dis = cfr.getChildInputStream("\u0005HwpSummaryInformation");
            if (dis != null) {
                HwpPropertySet propertySet = new HwpPropertySet(dis);
                hwpFile.setSummaryInformation(new HwpSummaryInformation(propertySet));
                dis.close();
            } else {
                System.out.println("HwpSummaryInformation is Empty");
            }
        } catch (FileNotFoundException e) {
            dis = null;
        }
    }

    private void scripts() throws Exception {
        if (cfr.isChildStorage("Scripts")) {
            cfr.moveChildStorage("Scripts");

            {
                StreamReader sr = streamReader("DefaultJScript");
                byte[] data = new byte[(int) sr.getSize()];
                sr.readBytes(data);
                sr.close();
                hwpFile.getScripts().setDefaultJScript(data);
            }

            {
                StreamReader sr = streamReader("JScriptVersion");
                byte[] data = new byte[(int) sr.getSize()];
                sr.readBytes(data);
                sr.close();
                hwpFile.getScripts().setJScriptVersion(data);
            }

            cfr.moveParentStorage();
        }
    }

    /**
     * 텍스트를 추출하기 위해 hwp 파일을 읽는다.
     *
     * @param filepath hwp파일의 경로
     * @param listener 텍스트 추출 리스너
     * @param tem      추출 방법
     * @throws Exception
     */
    public static void forExtractText(String filepath, TextExtractorListener listener, TextExtractMethod tem)
            throws Exception {
        forExtractText(new FileInputStream(filepath), listener, tem);
    }

    /**
     * 텍스트를 추출하기 위해 hwp 파일을 읽는다.
     *
     * @param is       hwp파일을 가리키는 Input Stream ㅒ객체
     * @param listener 텍스트 추출 리스너
     * @param tem      추출 방법
     * @throws Exception
     */
    private static void forExtractText(FileInputStream is, TextExtractorListener listener, TextExtractMethod tem)
            throws Exception {
        HWPReader r = new HWPReader();
        r.hwpFile = new HWPFile();
        r.cfr = new CompoundFileReader(is);

        r.fileHeader();
        if (r.hasPassword()) {
            throw new Exception("Files with passwords are not supported.");
        }
        if (r.isDistribution()) {
            throw new Exception("Distribution Files are not supported.");
        }

        r.docInfo();
        r.extractBodyText(listener, tem);

        r.cfr.close();
    }

    /**
     * 텍스트를 추출하기 위해 hwp 파일의 bodyText 부분을 읽는다.
     *
     * @param listener 텍스트 추출 리스너
     * @param tem      추출 방법
     * @throws Exception
     */
    private void extractBodyText(TextExtractorListener listener, TextExtractMethod tem) throws Exception {
        cfr.moveChildStorage("BodyText");

        int sectionCount = hwpFile.getDocInfo().getDocumentProperties().getSectionCount();
        for (int index = 0; index < sectionCount; index++) {
            extractSectionText(index, listener, tem);
        }

        cfr.moveParentStorage();
    }

    /**
     * 텍스트를 추출하기 위해 hwp 파일의 섹션 부분을 읽는다.
     *
     * @param sectionIndex 섹션 인덱스
     * @param listener     텍스트 추출 리스너
     * @param tem          추출 방법
     * @throws Exception
     */
    private void extractSectionText(int sectionIndex, TextExtractorListener listener, TextExtractMethod tem)
            throws Exception {
        StreamReader sr = cfr.getChildStreamReader("Section" + sectionIndex, isCompressed(), getVersion());
        sr.setDocInfo(hwpFile.getDocInfo());
        ForParagraphList.extractText(sr, listener, tem);
        sr.close();
    }
}
