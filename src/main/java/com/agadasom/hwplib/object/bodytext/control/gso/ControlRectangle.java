package com.agadasom.hwplib.object.bodytext.control.gso;

import com.agadasom.hwplib.object.bodytext.control.Control;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderGso;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponenteach.ShapeComponentRectangle;
import com.agadasom.hwplib.object.bodytext.control.gso.textbox.TextBox;

/**
 * 사각형 개체 컨트롤
 *
 * @author neolord
 */
public class ControlRectangle extends GsoControl {
    /**
     * 글상자
     */
    private TextBox textBox;
    /**
     * 사각형 개체 속성
     */
    private ShapeComponentRectangle shapeComponentRectangle;

    /**
     * 생성자
     */
    public ControlRectangle() {
        this(new CtrlHeaderGso());
    }

    /**
     * 생성자
     *
     * @param header 그리기 개체를 위한 컨트롤 헤더
     */
    public ControlRectangle(CtrlHeaderGso header) {
        super(header);
        setGsoId(GsoControlType.Rectangle.getId());

        textBox = null;
        shapeComponentRectangle = new ShapeComponentRectangle();
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
     * 사각형 개체의 속성 객체를 반환한다.
     *
     * @return 사각형 개체의 속성 객체
     */
    public ShapeComponentRectangle getShapeComponentRectangle() {
        return shapeComponentRectangle;
    }

    @Override
    public Control clone() {
        ControlRectangle cloned = new ControlRectangle();
        cloned.copyGsoControlPart(this);

        if (textBox != null) {
            cloned.createTextBox();
            cloned.textBox.copy(textBox);
        }

        cloned.shapeComponentRectangle.copy(shapeComponentRectangle);
        return cloned;
    }
}
