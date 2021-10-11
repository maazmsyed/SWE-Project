package ync.ysc3232.week7.io;

import ync.ysc3232.week7.models.Position;

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
