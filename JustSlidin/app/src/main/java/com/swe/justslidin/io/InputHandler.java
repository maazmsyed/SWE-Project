package com.swe.justslidin.io;


import com.swe.justslidin.models.Position;

public class InputHandler implements InputListener.Callback
{
    private ClickAction onClickAction;
    public void setOnClickAction(ClickAction onClickAction) {
        this.onClickAction = onClickAction;
    }
    @Override
    public void onClick(Position pos) {
        if (onClickAction != null) onClickAction.execute(pos);
    }
}
