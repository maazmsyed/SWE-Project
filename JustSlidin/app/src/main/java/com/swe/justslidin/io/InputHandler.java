package com.swe.justslidin.io;


import com.swe.justslidin.models.Position;

/**
 * Handles the inputs from the user
 * Includes two methods: setOnClickAction and onClick
 */


public class InputHandler implements InputListener.Callback
{
    private ClickAction onClickAction;
    public void setOnClickAction(ClickAction onClickAction) {
        this.onClickAction = onClickAction;
    }

    /**
     * This void method runs .execute() if the onClickAction (a ClickAction) is NOT NULL
     * @param pos Position object (pos) is taken as an input
     * the .execute() is called in the MoveAction (action that moves the users), see MoveAction.java
     */

    @Override
    public void onClick(Position pos) {
        if (onClickAction != null) onClickAction.execute(pos);
    }
}
