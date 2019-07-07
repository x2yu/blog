package cn.x2yu.blog.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UploadAndDeleteFile {

    @Autowired
    ReadMd readMd;

    private static final String filePath = "E:/blog/src/main/resources/static/images/featured-post/";

    private static final String articleImgPath = "E:/blog/src/main/resources/static/images/masonary-post/";

    private static final String articleMdPath = "E:/blog/src/main/resources/markdown/";

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

    /*----------------------------*/

    /**
     * 新增文章题图
     * */
    public void articleUpload(Long articleId,MultipartFile articleImg){

        String fileName = (articleId+".jpg");

        File img = new File(articleImgPath+fileName);

        try{
            //项目运行实际目录下的储存位置
            String classesPath= ResourceUtils.getURL("classpath:").getPath()+"/static/images/masonary-post/";
            File classesImg = new File(classesPath+fileName);
            //然后复制到实际运行的文件夹中
            FileUtils.copyInputStreamToFile(articleImg.getInputStream(),classesImg);

            //存入项目文件夹
            articleImg.transferTo(img);


        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

    /**
     * 删除文章图片
     * */
    public void articleDelete(Long articleId){
        String fileName = (articleId+".jpg");
        File img = new File(articleImgPath+fileName);
        try {
            //项目运行实际目录下的储存位置
            String classesPath= ResourceUtils.getURL("classpath:").getPath()+"/static/images/masonary-post/";
            File classesImg = new File(classesPath+fileName);

            if(img.exists()&&classesImg.exists()){
                img.delete();
                classesImg.delete();
            }
        }catch(IOException e){
            System.out.println(e.toString());
        }
    }

    /**
     * 更新文章题图
     * */
    public void articleUpdate(Long articleId,MultipartFile articleImg){

        //删除旧图片
        articleDelete(articleId);
        //储存新的图片
        articleUpload(articleId,articleImg);

    }

    /**
     * 更新文章内容
     * */
    public void articleContentUpdate(String title,String content){

        //根据Md5值判断文章内容是否改变，有改变才更新
        String contentMd5 = DigestUtils.md2Hex(content);
        System.out.println("新文件的md5:  "+contentMd5);

        try{
            String fileName = title+".md";

            String oldContent = readMd.readMdFile(fileName);
            String Md5o = DigestUtils.md2Hex(oldContent);
            System.out.println("老文件的md5:  "+Md5o);

            //如果不相等则有更新
            if(!contentMd5.equals(Md5o)){
              //先删除旧文件
                articleContentDelete(fileName);

              //创建新的同名文件
                articleMdUpload(title,content);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * 删除文章md载体文件
     * */
    public void articleContentDelete(String fileName){

        File mdFile = new File(articleMdPath+fileName);

        try{
            //项目运行实际目录下Md文件的储存位置

            Resource resource = new ClassPathResource("markdown/"+fileName);
            File file = resource.getFile();

            if(file.exists()&&mdFile.exists()){
                mdFile.exists();
                file.delete();
            }

        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

    /**
     * 编辑时
     * 新增文章Md
     * */
    public void articleMdUpload(String title,String content){

        String fileName = title+".md";
        File mdFile = new File(articleMdPath+fileName);

        try{
            if(!mdFile.exists()){
                mdFile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(mdFile);
            fos.write(content.getBytes());

            //真实运行的文件夹
            String classesPath= ResourceUtils.getURL("classpath:").getPath()+"/markdown/";
            File classesMdFile = new File(classesPath+fileName);

            //然后复制到实际运行的文件夹中
            FileOutputStream classfos = new FileOutputStream(classesMdFile);
            classfos.write(content.getBytes());

            fos.close();
            classfos.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * 储存上传的md文件
     * */
    public void saveMdFile(String fileName,MultipartFile mdFile){

        File articleMd = new File(articleMdPath+fileName);

        try{
            //项目运行实际目录下的储存位置
            String classesPath= ResourceUtils.getURL("classpath:").getPath()+"/markdown/";
            File classesMd = new File(classesPath+fileName);
            //然后复制到实际运行的文件夹中
            FileUtils.copyInputStreamToFile(mdFile.getInputStream(),classesMd);

            //存入项目文件夹
            mdFile.transferTo(articleMd);

        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

}
