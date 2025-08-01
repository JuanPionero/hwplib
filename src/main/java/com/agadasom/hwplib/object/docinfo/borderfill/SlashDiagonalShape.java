package com.agadasom.hwplib.object.docinfo.borderfill;

/**
 * Slash 대각선 모양
 *
 * @author neolord
 */
public enum SlashDiagonalShape {
    /**
     * none
     */
    None((byte) 0),
    /**
     * slash (RightTop --> leftBottom) "/"
     */
    Slash((byte) 2),
    /**
     * RightTop --> Bottom Edge
     */
    RightTopToBottomEdge((byte) 3),
    /**
     * RightTop --> Left Edge
     */
    RightTopToLeftEdge((byte) 6),
    /**
     * RightTop --> Bottom &amp; Left Edge
     */
    RightTopToBottomLeftEdge((byte) 7);

    /**
     * 파일에 저장되는 정수값
     */
    private byte value;

    /**
     * 생성자
     *
     * @param value 파일에 저장되는 정수값
     */
    SlashDiagonalShape(byte value) {
        this.value = value;
    }

    /**
     * 파일에 저장되는 정수값을 반환한다.
     *
     * @return 파일에 저장되는 정수값
     */
    public byte getValue() {
        return value;
    }

    /**
     * 파일에 저장되는 정수값에 해당되는 enum 값을 반환한다.
     *
     * @param value 파일에 저장되는 정수값
     * @return enum 값
     */
    public static SlashDiagonalShape valueOf(byte value) {
        for (SlashDiagonalShape sds : values()) {
            if (sds.value == value) {
                return sds;
            }
        }
        return None;
    }
}
