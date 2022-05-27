package com.mengzhilan.entity.puzzle;

import com.mengzhilan.base.MZBaseEntity;
import com.mengzhilan.base.Pinyin;
import org.xlp.db.ddl.annotation.XLPIndex;
import org.xlp.db.tableoption.annotation.XLPColumn;
import org.xlp.db.tableoption.annotation.XLPEntity;
import org.xlp.db.tableoption.xlpenum.DataType;
import org.xlp.javabean.annotation.Bean;
import org.xlp.javabean.annotation.FieldName;
import java.util.List;

/**
 * Create by xlp on 2021/3/28
 *
 * 字谜实体
 */
@Bean
@XLPEntity(tableName = "mz_puzzle", descriptor = "字谜表")
public class Puzzle extends MZBaseEntity implements Pinyin {
    private static final long serialVersionUID = 5270607112263112047L;

    @FieldName
    @XLPIndex
    @XLPColumn(columnName = "pinyin", dataType = DataType.VARCHAR,
            length = 1000, descriptor = "谜题拼音", maxLength = 1000)
    private String pinyin;

    @FieldName
    @XLPIndex
    @XLPColumn(columnName = "simple_pinyin", dataType = DataType.VARCHAR,
            length = 255, descriptor = "谜题拼音简写", maxLength = 255)
    private String simplePinyin;

    @FieldName
    @XLPColumn(columnName = "descriptor", dataType = DataType.VARCHAR,
            length = 500, descriptor = "描述", maxLength = 500)
    private String descriptor;

    @FieldName
    @XLPIndex
    @XLPColumn(columnName = "riddle", dataType = DataType.VARCHAR,
            length = 255, descriptor = "谜题", maxLength = 255, isNull = false)
    private String riddle;

    @FieldName
    @XLPIndex
    @XLPColumn(columnName = "mystery", dataType = DataType.CHAR,
            length = 1, descriptor = "谜底", maxLength = 1, isNull = false)
    private String mystery;

    @FieldName
    @XLPColumn(columnName = "reserved_field", dataType = DataType.VARCHAR,
            length = 255, descriptor = "预留字段", maxLength = 255)
    private String reservedField;

    @FieldName
    @XLPColumn(columnName = "classification", dataType = DataType.VARCHAR,
            length = 255, descriptor = "分类", maxLength = 255)
    private String classification;

    @FieldName
    @XLPColumn(columnName = "mystery_pinyin", dataType = DataType.VARCHAR,
            length = 15, descriptor = "谜底拼音", maxLength = 15)
    private String mysteryPinyin;

    @FieldName
    @XLPColumn(columnName = "mystery_simple_pinyin", dataType = DataType.VARCHAR,
            length = 5, descriptor = "谜底拼音简写", maxLength = 5)
    private String mysterySimplePinyin;

    @XLPColumn(columnName = "riddle_length", dataType = DataType.INT,
            descriptor = "谜题长度", zeroFill = true)
    private Integer riddleLength;

    @FieldName(mark = "字谜评论")
    private List<PuzzleComment> puzzleComments;

    /**
     * 获取字谜评论
     *
     * @return
     */
    public List<PuzzleComment> getPuzzleComments() {
        return puzzleComments;
    }

    /**
     * 设置字谜评论
     *
     * @param puzzleComments
     */
    public void setPuzzleComments(List<PuzzleComment> puzzleComments) {
        this.puzzleComments = puzzleComments;
    }

    /**
     * 获取谜题长度
     *
     * @return
     */
    public Integer getRiddleLength() {
        if (riddleLength == null && riddle != null)
            return riddle.length();
        return riddleLength;
    }

    /**
     * 设置谜题长度
     *
     * @param riddleLength
     */
    public void setRiddleLength(Integer riddleLength) {
        this.riddleLength = riddleLength;
    }

    /**
     * 获取谜底拼音
     *
     * @return
     */
    public String getMysteryPinyin() {
        return mysteryPinyin;
    }

    /**
     * 设置谜底拼音
     *
     * @param mysteryPinyin
     */
    public void setMysteryPinyin(String mysteryPinyin) {
        this.mysteryPinyin = mysteryPinyin;
    }

    /**
     * 获取谜底拼音简写
     *
     * @return
     */
    public String getMysterySimplePinyin() {
        return mysterySimplePinyin;
    }

    /**
     * 设置谜底拼音简写
     *
     * @param mysterySimplePinyin
     */
    public void setMysterySimplePinyin(String mysterySimplePinyin) {
        this.mysterySimplePinyin = mysterySimplePinyin;
    }

    /**
     * 获取描述
     *
     * @return
     */
    public String getDescriptor() {
        return descriptor;
    }

    /**
     * 设置描述
     *
     * @param descriptor
     */
    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * 获取谜题
     *
     * @return
     */
    public String getRiddle() {
        return riddle;
    }

    /**
     * 设置谜题
     *
     * @param riddle
     */
    public void setRiddle(String riddle) {
        this.riddle = riddle;
    }

    /**
     * 获取谜底
     *
     * @return
     */
    public String getMystery() {
        return mystery;
    }

    /**
     * 设置谜底
     *
     * @param mystery
     */
    public void setMystery(String mystery) {
        this.mystery = mystery;
    }

    /**
     * 获取预留字段
     *
     * @return
     */
    public String getReservedField() {
        return reservedField;
    }

    /**
     * 设置预留字段
     *
     * @param reservedField
     */
    public void setReservedField(String reservedField) {
        this.reservedField = reservedField;
    }

    /**
     * 获取分类
     *
     * @return
     */
    public String getClassification() {
        return classification;
    }

    /**
     * 设置分类
     *
     * @param classification
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public String getPinyin() {
        return pinyin;
    }

    @Override
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public String getSimplePinyin() {
        return simplePinyin;
    }

    @Override
    public void setSimplePinyin(String simplePinyin) {
        this.simplePinyin = simplePinyin;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "classId='" + getClassId() + '\'' +
                ", id='" + getId() + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", simplePinyin='" + simplePinyin + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", riddle='" + riddle + '\'' +
                ", mystery='" + mystery + '\'' +
                ", reservedField='" + reservedField + '\'' +
                ", classification='" + classification + '\'' +
                ", mysteryPinyin='" + mysteryPinyin + '\'' +
                ", mysterySimplePinyin='" + mysterySimplePinyin + '\'' +
                ", riddleLength=" + riddleLength +
                "} ";
    }
}
