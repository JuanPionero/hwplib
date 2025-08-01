package com.agadasom.hwplib.object.docinfo.facename;

import com.agadasom.hwplib.util.binary.BitFlag;

/**
 * 글꼴에 대한 속성 객체
 *
 * @author neolord
 */
public class FaceNameProperty {
    /**
     * 파일에 저장되는 정수값(unsigned 1 byte)
     */
    private short value;

    /**
     * 생성자
     */
    public FaceNameProperty() {
    }

    /**
     * 파일에 저장되는 정수값을 반환한다.
     *
     * @return 파일에 저장되는 정수값
     */
    public short getValue() {
        return value;
    }

    /**
     * 파일에 저장되는 정수값을 설정한다.
     *
     * @param value 파일에 저장되는 정수값
     */
    public void setValue(short value) {
        this.value = value;
    }

    /**
     * 글꼺 타입을 반환한다.(0~1 bit)
     * 
     * @return 글꼺 타입
     */
    public FontType getType() {
        return FontType.valueOf((byte) BitFlag.get(value, 0, 1));
    }

    /**
     * 글꼴 타입을 설정한다.(0~1 bit)
     * 
     * @param type 글꼴 타입
     */
    public void setType(FontType type) {
        value = BitFlag.set(value, 0, 1, type.getValue());
    }

    /**
     * 대체 글꼴 존재 여부를 반환한다. (0x80)
     *
     * @return 대체 글꼴 존재 여부
     */
    public boolean hasSubstituteFont() {
        return BitFlag.get(value, 7);
    }

    /**
     * 대체 글꼴 존재 여부을 설정한다. (0x80)
     *
     * @param hasSubstituteFontInfo 대체 글꼴 존재 여부
     */
    public void setHasSubstituteFont(boolean hasSubstituteFontInfo) {
        value = BitFlag.set(value, 7, hasSubstituteFontInfo);
    }

    /**
     * 글꼴 유형 정보 존재 여부를 반환한다. (0x40)
     *
     * @return 글꼴 유형 정보 존재 여부
     */
    public boolean hasFontInfo() {
        return BitFlag.get(value, 6);
    }

    /**
     * 글꼴 유형 정보 존재 여부를 설정한다. (0x40)
     *
     * @param hasFontInfo 글꼴 유형 정보 존재 여부
     */
    public void setHasFontInfo(boolean hasFontInfo) {
        value = BitFlag.set(value, 6, hasFontInfo);
    }

    /**
     * 기본 글꼴 존재 여부를 반환한다.
     *
     * @return 기본 글꼴 존재 여부
     */
    public boolean hasBaseFont() {
        return BitFlag.get(value, 5);
    }

    /**
     * 기본 글꼴 존재 여부를 설정한다.
     *
     * @param hasBaseFont 기본 글꼴 존재 여부
     */
    public void setHasBaseFont(boolean hasBaseFont) {
        value = BitFlag.set(value, 5, hasBaseFont);
    }

    public void copy(FaceNameProperty from) {
        value = from.value;
    }
}
