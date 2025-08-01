package com.agadasom.hwplib.object.bodytext.control.gso;

import com.agadasom.hwplib.object.bodytext.control.Control;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderGso;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponenteach.ShapeComponentArc;
import com.agadasom.hwplib.object.bodytext.control.gso.textbox.TextBox;

/**
 * 호 개체 컨트롤
 *
 * @author neolord
 */
public class ControlArc extends GsoControl {
    /**
     * 글상자
     */
    private TextBox textBox;
    /**
     * 호 개체 속성
     */
    private ShapeComponentArc shapeComponentArc;

    /**
     * 생성자
     */
    public ControlArc() {
        this(new CtrlHeaderGso());
    }

    /**
     * 생성자
     *
     * @param header 그리기 개체를 위한 컨트롤 헤더
     */
    public ControlArc(CtrlHeaderGso header) {
        super(header);
        setGsoId(GsoControlType.Arc.getId());

        textBox = null;
        shapeComponentArc = new ShapeComponentArc();
    }

    /**
     * 글상자 객체를 생성한다.
     */
    public void createTextBox() {
        textBox = new TextBox();
    }

    /**
     * 글상자 객체를 삭제한다.
     */
    public void deleteTextBox() {
        textBox = null;
    }

    /**
     * 글상자 객체를 반환한다.
     *
     * @return 글상자 객체
     */
    public TextBox getTextBox() {
        return textBox;
    }

    /**
     * 호 개체의 속성 객체를 반환한다.
     *
     * @return 호 개체의 속성 객체
     */
    public ShapeComponentArc getShapeComponentArc() {
        return shapeComponentArc;
    }

    @Override
    public Control clone() {
        ControlArc cloned = new ControlArc();
        cloned.copyGsoControlPart(this);

        if (textBox != null) {
            cloned.createTextBox();
            cloned.textBox.copy(textBox);
        }

        cloned.shapeComponentArc.copy(shapeComponentArc);
        return cloned;
    }
}
