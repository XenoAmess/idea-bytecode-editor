package com.github.pshirshov.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import com.github.pshirshov.ByteCodeFileEditor;
import com.github.pshirshov.util.IdeaUtils;
import com.github.pshirshov.vfs.DisassembledVirtualFile;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import kotlin.text.Charsets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Slf4j
@AllArgsConstructor
public class ExportAssembledClassAction extends AnAction {

    @Getter
    @NotNull
    private final ByteCodeFileEditor byteCodeFileEditor;

    @Getter
    @Nullable
    private final Project project;

    @Getter
    @NotNull
    private final DisassembledVirtualFile virtualFile;


    @Override
    public void update(AnActionEvent e) {
        e.getPresentation().setVisible(true);
        e.getPresentation().setIcon(AllIcons.ToolbarDecorator.Export);
        e.getPresentation().setDescription("Compile into class file");
    }


    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        VirtualFile selection = getExportClassFileDestination();
        if (selection == null) {
            return;
        }
        final String destDir = selection.getPath();
        try {
            log.info("Assembling classfile into " + destDir);
            ApplicationManager.getApplication().runWriteAction(new Runnable() {
                @Override
                public void run() {
                    try {
                        // TODO fixed charset at utf-8? can we read it from selection?
                        // TODO maybe can't because class file have no charset?
                        virtualFile.setBinaryContent(byteCodeFileEditor.getText().getBytes(Charsets.UTF_8));
                        try (
                                final InputStream inputStream = new ByteArrayInputStream(virtualFile.getContent());
                                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ) {
                            virtualFile.getDisassembleStrategyEnum().getAssembler().assemble(
                                    inputStream,
                                    byteArrayOutputStream
                            );
                            try (
                                    final OutputStream outputStream =
                                            getExportClassFileDestinationOutputStream(selection)
                            ) {
                                byteArrayOutputStream.writeTo(outputStream);
                            }
                        }
                    } catch (Exception e) {
                        log.error(ExportAssembledClassAction.this.getClass().getName() + "  failed", e);
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (Throwable e) {
            IdeaUtils.showErrorNotification("Assembler failed", e);
        }
    }

    @Nullable
    protected VirtualFile getExportClassFileDestination() {
        final FileChooserDescriptor fcd =
                new FileChooserDescriptor(false, true, false, false, false, false);
        return FileChooser.chooseFile(fcd, project, null);
    }

    @NotNull
    protected OutputStream getExportClassFileDestinationOutputStream(
            @NotNull VirtualFile selection
    ) throws IOException {
        return new FileOutputStream(
                Paths.get(selection.getPath()).resolve(
                        virtualFile.getPresentableName()
                ).toFile().getCanonicalPath()
        );
    }

}
