package com.github.pshirshov.action;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.Icon;

import com.github.pshirshov.ByteCodeFileEditor;
import com.github.pshirshov.vfs.DisassembledVirtualFile;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Slf4j
public class ExportAssembledClassToOriginalClassFileAction extends ExportAssembledClassAction {

    @Override
    public void update(AnActionEvent e) {
        if (this.getVirtualFile().getDisassembleStrategyEnum().getAssembler() == null) {
            e.getPresentation().setVisible(false);
            return;
        }
        e.getPresentation().setVisible(true);
        try {
            e.getPresentation().setIcon((Icon) AllIcons.Actions.class.getDeclaredField("MenuSaveall").get(null));
        } catch (Exception exception) {
            try {
                e.getPresentation().setIcon((Icon) AllIcons.Actions.class.getDeclaredField("Menu_saveall").get(null));
            } catch (Exception ignored) {
            }
            log.error("update failed", exception);
        }

        e.getPresentation().setDescription("Save");
    }

    public ExportAssembledClassToOriginalClassFileAction(
            @NotNull ByteCodeFileEditor byteCodeFileEditor,
            @Nullable Project project,
            @NotNull DisassembledVirtualFile virtualFile
    ) {
        super(
                byteCodeFileEditor,
                project,
                virtualFile
        );
    }

    @Override
    @NotNull
    protected VirtualFile getExportClassFileDestination() {
        return this.getVirtualFile().getOriginalClassVirtualFile();
    }

    @Override
    @NotNull
    protected OutputStream getExportClassFileDestinationOutputStream(
            @NotNull VirtualFile selection
    ) throws IOException {
        return this.getVirtualFile().getOriginalClassVirtualFile().getOutputStream(this);
    }

}
