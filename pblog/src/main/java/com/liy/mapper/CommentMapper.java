package com.liy.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liy.vo.CommentVO;
import com.liy.vo.ReplyCountVO;
import com.liy.vo.ReplyVO;
import com.liy.vo.SystemCommentVO;
import com.liy.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 博客评论表 Mapper 接口
 * </p>
 *
 * @author CodeScholar
 * @date 2023年4月9日
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentVO> listComments(@Param("page") int page, @Param("size") int size, @Param("articleId") Long articleId);

    List<ReplyVO> listReplies(Integer id);

    ReplyCountVO listReplyCountByCommentId(Integer id);

    Page<SystemCommentVO> selectPageList(@Param("page") Page<Object> objectPage, @Param("keywords") String keywords);
}
