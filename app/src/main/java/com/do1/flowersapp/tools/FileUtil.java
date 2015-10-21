package com.do1.flowersapp.tools;

/**
 * Created by Administrator on 2015/7/17.
 */

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class FileUtil {

    public static final String CLASS_NAME  = "FileUtil";

    /**
     * 字节缓冲区长度
     */
    public static final int    BUFFER_SIZE = 8192;

    /**
     * @Methods: getFileSize
     * @Description: 获取文件或文件夹的大小，包含子文件夹也可以
     * @param f
     *            File 实例
     * @return 文件夹大小，单位：字节
     * @throws Exception
     * @throws
     */
    public static long getFileSize(File f) {
        if (f == null || !f.exists())
            return 0;
        if(!f.isDirectory())
            return f.length();
        long size = 0;
        File flist[] = f.listFiles();
        if(flist != null) {
            for (int i = 0; i < flist.length; i++) {
                if (flist[i].isDirectory()) {
                    size = size + getFileSize(flist[i]);
                } else {
                    size = size + flist[i].length();
                }
            }
        }
        return size;
    }

    /**
     * 获取具体文件的大小
     * @param absPath
     * @return
     */
    public static long getFilePathSize(String absPath) {
        if(TextUtils.isEmpty(absPath))
            return 0;
        File mFile = new File(absPath);
        return getFileSize(mFile);
    }

    public static boolean exists(String path) {
        return new File(path).exists();
    }

    public static boolean createDir(String dirPath) {
        File file = new File(dirPath);
        if (file.exists()) {
            if (file.isDirectory()) {
                return true;
            } else {
                file.delete();
            }
        }
        try {
            return file.mkdirs();
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean createFile(String dirPath, String filePath) {
        File file = new File(filePath);
        if (file.exists()) { return false; }
        try {
            if (!createDir(dirPath)) return false;

            return file.createNewFile();
        } catch (Exception e) {
            // Logger.e("create file error ", e);
            // 无法创建文件，严重错误，影响很大，出现该错误，程序可能不知道如何继续执行，客户端严重错误
            e.printStackTrace();
        }
        return false;
    }

    public static boolean createFile(String paramString) {
        File f = new File(paramString);
        if (f.exists()) { return false; }

        try {
            return f.createNewFile();
        } catch (Exception e) {
            // 无法创建文件，严重错误，影响很大，出现该错误，程序可能不知道如何继续执行，客户端严重错误
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param path
     *            can be file or dir
     */
    public static void delete(String path) {
        if (path == null) return;
        File file = new File(path);
        if ((file == null) || (!file.exists())) { return; }

        if (file.isDirectory()) {
            File[] arrayOfFile = file.listFiles();
            for (int i = 0; i < arrayOfFile.length; i++) {
                delete(arrayOfFile[i].toString());
            }
        }
        file.delete();
    }

    /**
     * 删除目录（不包括目录本身）下的文件及目录
     *
     * @param path
     */
    public static void deleteSubFile(String path) {
        if (path == null) return;
        File file = new File(path);
        if ((file == null) || (!file.exists())) { return; }

        if (file.isDirectory()) {
            File[] arrayOfFile = file.listFiles();
            for (int i = 0; i < arrayOfFile.length; i++) {
                delete(arrayOfFile[i].toString());
            }
        }
    }

    public static void rename(String path, String reNamePath) {
        if ((path == null) || (reNamePath == null)) return;
        File file = new File(path);
        if (!file.exists()) { return; }
        File reNameFile = new File(reNamePath);
        file.renameTo(reNameFile);
    }

    /**
     *
     * @param srcFilePath
     *            原始文件路径
     * @param dstFilePath
     *            目标文件路径
     * @param forced
     *            是否强制覆盖
     * @return
     */
    public static boolean copyFile(String srcFilePath, String dstFilePath, boolean forced) {
        if ((srcFilePath == null) || (dstFilePath == null)) return false;
        File srcFile = new File(srcFilePath);
        if (!srcFile.exists()) { return false; }
        try {
            File dstFile = new File(dstFilePath);
            if ((!forced) && (dstFile.exists())) { return false; }
            if ((!dstFile.exists()) && (!dstFile.createNewFile())) return false;

            FileInputStream fileInputStream = new FileInputStream(srcFile);
            FileOutputStream fileOutputStream = new FileOutputStream(dstFile);
            byte[] arrayOfByte = new byte[BUFFER_SIZE];
            int i = -1;
            while ((i = fileInputStream.read(arrayOfByte)) > 0) {
                fileOutputStream.write(arrayOfByte, 0, i);

            }
            fileInputStream.close();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            // 拷贝文件失败，暂时未见调用，由于其重要程度，暂定为客户端严重错误
            e.printStackTrace();
        }
        return false;
    }

    /***
     * 拷贝成功返回File对象，拷贝失败返回空指针
     *
     * @param inputStream
     * @param dstFilePath
     * @param forceOverride
     * @return
     * @throws IOException
     */
    public static File copyFile(InputStream inputStream, String dstFilePath, boolean forceOverride) throws IOException {
        if ((inputStream == null) || (dstFilePath == null)) { return null; }
        File dstFile = new File(dstFilePath);
        if ((!forceOverride) && (dstFile.exists())) { return null; }
        if ((!dstFile.exists()) && (!dstFile.createNewFile())) return null;

        byte[] arrayOfByte = new byte[BUFFER_SIZE];
        FileOutputStream ouputStream = new FileOutputStream(dstFile);
        int i = -1;
        while ((i = inputStream.read(arrayOfByte)) > 0) {
            ouputStream.write(arrayOfByte, 0, i);

        }
        inputStream.close();
        ouputStream.close();
        return dstFile;
    }

    public static boolean copyFile(byte[] srcFileBytes, String dstFilePath, boolean forced) {
        if ((srcFileBytes == null) || (dstFilePath == null)) return false;
        try {
            File dstFile = new File(dstFilePath);
            if ((!forced) && (dstFile.exists())) { return false; }
            if ((!dstFile.exists()) && (!dstFile.createNewFile())) return false;
            FileOutputStream fileOutPutStream = new FileOutputStream(dstFile);
            fileOutPutStream.write(srcFileBytes);
            fileOutPutStream.close();
            return true;
        } catch (Exception e) {
            // 拷贝文件出错，暂时未见调用，由于其重要程度，暂定为客户端严重错误
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 复制文件夹
     *
     * @param srcDir
     * @param dstDir
     * @param forced
     * @return
     */
    public static boolean copyDirectory(String srcDir, String dstDir, boolean forced) {
        String[] aFileNames;
        String aFileName;
        String aFilePath;
        String tmpDstBasePath;
        InputStream aInput;
        try {
            FileUtil.createDir(dstDir);
            File src = new File(srcDir);

            aFileNames = src.list();
            for (int i = 0, len = aFileNames.length; i < len; i++) {
                aFileName = aFileNames[i];

                aFilePath = srcDir + File.separator + aFileName;
                tmpDstBasePath = dstDir + File.separator + aFileName;
                boolean isDir = new File(aFilePath).isDirectory();
                if (isDir) {
                    // 目录
                    copyDirectory(aFilePath, tmpDstBasePath, true);
                } else {
                    // 文件
                    aInput = new FileInputStream(aFilePath);
                    FileUtil.copyFile(aInput, tmpDstBasePath, true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /***
     * 获取最近一次修改时间
     *
     * @param path
     * @return
     */
    public static long getLastModified(String path) {
        File file = new File(path);
        if (file.exists()) { return file.lastModified(); }
        return 0;
    }

    /***
     * @param file
     * @return 返回字符串
     * @throws IOException
     *             发生文件不存在或者读取错误的情况下
     */
    public static String readFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        is.close();
        return new String(buffer);
    }

    public static byte[] readFileBytes(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes;
        byte[] buffer = new byte[8192];
        int byteRead = -1;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            while ((byteRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, byteRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = output.toByteArray();
        return bytes;
    }

    public static long getDirSizeByPath(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            return getDirSize(dir);
        } else {
            return 0;
        }
    }

    public static long getDirSize(File dir) {
        if (dir == null) { return 0; }
        if (!dir.isDirectory()) { return 0; }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 如果遇到目录则通过递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 写入properties信息
     *
     * @param filePath
     *            propeties 文件路径
     * @param parameterName
     *            属性名称
     * @param parameterValue
     *            属性值
     * @return
     */
    public static boolean writeProperty(String filePath, String parameterName, String parameterValue) {
        Properties prop = new Properties();
        try {
            File file = getSdcardFile(filePath);
            InputStream fis = new FileInputStream(file);
            prop.load(fis);
            OutputStream fos = new FileOutputStream(file);
            prop.setProperty(parameterName, parameterValue);
            prop.store(fos, "Update '" + parameterName + "' value");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String READ_VALUE_FORMAT = "%s : %s";

    /**
     * 根据key读取value
     *
     * @param filePath
     * @param key
     * @return
     */
    public static String readPropertiy(String filePath, String key) {
        Properties props = new Properties();
        try {
            File file = getSdcardFile(filePath);
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            props.load(in);
            String value = props.getProperty(key);
            Log.d("zxy", String.format(READ_VALUE_FORMAT, key, value));
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 根据path 返回一个File对象
     *
     * @param path
     * @return
     */
    public static File getSdcardFile(String path) {
        File setting = new File(path);
        if (!setting.exists()) {
            try {
                setting.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return setting;
    }

    /**
     * 写序列化对象到指定文件
     * @param filePath
     * @param obj
     * @return
     */
    public static boolean writeObjectToFile(String filePath, Object obj) {
        FileOutputStream os = null;
        ObjectOutputStream oo = null;
        try {
            makeDirs(filePath);
            os = new FileOutputStream(filePath);
            oo = new ObjectOutputStream(os);
            oo.writeObject(obj);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                if(oo != null)
                    oo.close();
                if(os != null)
                    os.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * 读取文件并转为对象
     * @param filePath
     * @return
     */
    public static Object readObjectFromFile(String filePath){
        FileInputStream freader = null;
        ObjectInputStream oi = null;
        try {
            freader = new FileInputStream(filePath);
            oi = new ObjectInputStream(freader);
            return oi.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(oi != null)
                    oi.close();
                if(freader != null)
                    freader.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 从文件路径里获取去除掉后缀名的文件名
     * @param filePath
     * @return
     */
    public static String getFileNameWithoutExtension(String filePath) {
        return getFilePathWithoutExtension(getFileName(filePath));
    }

    /**
     * 获取去除掉文件名后缀的文件路径
     * @param filePath
     * @return
     */
    public static String getFilePathWithoutExtension(String filePath) {
        if (TextUtils.isEmpty(filePath))
            return filePath;

        int extenPosi = filePath.lastIndexOf(".");
        if (extenPosi == -1)
            return filePath;
        return filePath.substring(0, extenPosi);
    }

    /**
     * 从文件路径里获取文件名 ，含文件后缀名
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath))
            return filePath;

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
    }

    /**
     * 获取该文件的所属上级文件夹
     * @param filePath
     * @return
     */
    public static String getFolderName(String filePath) {
        if (TextUtils.isEmpty(filePath))
            return filePath;

        File file = new File(filePath);
        if(file.exists() && file.isDirectory())
            return filePath;

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

    /**
     * 获取该文件路径的文件后缀名
     * @param filePath
     * @return
     */
    public static String getFileExtension(String filePath) {
        if (TextUtils.isEmpty(filePath))
            return filePath;

        int extenPosi = filePath.lastIndexOf(".");
        int filePosi = filePath.lastIndexOf(File.separator);
        if (extenPosi == -1)
            return "";
        return (filePosi >= extenPosi) ? "" : filePath.substring(extenPosi + 1);
    }

    /**
     * 创建文件夹
     */
    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);

        if (TextUtils.isEmpty(folderName))
            return false;

        File folder = new File(folderName);
        if(folder.exists() && folder.isDirectory())
            return true;

        return folder.mkdirs();
    }

    /**
     * 创建文件夹
     */
    public static boolean makeDirs(File folder) {
        if(folder.exists() && folder.isDirectory())
            return true;

        return folder.mkdirs();
    }


    /**
     * 判断制定文件路径的文件或文件夹是否存在
     *
     * @param filePath
     * @return
     */
    public static boolean isFileExist(String filePath) {
        if(TextUtils.isEmpty(filePath)) {
            return false;
        }

        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 判断制定文件路径的文件或文件夹是否存在
     *
     * @param file
     * @return
     */
    public static boolean isFileExist(File file) {
        if(file != null)
            return isFileExist(file.getAbsolutePath());
        return false;
    }

    /**
     * 删除文件或文件夹
     * @param path
     * @return
     */
    public static boolean deleteFile(String path) {
        if(TextUtils.isEmpty(path))
            return false;
        File file = new File(path);
        if (!file.exists())
            return true;

        if (file.isFile())
            return file.delete();

        try {
            File[] files = file.listFiles();
            if(files != null) {
                for(int i = 0; i < files.length; i++) {
                    if(!deleteFile(files[i].getAbsolutePath()))
                        return false;
                }
            }
            return file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 文件转Base64字符串
     * @param filename
     * @return
     */
    public static String fileConvertBase64String(String filename) {
        String str = null;
        try {
            File file = new File(filename);
            if (file.exists()) {
                str = Base64.encode(readFileBytes(file));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }
}