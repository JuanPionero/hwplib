package com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent;

import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.renderingnfo.RenderingInfo;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.renderingnfo.ScaleRotateMatrixPair;

/**
 * 객체 공통 속성
 *
 * @author neolord
 */
public abstract class ShapeComponent {
    /**
     * 개체 컨트롤 Id
     */
    private long gsoId;
    /**
     * 개체가 속한 그룹 내에서의 X offset
     */
    private int offsetX;
    /**
     * 개체가 속한 그룹 내에서의 Y offset
     */
    private int offsetY;
    /**
     * 그룹핑 횟수
     */
    private int groupingCount;
    /**
     * 개체 요소의 local file version
     */
    private int localFileVersion;
    /**
     * 생성시 폭
     */
    private int widthAtCreate;
    /**
     * 생성시 높이
     */
    private int heightAtCreate;
    /**
     * 현재 폭
     */
    private int widthAtCurrent;
    /**
     * 현재 높이
     */
    private int heightAtCurrent;
    /**
     * 속성(정보 없음)
     */
    private ShapeComponentProperty property;
    /**
     * 회전각
     */
    private int rotateAngle;
    /**
     * 회전 중심의 x 좌표(개체 좌표계)
     */
    private int rotateXCenter;
    /**
     * 회전 중심의 y 좌표(개체 좌표계)
     */
    private int rotateYCenter;
    /**
     * Rendering 정보
     */
    private RenderingInfo renderingInfo;

    private long instid;

    /**
     * 생성자
     */
    protected ShapeComponent() {
        renderingInfo = new RenderingInfo();
        property = new ShapeComponentProperty();
    }

    /**
     * 개체 컨트롤 Id를 반환한다.
     *
     * @return 개체 컨트롤 Id
     */
    public long getGsoId() {
        return gsoId;
    }

    /**
     * 개체 컨트롤 Id를 설정한다.
     *
     * @param gsoId 개체 컨트롤 Id
     */
    public void setGsoId(long gsoId) {
        this.gsoId = gsoId;
    }

    /**
     * 개체가 속한 그룹 내에서의 X offset을 반환한다.
     *
     * @return 개체가 속한 그룹 내에서의 X offset
     */
    public int getOffsetX() {
        return offsetX;
    }

    /**
     * 개체가 속한 그룹 내에서의 X offset를 설정한다.
     *
     * @param offsetX 개체가 속한 그룹 내에서의 X offset
     */
    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    /**
     * 개체가 속한 그룹 내에서의 Y offset을 반환한다.
     *
     * @return 개체가 속한 그룹 내에서의 Y offset
     */
    public int getOffsetY() {
        return offsetY;
    }

    /**
     * 개체가 속한 그룹 내에서의 Y offset를 설정한다.
     *
     * @param offsetY 개체가 속한 그룹 내에서의 Y offset
     */
    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    /**
     * 그룹핑 횟수를 반환한다.
     *
     * @return 그룹핑 횟수
     */
    public int getGroupingCount() {
        return groupingCount;
    }

    /**
     * 그룹핑 횟수를 설정한다.
     *
     * @param groupingCount 그룹핑 횟수
     */
    public void setGroupingCount(int groupingCount) {
        this.groupingCount = groupingCount;
    }

    /**
     * 개체 요소의 local file version을 반환한다.
     *
     * @return 개체 요소의 local file version
     */
    public int getLocalFileVersion() {
        return localFileVersion;
    }

    /**
     * 개체 요소의 local file version을 설정한다.
     *
     * @param localFileVersion 개체 요소의 local file version
     */
    public void setLocalFileVersion(int localFileVersion) {
        this.localFileVersion = localFileVersion;
    }

    /**
     * 생성시 폭을 반환한다.
     *
     * @return 생성시 폭
     */
    public int getWidthAtCreate() {
        return widthAtCreate;
    }

    /**
     * 생성시 폭을 설정한다.
     *
     * @param widthAtCreate 생성시 폭
     */
    public void setWidthAtCreate(int widthAtCreate) {
        this.widthAtCreate = widthAtCreate;
    }

    /**
     * 생성시 높이를 반환한다.
     *
     * @return 생성시 높이
     */
    public int getHeightAtCreate() {
        return heightAtCreate;
    }

    /**
     * 생성시 높이를 설정한다.
     *
     * @param heightAtCreate 생성시 높이
     */
    public void setHeightAtCreate(int heightAtCreate) {
        this.heightAtCreate = heightAtCreate;
    }

    /**
     * 현재 폭을 반환한다.
     *
     * @return 현재 폭
     */
    public int getWidthAtCurrent() {
        return widthAtCurrent;
    }

    /**
     * 현재 폭을 설정한다.
     *
     * @param widthAtCurrent 현재 폭
     */
    public void setWidthAtCurrent(int widthAtCurrent) {
        this.widthAtCurrent = widthAtCurrent;
    }

