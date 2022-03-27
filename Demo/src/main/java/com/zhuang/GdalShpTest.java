package com.zhuang;

import org.gdal.ogr.*;
import org.gdal.ogr.Driver;
import org.gdal.gdal.*;

public class GdalShpTest {
    public static void main(String[] args) {
        // 注册所有的驱动
        ogr.RegisterAll();
        // 为了支持中文路径，请添加下面这句代码
        gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8","YES");
        // 为了使属性表字段支持中文，请添加下面这句
        gdal.SetConfigOption("SHAPE_ENCODING","");

        String strVectorFile = "/Users/mrzhuang/Downloads/shp/J50B001001/TERL.shp";
        //打开文件
        DataSource ds = ogr.Open(strVectorFile,0);
        if (ds == null)
        {
            System.out.println("打开文件失败！" );
            return;
        }
        System.out.println("打开文件成功！" );
        Driver dv = ogr.GetDriverByName("GeoJSON");
        if (dv == null)
        {
            System.out.println("打开驱动失败！" );
            return;
        }
        System.out.println("打开驱动成功！" );
        dv.CopyDataSource(ds, "/Users/mrzhuang/Downloads/test/node.json");
        System.out.println("转换成功！" );
    }
}