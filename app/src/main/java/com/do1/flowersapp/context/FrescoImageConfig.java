package com.do1.flowersapp.context;

import android.content.Context;

import com.do1.flowersapp.tools.FileUtil;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;

import java.io.File;

/**
 * Created by gufeng on 2015/7/28.
 */
public class FrescoImageConfig {

    public static void initialize(Context context) {
        Fresco.initialize(context, getImagePipelineConfig(context));
    }

    private static ImagePipelineConfig getImagePipelineConfig(Context context) {
        ImagePipelineConfig.Builder configBuilder = ImagePipelineConfig.newBuilder(context);
        configureCaches(configBuilder, context);
        configureLoggingListeners(configBuilder);
        return configBuilder.build();
    }

    /**
     * Configures disk and memory cache not to exceed common limits
     */
    private static void configureCaches(ImagePipelineConfig.Builder configBuilder, Context context) {
        String cacheDir = AppConstant.getFrescoCacheDir();
        FileUtil.createDir(cacheDir);
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
                ConfigConstants.MAX_MEMORY_CACHE_SIZE, // Max total size of elements in the cache
                Integer.MAX_VALUE,                     // Max entries in the cache
                ConfigConstants.MAX_MEMORY_CACHE_SIZE, // Max total size of elements in eviction queue
                Integer.MAX_VALUE,                     // Max length of eviction queue
                Integer.MAX_VALUE);                    // Max cache entry size
        configBuilder.setBitmapMemoryCacheParamsSupplier(
                new Supplier<MemoryCacheParams>() {
                    public MemoryCacheParams get() {
                        return bitmapCacheParams;
                    }
                })
                .setMainDiskCacheConfig(
                        DiskCacheConfig.newBuilder()
                                .setBaseDirectoryPath(new File(cacheDir))
                                .setBaseDirectoryName("imagepipeline_cache")
                                .setMaxCacheSize(ConfigConstants.MAX_DISK_CACHE_SIZE)
                                .build());
    }

    private static void configureLoggingListeners(ImagePipelineConfig.Builder configBuilder) {
        configBuilder.setRequestListeners(Sets.newHashSet((RequestListener) new RequestLoggingListener()));
    }
}