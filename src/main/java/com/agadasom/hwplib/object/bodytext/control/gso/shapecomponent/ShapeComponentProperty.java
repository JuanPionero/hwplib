package com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent;

import com.agadasom.hwplib.util.binary.BitFlag;

/**
 * 객체 공통 속성의 속성을 나타내는 객체
 *
 * @author neolord
 */
public class ShapeComponentProperty {
    /**
     * 파일에 저장되는 정수값(unsigned 4 byte)
     */
    private long value;

    /**
     * 생성자
     */
    public ShapeComponentProperty() {
    }

    /**
     * 파일에 저장되는 정수값을 반환한다.
     *
     * @return 파일에 저장되는 정수값
     */
    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    /**
     * 개체가 좌우 대칭인지 여부를 반환한다. (0 bit)
     * 
     * @return 좌우 대칭인지 여부
     */
    public boolean isFlipHorizontal() {
        return BitFlag.get(value, 0);
    }

    /**
     * 개체가 좌우 대칭인지 여부를 설정한다. (0 bit)
     * 
     * @param flipHorizontal 좌우 대칭인지 여부
     */
    public void setFlipHorizontal(boolean flipHorizontal) {
        value = BitFlag.set(value, 0, flipHorizontal);
    }

    /**
     * 개체가 상하 대칭인지 여부를 반환한다. (1 bit)
     * 
     * @return 상하 대칭인지 여부
     */
    public boolean isFlipVertical() {
        return BitFlag.get(value, 1);
    }

    /**
     * 개체가 상하 대칭인지 여부를 설정한다. (1 bit)
     * 
     * @param flipVertical 상하 대칭인지 여부
     */
    public void setFlipVertical(boolean flipVertical) {
        value = BitFlag.set(value, 1, flipVertical);
    }

    /**
     * 개체가 이미지와 함께 회전하는지 여부를 반환한다. (19 bit)
     * 
     * @return 이미지와 함께 회전하는지 여부
     */
    public boolean isRotateWithImage() {
        return BitFlag.get(value, 19);
    }

    /**
     * 개체가 이미지와 함께 회전하는지 여부를 설정한다. (19 bit)
     * 
     * @param rotateWithImage 개체가 이미지와 함께 회전하는지 여부
     */
    public void setRotateWithImage(boolean rotateWithImage) {
        value = BitFlag.set(value, 19, rotateWithImage);
    }

    /**
     * 그림 개체의 색 반전 여부를 반환한다. (24 bit)
     * 
     * @return 그림 개체의 색 반전 여부
     */
    public boolean isReverseColor() {
        return BitFlag.get(value, 24);
    }

    /**
     * 그림 개체의 색 반전 여부를 설정한다. (24 bit)
     * 
     * @param reverseColor 그림 개체의 색 반전 여부
     */
    public void setReverseColor(boolean reverseColor) {
        value = BitFlag.set(value, 24, reverseColor);
    }

}
