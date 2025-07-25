package com.agadasom.hwplib.object.bodytext.control.gso.shapecomponenteach.arc;

/**
 * 호 테두리
 *
 * @author neolord
 */
public enum ArcType {
    /**
     * 호
     */
    Arc((byte) 0),
    /**
     * 부채꼴
     */
    CircularSector((byte) 1),
    /**
     * 활
     */
    Bow((byte) 2);

    /**
     * 파일에 저장되는 정수값
     */
    private byte value;

    /**
     * 생성자
     *
     * @param value 파일에 저장되는 정수값
     */
    ArcType(byte value) {
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
    public static ArcType valueOf(byte value) {
        for (ArcType ab : values()) {
            if (ab.value == value) {
                return ab;
            }
        }
        return Arc;
    }
}
