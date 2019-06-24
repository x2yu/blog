package cn.x2yu.blog.util;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadAndDeleteFile {

    private static final String filePath = "E:/blog/src/main/resources/static/images/featured-post/";

    /**
     * 新增分类图片
     * */
    public void  categoryUpload(Long categoryId,MultipartFile categoryImg){

       // String classesPath = "E:/blog/target/classes/static/images/featured-post/";

        String fileName = ("post-"+categoryId+".jpg");

        System.out.println("开始储存图片");
        File img = new File(filePath+fileName);

        try{
            //项目运行实际目录下的储存位置
             String classesPath= ResourceUtils.getURL("classpath:").getPath()+"/static/images/featured-post/";
             File classesImg = new File(classesPath+fileName);
            //然后复制到实际运行的文件夹中
             FileUtils.copyInputStreamToFile(categoryImg.getInputStream(),classesImg);

             //存入项目文件夹
             categoryImg.transferTo(img);


        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

   /**
    * 更新分类图片
    * */
    public void categoryUpdate(Long categoryId,MultipartFile categoryImg){
        if(categoryImg.isEmpty()){//图片默认不用更新
            return ;
        }
        //删除旧图片
        categoryDelete(categoryId);
        //储存新的图片
        categoryUpload(categoryId,categoryImg);

    }

    /**
     * 删除分类图片
     * */
    public void categoryDelete(Long categoryId){
        String fileName = ("post-"+categoryId+".jpg");
        File img = new File(filePath+fileName);
        try {
            //项目运行实际目录下的储存位置
            String classesPath= ResourceUtils.getURL("classpath:").getPath()+"/static/images/featured-post/";
            File classesImg = new File(classesPath+fileName);

            //先删除原来的文件 在存入新的
            if(img.exists()&&classesImg.exists()){
                img.delete();
                classesImg.delete();
            }
        }catch(IOException e){
            System.out.println(e.toString());
        }
    }
}
