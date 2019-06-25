package cn.x2yu.blog.util;

import cn.x2yu.blog.dto.ArticleDto;
import cn.x2yu.blog.dto.ArticlePages;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageUtil {

    //传入页码 文章总数 载体对象
    public void artclePages(Integer pageNum, Integer total, PageInfo pageInfo){

        final Integer count = 2;


        Integer first = 1;
        Integer last ;
        Integer previous;
        Integer next = pageNum;


        if(total%count==0){
            last = total/count;
        }else{
            last = (int)(Math.floor(total/count)+1);
        }

        if(next+1 >= last){
            next = last;
        }else{
            next = pageNum+1;
        }

        if(pageNum-1>0){
            previous = pageNum-1;
        }else{
            previous = 1;
        }

        pageInfo.setPrePage(previous);
        pageInfo.setNextPage(next);
        pageInfo.setFirstPage(first);
        pageInfo.setLastPage(last);
        pageInfo.setPageNum(pageNum);

    }
}
