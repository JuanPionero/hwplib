package com.agadasom.hwplib.object.bodytext.control.equation;

import com.agadasom.hwplib.object.etc.Color4Byte;
import com.agadasom.hwplib.object.etc.HWPString;

/**
 * 수식 컨트롤의 수식 정보를 나타내는 레코드
 *
 * @author neolord
 */
public class EQEdit {
    /**
     * 속성(스크립트가 차지하는 범위. 첫 비트가 켜져 있으면 줄 단위, 꺼져 있으면 글자 단위??)
     */
    private long property;
    /**
     * 한글 수식 스크립트
     */
    private HWPString script;
    /**
     * 수식 글자 크기
     */
    private long letterSize;
    /**
     * 글자 색상
     */
    private Color4Byte letterColor;
    /**
     * base line
     */
    private short baseLine;
    /**
     * 알수 없는 2 byte;
     */
    private int unknown;
    /**
     * 버전 정보
     */
    private HWPString versionInfo;
    /**
     * 폰트 이름
     */
    private HWPString fontName;

    /**
     * 생성자
     */
    public EQEdit() {
        script = new HWPString();
        letterColor = new Color4Byte();
        versionInfo = new HWPString();
        fontName = new HWPString();
    }

    /**
     * 속성값을 반환한다.
     *
     * @return 속성값
     */
    public long getProperty() {
        return property;
    }

    /**
     * 속성값을 설정한다.
     *
     * @param property 속성값
     */
    public void setProperty(long property) {
        this.property = property;
    }

    /**
     * 한글 수식 스크립트를 반환한다.
     *
     * @return 한글 수식 스크립트
     */
    public HWPString getScript() {
        return script;
    }

    /**
     * 수식 글자 크기를 반환한다.
     *
     * @return 수식 글자 크기
     */
    public long getLetterSize() {
        return letterSize;
    }

    /**
     * 수식 글자 크기를 설정한다.
     *
     * @param letterSize 수식 글자 크기
     */
    public void setLetterSize(long letterSize) {
        this.letterSize = letterSize;
    }

    /**
     * 글자 색상 객체를 반환한다.
     *
     * @return 글자 색상 객체
     */
    public Color4Byte getLetterColor() {
        return letterColor;
    }

    /**
     * base line을 반환한다.
     *
     * @return base line
     */
    public short getBaseLine() {
        return baseLine;
    }

    /**
     * base line을 설정한다.
     *
     * @param baseLine base line
     */
    public void setBaseLine(short baseLine) {
        this.baseLine = baseLine;
    }

    /**
     * 알수 없는 2 byte를 반환한다.
     */
    public int getUnknown() {
        return unknown;
    }

    /**
     * 알수 없는 2 byte를 설정한다.
     *
     * @param unknown 알수 없는 2 byte
     */
    public void setUnknown(int unknown) {
        this.unknown = unknown;
    }

    /**
     * 버전 정보를 반환한다.
     *
     * @return 버전 정보
     */
    public HWPString getVersionInfo() {
        return versionInfo;
    }

    /**
     * 폰트 이름을 반환한다.
     *
     * @return 폰트 이름
     */
    public HWPString getFontName() {
        return fontName;
    }

    public void copy(EQEdit from) {
        property = from.property;
        script.copy(from.script);
        letterSize = from.letterSize;
        letterColor.copy(from.letterColor);
        baseLine = from.baseLine;
        unknown = from.unknown;
        versionInfo.copy(from.versionInfo);
        fontName.copy(from.fontName);
    }
}
