import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.extension.nodeditor.NodeEditor;
import imgui.extension.nodeditor.NodeEditorConfig;
import imgui.extension.nodeditor.NodeEditorContext;
import imgui.flag.ImGuiWindowFlags;
import nodes.NodeEditorDisplay;

public class Main extends Application {
    private static final NodeEditorDisplay nodeEditor;

    static{
        nodeEditor = new NodeEditorDisplay();
    }

    @Override
    protected void configure(Configuration config) {
        config.setTitle("ReCTNode Editor");
    }

    @Override
    public void process() {
        ImGui.setNextWindowPos(0, 0);
        ImGui.setNextWindowSize(ImGui.getIO().getDisplaySizeX(), ImGui.getIO().getDisplaySizeY());

        if(ImGui.begin("editor", ImGuiWindowFlags.MenuBar | ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoScrollWithMouse)){

            nodeEditor.drawEditor();

            ImGui.end();
        }


    }

    public static void main(String[] args) {
        launch(new Main());
    }
}