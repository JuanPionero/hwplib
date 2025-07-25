package com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.shadowinfo;

import com.agadasom.hwplib.object.etc.Color4Byte;

/**
 * 그림자 정보
 *
 * @author neolord
 */
public class ShadowInfo {
    /**
     * 그림자 종류
     */
    private ShadowType type;
    /**
     * 그림자 색
     */
    private Color4Byte color;
    /**
     * 가로 방향 이동 거리
     */
    private int offsetX;
    /**
     * 세로 방향 이동 거리
     */
    private int offsetY;
    /**
     * 투명도
     */
    private short transparent;

    /**
     * 생성자
     */
    public ShadowInfo() {
        color = new Color4Byte();
    }

    /**
     * 그림자 종류를 반환한다.
     *
     * @return 그림자 종류
     */
    public ShadowType getType() {
        return type;
    }

    /**
     * 그림자 종류를 섷정한다.
     *
     * @param type 그림자 종류
     */
    public void setType(ShadowType type) {
        this.type = type;
    }

    /**
     * 그림자 색상 객체를 반환한다.
     *
     * @return 그림자 색상 객체
     */
    public Color4Byte getColor() {
        return color;
    }

    /**
     * 가로 방향 이동 거리를 반환한다.
     *
     * @return 가로 방향 이동 거리
     */
    public int getOffsetX() {
        return offsetX;
    }

    /**
     * 가로 방향 이동 거리를 설정한다.
     *
     * @param offsetX 가로 방향 이동 거리
     */
    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    /**
     * 세로 방향 이동 거리를 반환한다.
     *
     * @return 세로 방향 이동 거리
     */
    public int getOffsetY() {
        return offsetY;
    }

    /**
     * 세로 방향 이동 거리를 설정한다.
     *
     * @param offsetY 세로 방향 이동 거리
     */
    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    /**
     * 투명도를 반환한다.
     *
     * @return 투명도
     */
    public short getTransparent() {
        return transparent;
    }

    /**
     * 투명도를 설정한다.
     *
     * @param transparent 투명도
     */
    public void setTransparent(short transparent) {
        this.transparent = transparent;
    }

    public void copy(ShadowInfo from) {
        type = from.type;
        color.copy(from.color);
        offsetX = from.offsetX;
        offsetY = from.offsetY;
        transparent = from.transparent;
    }
}
