package nodes;

import imgui.ImColor;

public enum NodeType {
    Event("#ff6054", "#ff8d85"),
    Control("#383838", "#383838"),
    Math("#d11f4c", "#cf5170"),
    Operation("#ffaa4f", "#ffc180"),
    Value("#62b85a", "#70cc68");


    public final int HeadColor;
    public final int SelectedColor;
    private NodeType(String headColor, String selectedColor)
    {
        HeadColor = ImColor.rgbToColor(headColor);
        SelectedColor = ImColor.rgbToColor(selectedColor);
    }
}
