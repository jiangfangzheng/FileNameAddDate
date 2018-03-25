
import java.io.File;
import java.text.SimpleDateFormat;

/**
 * @author Sandeepin
 * 2018/3/25 0025
 */
public class RenameUtil {

    public static boolean addFileDataToFileName(String fileDirPath) {
        if (fileDirPath==null){
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
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String lastModTime = formatter.format(tempList[i].lastModified());
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
}
