package nodes;

import imgui.ImColor;

public enum Datatype {
    Bool("#e8a95d", false),
    String("#8ae065", false),
    Int("#6567e0", false),
    Float("#65a7e0", false),

    Variable("#65a7e0", false),

    BoolCollection("#e69c43", true),
    StringCollection("#63c439", true),
    IntCollection("#3335ab", true),
    FloatCollection("#286da8", true),

    Any("#b342f5", false),
    AnyCollection("#b342f5", true),

    Dictionary("#cafa87", false);

    //Type("#0fb800", false),
    //TypeCollection("#0fb800", true);

    public final int PinColor;
    public final boolean IsCollection;
    Datatype(String pinColor, boolean isCollection)
    {
        PinColor = ImColor.rgbToColor(pinColor);
        IsCollection = isCollection;
    }
}
