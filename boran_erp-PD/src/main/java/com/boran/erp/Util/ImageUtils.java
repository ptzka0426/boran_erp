package com.boran.erp.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author LT
 * @create 2021-05-20 16:30
 */
public class ImageUtils {
    /**
     * 图片格式：JPG
     */
    private static final String PICTRUE_FORMATE_JPG = "jpg";

    public static void main(String[] args) {
        File file = new File("C:\\Users\\LT\\Desktop\\新建文件夹\\");//图片文件目录
        List<String> path = new LinkedList<String>();
        for (File f : file.listFiles()) { //为了效果最好，图片数量最好是平方数
            System.out.println(f.getAbsolutePath());
            path.add(f.getAbsolutePath());
        }
        try {
            ImageUtils.getCombinationOfhead(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成组合头像
     *
     * @param paths 用户图像
     * @throws IOException
     */
    public static String getCombinationOfhead(List<String> paths)
            throws IOException {
        List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();

        int imgNumber = paths.size(); // 头像数量,为了效果最好，图片数量最好是平方数

        int PicHeightAndWidth = 1600; // 画板的宽高

        int gap = 22;//头像之间缝隙的宽度

        int numPerEdge = (int) Math.sqrt(imgNumber);//每条边头像的数量

        int heightOrWidth = (PicHeightAndWidth - gap * (numPerEdge + 1)) / numPerEdge;// 单个头像的长和宽
        for (int i = 0; i < paths.size(); i++) {
            bufferedImages.add(ImageUtils.resize2(paths.get(i), heightOrWidth, heightOrWidth, true));
        }

        BufferedImage outImage = new BufferedImage(PicHeightAndWidth, PicHeightAndWidth,
                BufferedImage.TYPE_INT_RGB);

        Graphics g = outImage.getGraphics();// 生成画布

        Graphics2D g2d = (Graphics2D) g;

        g2d.setBackground(new Color(255, 255, 255)); // 设置背景色

        g2d.clearRect(0, 0, PicHeightAndWidth, PicHeightAndWidth);// 通过使用当前绘图表面的背景色进行填充来清除指定的矩形。

        int numPerEdges = 0;
        //逐一绘制头像
        for (int i = 0; i < numPerEdge; i++) {
            for (int j = 0; j < numPerEdge; j++) {
                g2d.drawImage(bufferedImages.get(numPerEdges), (gap * i) + (i * heightOrWidth), (gap * j) + (heightOrWidth * j), null);
                numPerEdges++;
            }
        }


        /*String outPath = "C:\\Users\\LT\\Desktop\\新建文件夹\\b.jpg";

        String format = "JPG";

        ImageIO.write(outImage, format, new File(outPath));*/
        //MultipartFile_ImageToBase64.resizeImageTo40K(MultipartFile_ImageToBase64.BufferedImageToBase64(outImage));
        String img_base64=MultipartFile_ImageToBase64.BufferedImageToBase64(outImage);
        return img_base64;
    }

    /**
     * 图片缩放
     *
     * @param filePath 图片路径
     * @param height   高度
     * @param width    宽度
     * @param bb       比例不对时是否需要补白
     */
    public static BufferedImage resize2(String filePath, int height, int width,
                                        boolean bb) {
        try {
            double ratio = 0; // 缩放比例
            File f = new File(filePath);
            BufferedImage bi = ImageIO.read(f);
            f.delete();//删除图片
            Image itemp = bi.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue()
                            / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(
                        AffineTransform.getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null)) {
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                } else {
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                    g.dispose();
                    itemp = image;
                }
            }
            BufferedImage im = ((sun.awt.image.ToolkitImage) itemp).getBufferedImage();
            return (BufferedImage) im;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
