package com.agadasom.hwplib.object.bodytext.control.sectiondefine;

import com.agadasom.hwplib.object.docinfo.borderfill.EachBorder;
import com.agadasom.hwplib.object.etc.HWPString;

/**
 * 미주/각주 모양 정보에 대한 레코드
 *
 * @author neolord
 */
public class FootEndNoteShape {
    /**
     * 속성
     */
    private FootNoteShapeProperty property;
    /**
     * 사용자 기호
     */
    private HWPString userSymbol;
    /**
     * 앞 장식 문자
     */
    private HWPString beforeDecorativeLetter;
    /**
     * 뒤 장식 문자
     */
    private HWPString afterDecorativeLetter;
    /**
     * 시작 번호
     */
    private int startNumber;
    /**
     * 구분선 길이
     */
    private long divideLineLength;
    /**
     * 구분선 위 여백
     */
    private int divideLineTopMargin;
    /**
     * 구분선 아래 여백
     */
    private int divideLineBottomMargin;
    /**
     * 주석 사이 여백
     */
    private int betweenNotesMargin;
    /**
     * 구분선 정보
     */
    private EachBorder divideLine;
    /**
     * 알수 없는 4 byte;
     */
    private long unknown;

    /**
     * 생성자
     */
    public FootEndNoteShape() {
        property = new FootNoteShapeProperty();
        userSymbol = new HWPString();
        beforeDecorativeLetter = new HWPString();
        afterDecorativeLetter = new HWPString();
        divideLine = new EachBorder();
    }

    /**
     * 속성 객체를 반환한다.
     *
     * @return 속성 객체
     */
    public FootNoteShapeProperty getProperty() {
        return property;
    }

    /**
     * 사용자 기호를 반환한다.
     *
     * @return 사용자 기호
     */
    public HWPString getUserSymbol() {
        return userSymbol;
    }

    /**
     * 앞 장식 문자를 반환한다.
     *
     * @return 앞 장식 문자
     */
    public HWPString getBeforeDecorativeLetter() {
        return beforeDecorativeLetter;
    }

    /**
     * 뒤 장식 문자를 반환한다.
     *
     * @return 뒤 장식 문자
     */
    public HWPString getAfterDecorativeLetter() {
        return afterDecorativeLetter;
    }

    /**
     * 시작 번호를 반환한다.
     *
     * @return 시작 번호
     */
    public int getStartNumber() {
        return startNumber;
    }

    /**
     * 시작 번호를 설정한다.
     *
     * @param startNumber 시작 번호
     */
    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    /**
     * 구분선 길이를 반환한다.
     *
     * @return 구분선 길이
     */
    public long getDivideLineLength() {
        return divideLineLength;
    }

    /**
     * 구분선 길이를 설정한다.
     *
     * @param divideLineLength 구분선 길이
     */
    public void setDivideLineLength(long divideLineLength) {
        this.divideLineLength = divideLineLength;
    }

    /**
     * 구분선 위 여백의 크기를 반환한다.
     *
     * @return 구분선 위 여백의 크기
     */
    public int getDivideLineTopMargin() {
        return divideLineTopMargin;
    }

    /**
     * 구분선 위 여백의 크기를 설정한다.
     *
     * @param divideLineTopMargin 구분선 위 여백의 크기
     */
    public void setDivideLineTopMargin(int divideLineTopMargin) {
        this.divideLineTopMargin = divideLineTopMargin;
    }

    /**
     * 구분선 아래 여백의 크기를 반환한다.
     *
     * @return 구분선 아래 여백의 크기
     */
    public int getDivideLineBottomMargin() {
        return divideLineBottomMargin;
    }

    /**
     * 구분선 아래 여백의 크기를 설정한다.
     *
     * @param divideLineBottomMargin 구분선 아래 여백의 크기
     */
    public void setDivideLineBottomMargin(int divideLineBottomMargin) {
        this.divideLineBottomMargin = divideLineBottomMargin;
    }

    /**
     * 주석 사이 여백의 크기를 반환한다.
     *
     * @return 주석 사이 여백의 크기
     */
    public int getBetweenNotesMargin() {
        return betweenNotesMargin;
    }

    /**
     * 주석 사이 여백의 크기를 설정한다.
     *
     * @param betweenNotesMargin 주석 사이 여백의 크기
     */
    public void setBetweenNotesMargin(int betweenNotesMargin) {
        this.betweenNotesMargin = betweenNotesMargin;
    }

    /**
     * 구분선 정보를 반환한다.
     *
     * @return 구분선 정보
     */
    public EachBorder getDivideLine() {
        return divideLine;
    }

    /**
     * 알수 없는 4byte를 반환한다.
     *
     * @return 알수 없는 4byte
     */
    public long getUnknown() {
        return unknown;
    }

    /**
     * 알수 없는 4byte를 설정한다.
     *
     * @param unknown 알수 없는 4byte
     */
    public void setUnknown(long unknown) {
        this.unknown = unknown;
    }

    public void copy(FootEndNoteShape from) {
        property.copy(from.property);
        userSymbol.copy(from.userSymbol);
        beforeDecorativeLetter.copy(from.beforeDecorativeLetter);
        afterDecorativeLetter.copy(from.afterDecorativeLetter);
        startNumber = from.startNumber;
        divideLineLength = from.divideLineLength;
        divideLineTopMargin = from.divideLineTopMargin;
        divideLineBottomMargin = from.divideLineBottomMargin;
        betweenNotesMargin = from.betweenNotesMargin;
        divideLine.copy(from.divideLine);
        unknown = from.unknown;
    }
}
