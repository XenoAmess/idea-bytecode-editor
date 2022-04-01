package com.github.pshirshov.conversion.impl.krakatau;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;

import com.intellij.openapi.application.PathManager;
import com.intellij.util.download.DownloadableFileDescription;
import com.intellij.util.download.DownloadableFileService;
import org.jetbrains.annotations.NotNull;
import org.python.util.PythonInterpreter;

public class KrakatauFetchUtil {

    public static String KRAKATAU_JAVA_WRAPPER_VERSION = "0.0.1";

    public static volatile Class<?> KRAKATAU_UTIL_CLASS;

    @NotNull
    public static synchronized Class<?> assureKrakatauDownloaded() throws MalformedURLException,
            ClassNotFoundException {
        if (KRAKATAU_UTIL_CLASS != null) {
            return KRAKATAU_UTIL_CLASS;
        }
        File file = new File(
                PathManager.getSystemPath(),
                "download-cache/krakatau_java_wrapper-" + KRAKATAU_JAVA_WRAPPER_VERSION + ".jar"
        );
        if (file.exists()) {
            return assertKrakatauLoaded(file);
        }
        String downloadUrlString =
                "https://repo1.maven.org/maven2/com/xenoamess/krakatau_java_wrapper/"
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
                PathManager.getSystemPath() + "/download-cache",
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
//                        file.getAbsoluteFile().toURI().toURL()
                },
                KrakatauFetchUtil.class.getClassLoader()
        );
        KRAKATAU_UTIL_CLASS = Class.forName(
                "com.xenoamess.krakatau_java_wrapper.util.KrakatauUtil",
                true,
                urlClassLoader
        );
        try (
                PythonInterpreter pythonInterpreter = new PythonInterpreter()
        ) {
            pythonInterpreter.exec("import sys");
            pythonInterpreter.exec("sys.path.append(\"" + file.getAbsolutePath().replace("\\\\", "/") + "\")");
        }
        return KRAKATAU_UTIL_CLASS;
    }

}
