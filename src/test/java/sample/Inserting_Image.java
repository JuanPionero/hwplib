package sample;

import com.agadasom.hwplib.object.HWPFile;
import com.agadasom.hwplib.object.bodytext.Section;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderGso;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.gso.*;
import com.agadasom.hwplib.object.bodytext.control.gso.ControlRectangle;
import com.agadasom.hwplib.object.bodytext.control.gso.GsoControlType;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.ShapeComponentNormal;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.lineinfo.*;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.shadowinfo.ShadowInfo;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.shadowinfo.ShadowType;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponenteach.ShapeComponentRectangle;
import com.agadasom.hwplib.object.bodytext.paragraph.Paragraph;
import com.agadasom.hwplib.object.docinfo.BinData;
import com.agadasom.hwplib.object.docinfo.bindata.BinDataCompress;
import com.agadasom.hwplib.object.docinfo.bindata.BinDataState;
import com.agadasom.hwplib.object.docinfo.bindata.BinDataType;
import com.agadasom.hwplib.object.docinfo.borderfill.fillinfo.FillInfo;
import com.agadasom.hwplib.object.docinfo.borderfill.fillinfo.ImageFill;
import com.agadasom.hwplib.object.docinfo.borderfill.fillinfo.ImageFillType;
import com.agadasom.hwplib.object.docinfo.borderfill.fillinfo.PictureEffect;
import com.agadasom.hwplib.reader.HWPReader;
import com.agadasom.hwplib.writer.HWPWriter;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 파일에 이미지와 이미지 컨트롤을 삽입하는 샘플 프로그램
 */
public class Inserting_Image {
    public static void main(String[] args) throws Exception {
        String filename = "sample_hwp" + File.separator + "blank.hwp";

        HWPFile hwpFile = HWPReader.fromFile(filename);
        if (hwpFile != null) {
            Inserting_Image tii = new Inserting_Image();
            tii.insertShapeWithImage(hwpFile);

            String writePath = "sample_hwp" + File.separator + "result-inserting-image.hwp";
            HWPWriter.toFile(hwpFile, writePath);
        }
    }

    private final String imageFilePath = "sample_hwp" + File.separator + "image" + File.separator + "sample.jpg";
    private final String imageFileExt = "jpg";
    private final BinDataCompress compressMethod = BinDataCompress.ByStorageDefault;

    private int instanceID = 0x5bb840e1;
    private HWPFile hwpFile;
    private int streamIndex;
    private int binDataID;

    private ControlRectangle rectangle;
    private Rectangle shapePosition = new Rectangle(50, 50, 100, 100);

    private void insertShapeWithImage(HWPFile hwpFile) throws IOException {
        this.hwpFile = hwpFile;

        addBinData();
        binDataID = addBinDataInDocInfo(streamIndex);
        addGsoControl();
    }

    private void addBinData() throws IOException {
        streamIndex = hwpFile.getBinData().getEmbeddedBinaryDataList().size() + 1;
        String streamName = getStreamName();
        byte[] fileBinary = loadFile();

        hwpFile.getBinData().addNewEmbeddedBinaryData(streamName, fileBinary, compressMethod);
    }

    private String getStreamName() {
        return "Bin" + String.format("%04X", streamIndex) + "." + imageFileExt;
    }

    private byte[] loadFile() throws IOException {
        File file = new File(imageFilePath);
        byte[] buffer = new byte[(int) file.length()];
        InputStream ios = null;
        try {
            ios = new FileInputStream(file);
            ios.read(buffer);
        } finally {
            try {
                if (ios != null)
                    ios.close();
            } catch (IOException e) {
            }
        }
        return buffer;
    }

    private int addBinDataInDocInfo(int streamIndex) {
        BinData bd = new BinData();
        bd.getProperty().setType(BinDataType.Embedding);
        bd.getProperty().setCompress(compressMethod);
        bd.getProperty().setState(BinDataState.NotAccess);
        bd.setBinDataID(streamIndex);
        bd.setExtensionForEmbedding(imageFileExt);
        hwpFile.getDocInfo().getBinDataList().add(bd);
        return hwpFile.getDocInfo().getBinDataList().size();
    }

    private void addGsoControl() {
        createRectangleControlAtFirstParagraph();

        setCtrlHeaderGso();
        setShapeComponent();
        setShapeComponentRectangle();
    }

    private void createRectangleControlAtFirstParagraph() {
        Section firstSection = hwpFile.getBodyText().getSectionList().get(0);
        Paragraph firstParagraph = firstSection.getParagraph(0);

        // 문단에서 사각형 컨트롤의 위치를 표현하기 위한 확장 문자를 넣는다.
        firstParagraph.getText().addExtendCharForGSO();

        // 문단에 사각형 컨트롤 추가한다.
        rectangle = (ControlRectangle) firstParagraph.addNewGsoControl(GsoControlType.Rectangle);
    }

