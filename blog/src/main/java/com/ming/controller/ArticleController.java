package com.ming.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ming.common.VO.ArticleVO;
import com.ming.common.lang.Result;
import com.ming.entity.Article;
import com.ming.service.ArticleService;
import com.ming.service.HistoryService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ming
 * @since 2021-05-05
 */
@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    HistoryService historyService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    private static final String ARTICLE_PREFIX_NAME = "queryArticleById:";

    //分页展示所有文章
    @GetMapping({"/articles", "/"})
    public Result list(@RequestParam(defaultValue = "1") Long currentPage, HttpServletRequest request) {
        //增加访问记录
        historyService.incrementViews(request.getRemoteAddr());

        IPage<Article> iPage = articleService.queryPage(currentPage, 5);
        return Result.success(iPage);
    }

    //查询文章详情
    @GetMapping("/article/{id}")
    public Result detail(@PathVariable Long id) {

        ArticleVO article = articleService.queryArticleVOById(id);
        Assert.notNull(article, "该博客已被删除或不存在");

        return Result.success(article);
    }

    //编辑或添加文章,需要认证
    @RequiresAuthentication
    @PostMapping("/article/edit")
    public Result edit(@Validated @RequestBody Article article) {
        articleService.saveOrUpdateArticle(article);
        return Result.success(null);
    }


    //删除文章
    @RequiresAuthentication
    @GetMapping("/article/delete/{id}")
    public Result delete(@PathVariable Long id) {
        //逻辑删除，管理员可回收
        boolean flag = articleService.removeById(id);
        if (flag) {
            redisTemplate.delete(ARTICLE_PREFIX_NAME + id);
            return Result.success("删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }
}
