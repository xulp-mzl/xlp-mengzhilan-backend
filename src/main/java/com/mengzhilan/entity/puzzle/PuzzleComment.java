package com.mengzhilan.entity.puzzle;

import com.mengzhilan.base.MZBaseEntity;
import org.xlp.db.tableoption.annotation.XLPColumn;
import org.xlp.db.tableoption.annotation.XLPEntity;
import org.xlp.db.tableoption.xlpenum.DataType;
import org.xlp.javabean.annotation.Bean;
import org.xlp.javabean.annotation.FieldName;

/**
 * Create by xlp on 2021/3/30
 *
 * 字谜评论实体
 */
@Bean
@XLPEntity(tableName = "mz_puzzle_comment", descriptor = "字谜评论表")
public class PuzzleComment extends MZBaseEntity {
    private static final long serialVersionUID = -645870733567119336L;

    @FieldName
    @XLPColumn(columnName = "is_approved", dataType = DataType.BOOLEAN,
            descriptor = "评论审核是否通过", defaultValue = "false")
    private boolean isApproved = false;

    @FieldName
    @XLPColumn(columnName = "content", dataType = DataType.VARCHAR,
            descriptor = "评论内容", length = 500, maxLength = 500)
    private String content;

    @FieldName
    @XLPColumn(columnName = "puzzle_id", dataType = DataType.VARCHAR,
            descriptor = "字谜id", length = 64)
    private String puzzleId;

    @FieldName
    @XLPColumn(columnName = "parent_puzzle_comment_id", dataType = DataType.VARCHAR,
            descriptor = "父评论id", length = 64)
    private String parentPuzzleCommentId;

    /**
     * 字谜对象
     */
    private Puzzle puzzle;

    /**
     * 父评论
     */
    private PuzzleComment parentPuzzleComment;

    public String getParentPuzzleCommentId() {
        return parentPuzzleCommentId;
    }

    public void setParentPuzzleCommentId(String parentPuzzleCommentId) {
        this.parentPuzzleCommentId = parentPuzzleCommentId;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public PuzzleComment getParentPuzzleComment() {
        return parentPuzzleComment;
    }

    public void setParentPuzzleComment(PuzzleComment parentPuzzleComment) {
        this.parentPuzzleComment = parentPuzzleComment;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(String puzzleId) {
        this.puzzleId = puzzleId;
    }

    @Override
    public String toString() {
        return "PuzzleComment{" +
                "classId='" + getClassId() + '\'' +
                ", id='" + getId() + '\'' +
                ", isApproved=" + isApproved +
                ", content='" + content + '\'' +
                ", puzzleId='" + puzzleId + '\'' +
                ", parentPuzzleCommentId='" + parentPuzzleCommentId + '\'' +
                "} ";
    }
}
