package nodes;

import imgui.ImColor;

public enum NodeType {
    Event("#ff6054", "#ff8d85"),
    Control("#0cedaa", "#8debcf"),
    Math("#d11f4c", "#cf5170"),
    Operation("#ffaa4f", "#ffc180"),
    Value("#75ff69", "#a1ff99");


    public final int HeadColor;
    public final int SelectedColor;
    private NodeType(String headColor, String selectedColor)
    {
        HeadColor = ImColor.rgbToColor(headColor);
        SelectedColor = ImColor.rgbToColor(selectedColor);
    }
}
