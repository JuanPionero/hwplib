package com.agadasom.hwplib.object.bodytext.control.ctrlheader;

import com.agadasom.hwplib.object.bodytext.control.ControlType;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.columndefine.ColumnDefineHeaderProperty;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.columndefine.ColumnInfo;
import com.agadasom.hwplib.object.docinfo.borderfill.EachBorder;

import java.util.ArrayList;

/**
 * 단 정의 컨트롤을 위한 컨트롤 헤더 레코드
 *
 * @author neolord
 */
public class CtrlHeaderColumnDefine extends CtrlHeader {
    /**
     * 속성
     */
    private ColumnDefineHeaderProperty property;
    /**
     * 단 사이 간격
     */
    private int gapBetweenColumn;
    /**
     * 속성2(정보 없음)
     */
    private int property2;
    /**
     * 단 정보 리스트
     */
    private ArrayList<ColumnInfo> columnInfoList;
    /**
     * 단 구분선 정보
     */
    private EachBorder divideLine;

    /**
     * 생성자
     */
    public CtrlHeaderColumnDefine() {
        super(ControlType.ColumnDefine.getCtrlId());

        property = new ColumnDefineHeaderProperty();
        columnInfoList = new ArrayList<ColumnInfo>();
        divideLine = new EachBorder();
    }

    /**
     * 단 정의 컨트롤의 속성 객체를 반환한다.
     *
     * @return 단 정의 컨트롤의 속성 객체
     */
    public ColumnDefineHeaderProperty getProperty() {
        return property;
    }

    /**
     * 단 사이 간격을 반환한다.
     *
     * @return 단 사이 간격
     */
    public int getGapBetweenColumn() {
        return gapBetweenColumn;
    }

    /**
     * 단 사이 간격를 설정한다.
     *
     * @param gapBetweenColumn 단 사이 간격
     */
    public void setGapBetweenColumn(int gapBetweenColumn) {
        this.gapBetweenColumn = gapBetweenColumn;
    }

    /**
     * 속성2를 반환한다.
     *
     * @return 속성2
     */
    public int getProperty2() {
        return property2;
    }

    /**
     * 속성2를 설정한다.
     *
     * @param property2 속성2
     */
    public void setProperty2(int property2) {
        this.property2 = property2;
    }

    /**
     * 새로운 단 정보 객체를 생성하고 리스트에 추가한다.
     *
     * @return 새로 생성된 단 정보 객체
     */
    public ColumnInfo addNewColumnInfo() {
        ColumnInfo ci = new ColumnInfo();
        columnInfoList.add(ci);
        return ci;
    }

    /**
     * 단 정보 리스트를 반환한다.
     *
     * @return 단 정보 리스트
     */
    public ArrayList<ColumnInfo> getColumnInfoList() {
        return columnInfoList;
    }

    /**
     * 단 구분선 정보를 반환한다.
     *
     * @return 단 구분선 종류
     */
    public EachBorder getDivideLine() {
        return divideLine;
    }

    @Override
    public void copy(CtrlHeader from) {
        CtrlHeaderColumnDefine from2 = (CtrlHeaderColumnDefine) from;
        property.copy(from2.property);
        gapBetweenColumn = from2.gapBetweenColumn;
        property2 = from2.property2;

        for (ColumnInfo columnInfo : from2.columnInfoList) {
            columnInfoList.add(columnInfo.clone());
        }
        divideLine.copy(from2.divideLine);
    }
}