    /**
     * 현재 높이를 반환한다.
     *
     * @return 현재 높이
     */
    public int getHeightAtCurrent() {
        return heightAtCurrent;
    }

    /**
     * 현재 높이를 설정한다.
     *
     * @param heightAtCurrent 현재 높이
     */
    public void setHeightAtCurrent(int heightAtCurrent) {
        this.heightAtCurrent = heightAtCurrent;
    }

    /**
     * 속성값을 반환한다.(정보 없음)
     *
     * @return 속성값
     */
    public ShapeComponentProperty getProperty() {
        return property;
    }

    /**
     * 회전각을 반환한다.
     *
     * @return 회전각
     */
    public int getRotateAngle() {
        return rotateAngle;
    }

    /**
     * 회전각을 설정한다.
     *
     * @param rotateAngle 회전각
     */
    public void setRotateAngle(int rotateAngle) {
        this.rotateAngle = rotateAngle;
    }

    /**
     * 회전 중심의 x 좌표를 반환한다.(개체 좌표계)
     *
     * @return 회전 중심의 x 좌표
     */
    public int getRotateXCenter() {
        return rotateXCenter;
    }

    /**
     * 회전 중심의 x 좌표를 설정한다. (개체 좌표계)
     *
     * @param rotateXCenter 회전 중심의 x 좌표
     */
    public void setRotateXCenter(int rotateXCenter) {
        this.rotateXCenter = rotateXCenter;
    }

    /**
     * 회전 중심의 y 좌표를 반환한다.(개체 좌표계)
     *
     * @return 회전 중심의 y 좌표
     */
    public int getRotateYCenter() {
        return rotateYCenter;
    }

    /**
     * 회전 중심의 y 좌표를 설정한다.
     *
     * @param rotateYCenter
     */
    public void setRotateYCenter(int rotateYCenter) {
        this.rotateYCenter = rotateYCenter;
    }

    /**
     * Rendering 정보 객체를 반환한다.
     *
     * @return Rendering 정보 객체
     */
    public RenderingInfo getRenderingInfo() {
        return renderingInfo;
    }

    /**
     * 메트릭스들를 일반 상태(회전이나 확대되지 않은 상턔)로 설정한다.
     */
    public void setMatrixsNormal() {
        renderingInfo.getTranslationMatrix().setValue(0, 1.0f);
        renderingInfo.getTranslationMatrix().setValue(1, 0.0f);
        renderingInfo.getTranslationMatrix().setValue(2, 0.0f);
        renderingInfo.getTranslationMatrix().setValue(3, 0.0f);
        renderingInfo.getTranslationMatrix().setValue(4, 1.0f);
        renderingInfo.getTranslationMatrix().setValue(5, 0.0f);

        ScaleRotateMatrixPair pair = renderingInfo.addNewScaleRotateMatrixPair();
        pair.getScaleMatrix().setValue(0, 1.0f);
        pair.getScaleMatrix().setValue(1, 0.0f);
        pair.getScaleMatrix().setValue(2, 0.0f);
        pair.getScaleMatrix().setValue(3, 0.0f);
        pair.getScaleMatrix().setValue(4, 1.0f);
        pair.getScaleMatrix().setValue(5, 0.0f);

        pair.getRotateMatrix().setValue(0, 1.0f);
        pair.getRotateMatrix().setValue(1, 0.0f);
        pair.getRotateMatrix().setValue(2, 0.0f);
        pair.getRotateMatrix().setValue(3, 0.0f);
        pair.getRotateMatrix().setValue(4, 1.0f);
        pair.getRotateMatrix().setValue(5, 0.0f);
    }

    /**
     * 객체의 인스턴스 아이디를 반환한다..
     * 
     * @return 객체의 인스턴스 아이디
     */
    public long getInstid() {
        return instid;
    }

    /**
     * 객체의 인스턴스 아이디를 설정한다.
     * 
     * @param instid 객체의 인스턴스 아이디
     */
    public void setInstid(long instid) {
        this.instid = instid;
    }

    public abstract void copy(ShapeComponent from);

    public void copyShapeComponent(ShapeComponent from) {
        setGsoId(from.getGsoId());

        offsetX = from.offsetX;
        offsetY = from.offsetY;
        groupingCount = from.groupingCount;
        localFileVersion = from.localFileVersion;
        widthAtCreate = from.widthAtCreate;
        heightAtCreate = from.heightAtCreate;
        widthAtCurrent = from.widthAtCurrent;
        heightAtCurrent = from.heightAtCurrent;
        property.setValue(from.property.getValue());
        rotateAngle = from.rotateAngle;
        rotateXCenter = from.rotateXCenter;
        rotateYCenter = from.rotateYCenter;
        renderingInfo.copy(from.renderingInfo);
        instid = from.instid;
    }
}
