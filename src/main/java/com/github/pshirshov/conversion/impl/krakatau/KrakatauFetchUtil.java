package com.github.pshirshov.conversion.impl.krakatau;

import com.intellij.openapi.application.PathManager;
import com.intellij.util.download.DownloadableFileDescription;
import com.intellij.util.download.DownloadableFileService;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;

public class KrakatauFetchUtil {

    public static String KRAKATAU_JAVA_WRAPPER_VERSION = "0.0.4-SNAPSHOT";

    public static volatile Class<?> KRAKATAU_UTIL_CLASS;

    private static final String MAVEN_STABLE_REPO_HEADER = "https://repo1.maven.org/maven2/com/xenoamess/krakatau_java_wrapper/";

    private static final String MAVEN_SNAPSHOT_REPO_HEADER = "https://oss.sonatype.org/content/repositories/snapshots/com/xenoamess/krakatau_java_wrapper/";

    @NotNull
    public static synchronized Class<?> assureKrakatauDownloaded() throws MalformedURLException,
            ClassNotFoundException {
        if (KRAKATAU_UTIL_CLASS != null) {
            return KRAKATAU_UTIL_CLASS;
        }
        File file = new File(
                PathManager.getSystemPath(),
                "download-cache/idea-bytecode-editor/krakatau_java_wrapper-" + KRAKATAU_JAVA_WRAPPER_VERSION + ".jar"
        );
        if (file.exists()) {
            return assertKrakatauLoaded(file);
        }
        String downloadUrlString =
                KRAKATAU_JAVA_WRAPPER_VERSION.endsWith("-SNAPSHOT") ? MAVEN_SNAPSHOT_REPO_HEADER : MAVEN_STABLE_REPO_HEADER
                        + KRAKATAU_JAVA_WRAPPER_VERSION
                        + "/krakatau_java_wrapper-"
                        + KRAKATAU_JAVA_WRAPPER_VERSION
                        + ".jar";
        DownloadableFileService downloader = DownloadableFileService.getInstance();
        DownloadableFileDescription description =
                downloader.createFileDescription(
                        downloadUrlString,
                        "krakatau_java_wrapper-" + KRAKATAU_JAVA_WRAPPER_VERSION + ".jar"
                );
        downloader.createDownloader(
                Collections.singletonList(description),
                "krakatau_java_wrapper-" + KRAKATAU_JAVA_WRAPPER_VERSION + ".jar"
        ).downloadFilesWithProgress(
                PathManager.getSystemPath() + "/download-cache/idea-bytecode-editor",
                null,
                null
        );
        return assertKrakatauLoaded(file);
    }

    @NotNull
    private static synchronized Class<?> assertKrakatauLoaded(@NotNull File file) throws MalformedURLException,
            ClassNotFoundException {
        if (KRAKATAU_UTIL_CLASS != null) {
            return KRAKATAU_UTIL_CLASS;
        }
        URLClassLoader urlClassLoader = new URLClassLoader(
                new URL[]{
                        file.getAbsoluteFile().toURI().toURL()
                },
                KrakatauFetchUtil.class.getClassLoader()
        );
        KRAKATAU_UTIL_CLASS = Class.forName(
                "com.xenoamess.krakatau_java_wrapper.util.KrakatauUtil",
                true,
                urlClassLoader
        );
        return KRAKATAU_UTIL_CLASS;
    }

}
