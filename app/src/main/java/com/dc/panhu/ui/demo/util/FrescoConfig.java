package com.dc.panhu.ui.demo.util;
import android.content.Context;
import android.graphics.Bitmap;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;


public class FrescoConfig {

    private final int MAX_DISK_CACHE_VERYLOW_SIZE = 10 * ByteConstants.MB;//默认图极低磁盘空间缓存的最大值
    private final int MAX_DISK_CACHE_LOW_SIZE = 50 * ByteConstants.MB;//默认图低磁盘空间缓存的最大值
    private final int MAX_DISK_CACHE_SIZE = 80 * ByteConstants.MB;//默认图磁盘缓存的最大值

    public void initFrescoConfig(Context context) {

        File baseDirectory = getBaseDirectory(context);

        //小图片的磁盘配置
        DiskCacheConfig diskSmallCacheConfig = DiskCacheConfig.newBuilder(context
                .getApplicationContext())
                .setBaseDirectoryPath(baseDirectory)//缓存图片基路径
                .setBaseDirectoryName("s_image_cache")//文件夹名
                .build();


        //默认图片的磁盘配置
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(context
                .getApplicationContext())
                .setBaseDirectoryPath(baseDirectory)//缓存图片基路径
                .setBaseDirectoryName("image_cache")//文件夹名
                .setMaxCacheSize(MAX_DISK_CACHE_SIZE)//默认缓存的最大大小。
                .setMaxCacheSizeOnLowDiskSpace(MAX_DISK_CACHE_LOW_SIZE)//缓存的最大大小,使用设备时低磁盘空间。
                .setMaxCacheSizeOnVeryLowDiskSpace(MAX_DISK_CACHE_VERYLOW_SIZE)//缓存的最大大小,当设备极低磁盘空间
                .build();


        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(context)
                // .setBitmapMemoryCacheParamsSupplier(mSupplierMemoryCacheParams)
                // 内存缓存配置（一级缓存，已解码的图片）
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .setMainDiskCacheConfig(diskCacheConfig)//磁盘缓存配置（总，三级缓存）
                .setSmallImageDiskCacheConfig(diskSmallCacheConfig)//磁盘缓存配置（小图片，可选～三级缓存的小图优化缓存）
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(context.getApplicationContext(), imagePipelineConfig);
    }

    private File getBaseDirectory(Context context) {
        return new tv.xiaoka.base.util.FileUtil().externalMemoryAvailable() ? context.getExternalCacheDir() : context.getCacheDir();
    }

}