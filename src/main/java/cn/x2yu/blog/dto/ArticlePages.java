package cn.x2yu.blog.dto;

import java.util.List;

//文章首页分页对象
public class ArticlePages {

    private int first;//首页
    private int last;//尾页
    private int previous;//上一页
    private int next;//下一页
    private int pageNum;//当前页

    private List<ArticleDto> articleDtos;//文章内容

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<ArticleDto> getArticleDtos() {
        return articleDtos;
    }

    public void setArticleDtos(List<ArticleDto> articleDtos) {
        this.articleDtos = articleDtos;
    }
}
