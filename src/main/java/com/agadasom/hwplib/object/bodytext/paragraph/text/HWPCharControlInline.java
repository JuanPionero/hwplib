package com.agadasom.hwplib.object.bodytext.paragraph.text;

/**
 * 인라인 컨트롤 character
 *
 * @author neolord
 */
public class HWPCharControlInline extends HWPChar {
    /**
     * 추가 정보
     */
    private byte[] addition;

    /**
     * 생성자
     */
    public HWPCharControlInline() {
    }

    /**
     * 글자의 종류을 반환한다.
     *
     * @return 글자의 타입
     */
    @Override
    public HWPCharType getType() {
        return HWPCharType.ControlInline;
    }

    /**
     * 추가 정보를 반환한다.
     *
     * @return 추가 정보
     */
    public byte[] getAddition() {
        return addition;
    }

    /**
     * 추가 정보를 설정한다.
     *
     * @param addition 추가 정보
     * @throws Exception
     */
    public void setAddition(byte[] addition) throws Exception {
        if (addition.length != 12) {
            throw new Exception("addition's length must be 12");
        }
        this.addition = addition;
    }

    public HWPChar clone() {
        HWPCharControlInline cloned = new HWPCharControlInline();
        cloned.code = code;

        if (addition != null) {
            cloned.addition = addition.clone();
        } else {
            cloned.addition = null;
        }

        return cloned;
    }

    @Override
    public int getCharSize() {
        return 8;
    }

    public boolean isHyperlinkEnd() {
        if (getCode() == 0x0004
                && hasAddition('%', 'h', 'l', 'k')) {
            return true;
        }
        return false;
    }

    private boolean hasAddition(char byte1, char byte2, char byte3, char byte4) {
        if (addition != null
                && addition[3] == byte1
                && addition[2] == byte2
                && addition[1] == byte3
                && addition[0] == byte4) {
            return true;
        }
        return false;
    }

}
