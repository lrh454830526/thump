//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//               佛祖保佑         永无BUG

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author ecoman
 * @Date 2019/8/1015:16
 * @Version 1.0
 * @Description TODO
 **/

public class Main {

    private static String path = System.getProperty("user.dir");
    private static String targetPath = path + "\\thump";

    private static final double DEFAULT_RATE = 0.7;

    private static String[] PICTURE_SUFFIX = {"jpg", "png", "bmp", "jpeg"};
    private static String TRUE = "true";
    private static String FALSE = "false";
    private static int count = 0;
    private static double rate = 0.7;
    private static boolean together;

    public static void main(String[] args) {

        if (args.length != 0 && isNumeric(args[0])) {
            rate = Double.valueOf(args[0]);
        }

        rate = getRate(args);
        together = getTogether(args);
        if (!exist(path)) {
            System.out.println("the directory does't exist");
            System.exit(1);
        }

        System.out.println("start to work----------------------");
        thumpDir(path, targetPath, together);
        System.out.println("total " + count + " pictures thump");
    }


    public static boolean getTogether(String argc[]) {
        if (argc.length != 0) {
            for (String str : argc) {
                if (TRUE.equalsIgnoreCase(str)) {
                    return true;
                }
                if (FALSE.equalsIgnoreCase(str)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static double getRate(String argc[]) {
        if (argc.length != 0) {
            for (String str : argc) {
                if (isNumeric(str)) {
                    return Double.parseDouble(str);
                }
            }
        }
        return DEFAULT_RATE;
    }

    public static boolean isNumeric(String str) {
        try {
            new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }
        return true;
    }

    public static void thumpDir(String sourcePath, String targetPath, boolean putTogether) {
        File sourceFile = new File(sourcePath);

        if (!sourceFile.exists()) {
            return;
        }

        if (sourceFile.isFile() && isImage(sourcePath)) {
            thumpPicture(sourcePath, targetPath);
        }

        File[] listFile = sourceFile.listFiles();

        if (listFile == null) {
            return;
        }

        for (File file : listFile) {
            if (putTogether) {
                thumpDir(file.getAbsolutePath(), Main.targetPath + File.separator + file.getName(), putTogether);
            } else {
                thumpDir(file.getAbsolutePath(), targetPath + File.separator + file.getName(), putTogether);
            }

        }
    }

    public static boolean isImage(String target) {
        if (target == null || target.length() == 0) {
            return false;
        }
        for (String s : PICTURE_SUFFIX) {
            if (target.toLowerCase().endsWith(s)) {
                return true;
            }
        }
        return false;
    }

    public static void thumpPicture(String sourcePath, String targetPath) {
        System.out.println("------------------------------------");
        System.out.println(targetPath + "--------------- success");
        System.out.println("------------------------------------");
        mkdirFile(targetPath);
        try {
            Thumbnails.of(sourcePath).
                    outputQuality(rate).
                    scale(rate).
                    toFile(targetPath);
            count++;
        } catch (IOException e) {
            return;
        }
    }

    /**
     * 创建文件夹
     *
     * @param filePath
     */
    public static void mkdirFile(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            if (!file.exists()) {
                file.mkdirs();
            }
        } else {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
        }
    }

    public static boolean exist(String str) {
        File file = new File(str);
        System.out.println(file.getAbsolutePath());
        return file.exists();
    }


}