    private void setCtrlHeaderGso() {
        CtrlHeaderGso hdr = rectangle.getHeader();
        GsoHeaderProperty prop = hdr.getProperty();
        prop.setLikeWord(false);
        prop.setApplyLineSpace(false);
        prop.setVertRelTo(VertRelTo.Para);
        prop.setVertRelativeArrange(RelativeArrange.TopOrLeft);
        prop.setHorzRelTo(HorzRelTo.Para);
        prop.setHorzRelativeArrange(RelativeArrange.TopOrLeft);
        prop.setVertRelToParaLimit(true);
        prop.setAllowOverlap(true);
        prop.setWidthCriterion(WidthCriterion.Absolute);
        prop.setHeightCriterion(HeightCriterion.Absolute);
        prop.setProtectSize(false);
        prop.setTextFlowMethod(TextFlowMethod.FitWithText);
        prop.setTextHorzArrange(TextHorzArrange.BothSides);
        prop.setObjectNumberSort(ObjectNumberSort.Figure);

        hdr.setyOffset(fromMM(shapePosition.y));
        hdr.setxOffset(fromMM(shapePosition.x));
        hdr.setWidth(fromMM(shapePosition.width));
        hdr.setHeight(fromMM(shapePosition.height));
        hdr.setzOrder(0);
        hdr.setOutterMarginLeft(0);
        hdr.setOutterMarginRight(0);
        hdr.setOutterMarginTop(0);
        hdr.setOutterMarginBottom(0);
        hdr.setInstanceId(instanceID);
        hdr.setPreventPageDivide(false);
        hdr.getExplanation().setBytes(null);
    }

    private int fromMM(int mm) {
        if (mm == 0) {
            return 1;
        }

        return (int) ((double) mm * 72000.0f / 254.0f + 0.5f);
    }

    private void setShapeComponent() {
        ShapeComponentNormal sc = (ShapeComponentNormal) rectangle.getShapeComponent();
        sc.getProperty().setRotateWithImage(true);
        sc.setOffsetX(0);
        sc.setOffsetY(0);
        sc.setGroupingCount(0);
        sc.setLocalFileVersion(1);
        sc.setWidthAtCreate(fromMM(shapePosition.width));
        sc.setHeightAtCreate(fromMM(shapePosition.height));
        sc.setWidthAtCurrent(fromMM(shapePosition.width));
        sc.setHeightAtCurrent(fromMM(shapePosition.height));
        sc.setRotateAngle(0);
        sc.setRotateXCenter(fromMM(shapePosition.width / 2));
        sc.setRotateYCenter(fromMM(shapePosition.height / 2));

        sc.createLineInfo();
        LineInfo li = sc.getLineInfo();
        li.getProperty().setLineEndShape(LineEndShape.Flat);
        li.getProperty().setStartArrowShape(LineArrowShape.None);
        li.getProperty().setStartArrowSize(LineArrowSize.MiddleMiddle);
        li.getProperty().setEndArrowShape(LineArrowShape.None);
        li.getProperty().setEndArrowSize(LineArrowSize.MiddleMiddle);
        li.getProperty().setFillStartArrow(true);
        li.getProperty().setFillEndArrow(true);
        li.getProperty().setLineType(LineType.None);
        li.setOutlineStyle(OutlineStyle.Normal);
        li.setThickness(0);
        li.getColor().setValue(0);

        sc.createFillInfo();
        FillInfo fi = sc.getFillInfo();
        fi.getType().setPatternFill(false);
        fi.getType().setImageFill(true);
        fi.getType().setGradientFill(false);
        fi.createImageFill();
        ImageFill imgF = fi.getImageFill();
        imgF.setImageFillType(ImageFillType.FitSize);
        imgF.getPictureInfo().setBrightness((byte) 0);
        imgF.getPictureInfo().setContrast((byte) 0);
        imgF.getPictureInfo().setEffect(PictureEffect.RealPicture);
        imgF.getPictureInfo().setBinItemID(binDataID);

        sc.createShadowInfo();
        ShadowInfo si = sc.getShadowInfo();
        si.setType(ShadowType.None);
        si.getColor().setValue(0xc4c4c4);
        si.setOffsetX(283);
        si.setOffsetY(283);
        si.setTransparent((short) 0);

        sc.setMatrixsNormal();
    }

    private void setShapeComponentRectangle() {
        ShapeComponentRectangle scr = rectangle.getShapeComponentRectangle();
        scr.setRoundRate((byte) 0);
        scr.setX1(0);
        scr.setY1(0);
        scr.setX2(fromMM(shapePosition.width));
        scr.setY2(0);
        scr.setX3(fromMM(shapePosition.width));
        scr.setY3(fromMM(shapePosition.height));
        scr.setX4(0);
        scr.setY4(fromMM(shapePosition.height));
    }
}
