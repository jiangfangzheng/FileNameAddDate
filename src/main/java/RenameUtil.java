
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sandeepin
 * 2018/3/25 0025
 */
public class RenameUtil {

    public static boolean addFileDataToFileName(String fileDirPath) throws ParseException, IOException, ImageProcessingException {
        if (fileDirPath == null) {
            return false;
        }
        boolean b = false;
        File files = new File(fileDirPath);
        if (!files.exists()) {
//            files.mkdir();
            return false;
        }
        File[] tempList = files.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                // 获取文件修改日期
                String lastModTime = getDateTimeOriginal(tempList[i]);
                String oldName = tempList[i].toString();
                String newNameAddDate = lastModTime + "_" + tempList[i].getName();
                String newName = oldName.replace(tempList[i].getName(), newNameAddDate);
                b = renameFile(oldName, newName);
                if (b) {
                    System.out.println("老名字：" + oldName + " \t新名字：" + newName);
                }
            }
        }
        return b;
    }

    // 文件重命名
    private static boolean renameFile(String oldName, String newName) {
        // 新的文件名和以前文件名不同时，才有必要进行重命名
        if (!oldName.equals(newName)) {
            File oldfile = new File(oldName);
            File newfile = new File(newName);
            // 重命名文件不存在
            if (!oldfile.exists()) {
                return false;
            }
            // 若在该目录下已经有一个文件和新文件名相同，则不允许重命名
            if (newfile.exists()) {
                System.out.println(newName + "已经存在！");
                return false;
            } else {
                return oldfile.renameTo(newfile);
            }
        } else {
            System.out.println("新文件名和旧文件名相同...");
            return false;
        }
    }

    // 获取文件拍摄时间或最后修改时间
    private static String getDateTimeOriginal(File file) throws ImageProcessingException, IOException, ParseException {
        // 获取拍摄时间
        if(file.length() > 0 && (file.getName().contains("JP") || file.getName().contains("jp")  || file.getName().contains("MOV"))){
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    // 标签名
                    String tagName = tag.getTagName();
                    // 标签信息
                    String desc = tag.getDescription();
                    if ("Date/Time Original".equals(tagName)) {
                        System.out.println("拍摄时间(格式化前):" + desc);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                        Date date = sdf.parse(desc);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String originalTime = formatter.format(date);
                        System.out.println("拍摄时间:" + originalTime);
                        return originalTime;
                    }
                }
            }
        }
        // 没有则获取最后修改时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String lastModTime = formatter.format(file.lastModified());
        System.out.println("修改时间:" + lastModTime);
        return lastModTime;
    }

    public static void main(String[] args) throws ImageProcessingException, IOException, ParseException {
        File file = new File("E:\\相机\\2018年1~6月\\DCIM\\100APPLE\\2018-02-01_IMG_0014.JPG");
//        File file = new File("E:\\相机\\2018年1~6月\\DCIM\\100APPLE\\2018-02-10_IMG_0917.PNG");
        getDateTimeOriginal(file);
    }
}
