package com.swe.justslidin.io;


import com.swe.justslidin.models.Position;

public interface ClickAction extends Action {
    void execute(Position a);
}
